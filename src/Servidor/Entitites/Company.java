package Servidor.Entitites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Company extends User {
    public String Ramo;
    public String Description;
    public String CNPJ;
    public String CorporateName;

    public List<Opportunity> Opportunities;

    public Company(String email, String password, String ramo,
                   String description, String cnpj, String corporateName) {
        this.Email = email;
        this.Password = password;
        this.Ramo = ramo;
        this.Description = description;
        this.CNPJ = cnpj;
        this.CorporateName = corporateName;
    }

    public Company(int id, String email, String password, String ramo,
                   String description, String cnpj, String corporateName) {
        this.Id = id;
        this.Email = email;
        this.Password = password;
        this.Ramo = ramo;
        this.Description = description;
        this.CNPJ = cnpj;
        this.CorporateName = corporateName;
    }

    public Company(String email, String password) {
        super(email, password);
    }

    public Company(){}

    public static Company Entity(ResultSet rs) throws SQLException {
        var id = rs.getInt("ID");
        var email = rs.getString("Email");
        var password = rs.getString("Senha");
        var ramo = rs.getString("Ramo");
        var description = rs.getString("Descricao");
        var cnpj = rs.getString("CNPJ");
        var corporateName = rs.getString("Razao_Social");
        return new Company(id, email, password,ramo, description, cnpj, corporateName);
    }
}
