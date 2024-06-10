package Servidor.Entitites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Candidate extends User {
    public String Name;

    public List<Competence> Competences;

    public Candidate() {

    }

    public Candidate(String name, String email, String password) {
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public Candidate(int id, String name, String email, String password) {
        this.Id = id;
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public static Candidate Entity(ResultSet rs) throws SQLException {
        var id = rs.getInt("ID");
        var email = rs.getString("Email");
        var name = rs.getString("Nome");
        var password = rs.getString("Senha");
        return new Candidate(id, name, email, password);
    }
}
