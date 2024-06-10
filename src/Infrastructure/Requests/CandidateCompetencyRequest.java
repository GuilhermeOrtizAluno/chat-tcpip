package Infrastructure.Requests;

public class CandidateCompetencyRequest {
    public String competencia;
    public int experiencia;


    public CandidateCompetencyRequest(String competencia, int experiencia) {
        this.competencia = competencia;
        this.experiencia = experiencia;
    }
}
