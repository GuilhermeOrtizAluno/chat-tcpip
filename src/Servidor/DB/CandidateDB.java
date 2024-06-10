package Servidor.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import Infrastructure.Requests.CandidateCompetenciesRequest;
import Infrastructure.Responses.CandidateCompetencyResponse;
import Servidor.Entitites.Candidate;
import Servidor.Entitites.Competence;
import Servidor.Entitites.User;
import Utils.Util;

public class CandidateDB extends BaseDB {

    public static Candidate Read(String emailRequest) {
        var sql = "SELECT * FROM candidato WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, emailRequest);
            try (var result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                return Candidate.Entity(result);
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void Create(Candidate candidate)  {
        var sql = "INSERT INTO candidato (Nome, Email, Senha) VALUES (?, ?, ?)";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, candidate.Name);
            statement.setString(2, candidate.Email);
            statement.setString(3, candidate.Password);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static boolean Update(Candidate candidate) {
        if (Read(candidate.Email) == null) {
            Util.PrintError("teste");
            return false;
        }

        var sql = "UPDATE candidato SET Nome = ?, Email = ?, Senha = ? WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, candidate.Name);
            statement.setString(2, candidate.Email);
            statement.setString(3, candidate.Password);
            statement.setString(4, candidate.Email);

            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean Delete(String email)  {
        if (Read(email) == null) {
            return false;
        }

        var sql = "DELETE FROM candidato WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean SignIn(User user) {
        var sql = "SELECT Email, Senha FROM candidato WHERE Email = ? AND Senha = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.Email);
            statement.setString(2, user.Password);
            try (var result = statement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }

    public static ArrayList<CandidateCompetencyResponse> GetCompetencies(int candidateId) {
        var competences = new ArrayList<CandidateCompetencyResponse>(){};
        var sql = "SELECT c.*,  cc.Experiencia " +
                "FROM candidato_x_competencia cc " +
                "INNER JOIN competencia c ON c.ID = cc.CompetenciaID " +
                "WHERE cc.CandidatoID = ?";

        try (var connection = getConnection();
             var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidateId);

            var rs = stmt.executeQuery();
            while (rs.next()) {
                competences.add(Competence.EntityCandidateCompetency(rs));
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }

        return  competences;
    }

    public static void CreateCompetencies(int candidateId, CandidateCompetenciesRequest request)  {
        var competences = request.competenciaExperiencia.stream().map(c -> c.competencia).toList();
        var competenciasFiltered = CompetenceDB.FilterCompetences(competences);

        var sql = "INSERT INTO candidato_x_competencia (CandidatoID, CompetenciaID, Experiencia) " +
                "VALUES (?, ?, ?)";
        try (var connection = getConnection();
             var stmt = connection.prepareStatement(sql)) {

            for (var competency : competenciasFiltered) {
                var experience = request.competenciaExperiencia.stream()
                        .filter(c -> Objects.equals(c.competencia, competency.Title))
                        .findFirst().orElse(null);

                stmt.setInt(1, candidateId);
                stmt.setInt(2, competency.Id);
                stmt.setInt(3, experience.experiencia);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void UpdateCompetencies(int candidateId, CandidateCompetenciesRequest request) {
        DeleteCompetencies(candidateId);
        CreateCompetencies(candidateId, request);
    }

    public static boolean DeleteCompetencies(int id)  {

        var sql1 = "DELETE FROM candidato_x_competencia WHERE CandidatoID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql1)) {

            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

}
