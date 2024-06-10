package Servidor.Entitites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Opportunity extends BaseEntity {
    public String Description;
    public String Status;
    public int SalaryRange;
    public int CompanyId;

    public Company Company;
    public List<Competence> Competences;

    public Opportunity(){}

    public Opportunity(String descricao, String estado, int faixaSalarial, int empresaId) {
        Description = descricao;
        Status = estado;
        SalaryRange = faixaSalarial;
        CompanyId = empresaId;
    }

    public Opportunity(int id, String descricao, String estado, int faixaSalarial, int empresaId) {
        Id = id;
        Description = descricao;
        Status = estado;
        SalaryRange = faixaSalarial;
        CompanyId = empresaId;
    }

    public static Opportunity Entity(ResultSet rs) throws SQLException {
        var id = rs.getInt("ID");
        var descricao = rs.getString("Descricao");
        var estado = rs.getString("Estado");
        var faixaSalarial = rs.getInt("Faixa_salarial");
        var empresaId = rs.getInt("EmpresaID");

        return new Opportunity(id, descricao, estado, faixaSalarial, empresaId);
    }
}
