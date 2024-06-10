package Servidor.DB;

import Servidor.Entitites.Competence;
import Servidor.Entitites.Opportunity;
import Utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpportunityDB extends BaseDB{
    public static Opportunity Read(int id, String emailRequest) {
        var sql = "SELECT * FROM vaga WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (var result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }
                var opportunity = Opportunity.Entity(result);
                opportunity.Competences = GetCompetences(id);

                return opportunity;
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static int Create(Opportunity opportunity)  {
        var sql = "INSERT INTO vaga (Descricao, Estado, Faixa_salarial, EmpresaID) VALUES (?, ?, ?, ?)";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, opportunity.Description);
            statement.setString(2, opportunity.Status);
            statement.setInt(3, opportunity.SalaryRange);
            statement.setInt(4, opportunity.CompanyId);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }

        return 0;
    }

    public static boolean Update(Opportunity opportunity) {
        if (Read(opportunity.Id, "") == null) {
            Util.PrintError("teste");
            return false;
        }

        var sql = "UPDATE vaga SET Descricao = ?, Estado = ?, Faixa_salarial = ?, EmpresaID = ? WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, opportunity.Description);
            statement.setString(2, opportunity.Status);
            statement.setInt(3, opportunity.SalaryRange);
            statement.setInt(4, opportunity.CompanyId);
            statement.setInt(5, opportunity.Id);

            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean Delete(int id)  {
        if (Read(id, "") == null) {
            return false;
        }

        var sql1 = "DELETE FROM vaga_x_competencia WHERE VagaID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql1)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        var sql2 = "DELETE FROM vaga WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql2)) {

            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Competence> GetCompetences(int id){
        var competences = new ArrayList<Competence>(){};
        var sql = "SELECT c.* " +
                "FROM vaga_x_competencia vc " +
                "INNER JOIN competencia c ON c.ID = vc.CompetenciaID " +
                "WHERE vc.VagaID = ?";

        try (var connection = getConnection();
             var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();
            while (rs.next()) {
                competences.add(Competence.Entity(rs));
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }

        return  competences;
    }

    public static void AddCompetences(int opportunityId, List<String> competences)  {
        var competenciasFiltered = CompetenceDB.FilterCompetences(competences);

        var sql = "INSERT INTO vaga_x_competencia (VagaID, CompetenciaID) VALUES (?, ?)";
        try (var connection = getConnection();
             var stmt = connection.prepareStatement(sql)) {

            for (var competency : competenciasFiltered) {
                stmt.setInt(1, opportunityId);
                stmt.setInt(2, competency.Id);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void UpdateCompetences(int id, List<String> competences)  {
        DeleteCompetences(id);
        AddCompetences(id, competences);
    }

    public static void DeleteCompetences(int id){
        if (Read(id, "") == null) {
            return;
        }

        var sql1 = "DELETE FROM vaga_x_competencia WHERE VagaID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql1)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
