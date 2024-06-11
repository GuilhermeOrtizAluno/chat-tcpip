package Infrastructure.Responses;

public class OpportunityListItenResponse extends BaseResponse {
    public String nome;
    public int idVaga;

    public OpportunityListItenResponse(int idVaga, String nome) {
        this.nome = nome;
        this.idVaga = idVaga;
    }
}
