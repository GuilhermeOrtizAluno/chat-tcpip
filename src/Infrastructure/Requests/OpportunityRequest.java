package Infrastructure.Requests;

import java.util.List;

public class OpportunityRequest extends BaseRequest {
    public int idVaga;
    public String descricao;
    public String estado;
    public int faixaSalarial;
    public String email;
    public List<String> competencias;
    public OpportunityFilterRequest filtros;
}
