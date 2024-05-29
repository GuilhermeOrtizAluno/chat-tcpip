package Servidor.Entitites;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidate extends User {

    public Candidate(String nome, String email, String password) {
        super(nome, email, password);
    }

    public Candidate() {

    }

    public static Candidate Entity(ResultSet rs) throws SQLException {
        String email = rs.getString("Email");
        String name = rs.getString("Nome");
        String password = rs.getString("Senha");
        return new Candidate(name, email, password);
    }
}
