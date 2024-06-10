package Servidor.Entitites;

import Infrastructure.Responses.CandidateCompetencyResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Competence extends BaseEntity {
    public String Title;

    public List<Candidate> Candidates;
    public List<Opportunity> Opportunities;

    public Competence() {}

    public Competence(int id, String title) {
        Id = id;
        Title = title;
    }

    public static Competence Entity(ResultSet rs) throws SQLException {
        var id = rs.getInt("ID");
        var title = rs.getString("Titulo");

        return new Competence(id, title);
    }

    public static CandidateCompetencyResponse EntityCandidateCompetency(ResultSet rs) throws SQLException {
        var id = rs.getInt("ID");
        var title = rs.getString("Titulo");
        var experiencia = rs.getInt("Experiencia");

        return new CandidateCompetencyResponse(title, experiencia);
    }
}
