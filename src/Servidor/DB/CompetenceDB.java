package Servidor.DB;

import Servidor.Entitites.Competence;
import Utils.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompetenceDB extends BaseDB {
    public static Competence Read(int id) {
        var sql = "SELECT * FROM competencia WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (var result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                return Competence.Entity(result);
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void Create(Competence competence)  {
        var sql = "INSERT INTO competencia (Titulo) VALUES (?)";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, competence.Title);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static boolean Update(Competence competence) {
        if (Read(competence.Id) == null) {
            Util.PrintError("teste");
            return false;
        }

        var sql = "UPDATE competencia SET Titulo = ? WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, competence.Title);
            statement.setInt(5, competence.Id);

            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean Delete(int id)  {
        if (Read(id) == null) {
            return false;
        }

        var sql = "DELETE FROM competencia WHERE ID = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Competence> FilterCompetences(List<String> competencias){
        var competences = new ArrayList<Competence>(){};
        String inClause = String.join(",", Collections.nCopies(competencias.size(), "?"));
        var sql = "SELECT * FROM competencia WHERE Titulo IN (" + inClause + ")";
        try (var connection = getConnection();
             var stmt = connection.prepareStatement(sql)) {
            var index = 1;
            for (var title : competencias) {
                stmt.setString(index++, title);
            }

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
}
