package Servidor.DB;

import Servidor.Entitites.Company;
import Servidor.Entitites.User;
import Utils.Util;

import java.sql.SQLException;

public class CompanyDB extends BaseDB{
    public static Company Read(String email) {
        var sql = "SELECT * FROM empresa WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (var result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                return Company.Entity(result);
            }
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void Create(Company company) {
        var sql = "INSERT INTO empresa (Nome, Email, Senha, Descricao, CNPJ, Razao_Social, Ramo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.Name);
            statement.setString(2, company.Email);
            statement.setString(3, company.Password);
            statement.setString(4, company.Description);
            statement.setString(5, company.CNPJ);
            statement.setString(6, company.CorporateName);
            statement.setString(7, company.Ramo);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static boolean Update(Company company) {
        if (Read(company.Email) == null) {
            Util.PrintError("teste");
            return false;
        }

        var sql = "UPDATE empresa SET Nome = ?, Email = ?, Senha = ?, Descricao = ?, CNPJ = ?, Razao_Social = ?, Ramo = ? WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.Name);
            statement.setString(2, company.Email);
            statement.setString(3, company.Password);
            statement.setString(4, company.Description);
            statement.setString(5, company.CNPJ);
            statement.setString(6, company.CorporateName);
            statement.setString(7, company.Ramo);
            statement.setString(8, company.Email);

            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean Delete(String email) {
        if (Read(email) == null) {
            return false;
        }

        var sql = "DELETE FROM empresa WHERE Email = ?";
        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            var affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Util.PrintError("SQL error occurred: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean SignIn(User user) {
        String sql = "SELECT Email, Senha FROM empresa WHERE Email = ? AND Senha = ?";
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
