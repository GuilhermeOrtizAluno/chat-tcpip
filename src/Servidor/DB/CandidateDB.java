package Servidor.DB;

import java.sql.SQLException;

import Servidor.Entitites.Candidate;
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

}
