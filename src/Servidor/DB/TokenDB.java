package Servidor.DB;

import Utils.Util;

import java.sql.SQLException;

public class TokenDB extends BaseDB{
    public static boolean Exists(String token) {
        var sql = "SELECT * FROM token WHERE Token = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, token);
            try (var result = statement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static String CompanyOrCandidate(String token) {
        var sql = "SELECT * FROM token WHERE Token = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            try (var rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                var company = rs.getInt("EmpresaID");
                var candidate = rs.getInt("CandidatoID");

                return company != 0 ? "COMPANY" : candidate != 0 ? "CANDIDATE" : null;
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void Create(String token, Integer candidatoID, Integer empresaID)  {
        var sql = "INSERT INTO token (Token, CandidatoID, EmpresaID) VALUES (?, ?, ?)";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, token);
            if (candidatoID == null) {
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, candidatoID);
            }

            if (empresaID == null) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3, empresaID);
            }
            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static boolean Delete(String token) {
        if (!Exists(token)) {
            return false;
        }

        var sql = "DELETE FROM token WHERE Token = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, token);
            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
