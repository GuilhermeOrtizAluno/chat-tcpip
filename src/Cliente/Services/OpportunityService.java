package Cliente.Services;

import Infrastructure.Requests.OpportunityFilterRequest;
import Infrastructure.Requests.OpportunityRequest;
import Utils.Util;

import java.util.List;

public class OpportunityService extends BaseService {
    public static String Read(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite id da vaga:");
        var id = _scanner.nextInt();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "visualizarVaga";
        request.token = token;
        request.idVaga = id;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String All(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "listarVagas";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String Filter(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite o tipo de filtro:");
        Util.Println("1 - OR");
        Util.Println("2 - AND");
        var type = _scanner.nextInt();

        Util.Println("Digite as copetencias separado por virgula:");
        var competences = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "filtrarVagas";
        request.token = token;

        var filters = new OpportunityFilterRequest();
        filters.tipo = type == 1 ? "OR" : "AND";
        filters.competencias = List.of(competences.split(","));

        request.filtros = filters;

        return _gson.toJson(request);
    }

    public static String Create(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite Faixa Salarial:");
        var salarial = _scanner.nextInt();

        Util.Println("Digite Descriccao:");
        var description = _scanner.nextLine();

        Util.Println("Digite Estado:");
        Util.Println("1-Disponível)");
        Util.Println("2-Divulgavel):");
        var status = _scanner.nextInt();

        Util.Println("Digite as copetencias separado por virgula:");
        var competences = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "cadastrarVaga";
        request.token = token;
        request.email = email;
        request.faixaSalarial = salarial;
        request.descricao = description;
        request.estado = status == 1 ? "Disponível" : "Divulgavel";
        request.competencias = List.of(competences.split(","));

        return _gson.toJson(request);
    }

    public static String Update(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite id da vaga:");
        var id = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite Faixa Salarial:");
        var salarial = _scanner.nextInt();

        Util.Println("Digite Descriccao:");
        var description = _scanner.nextLine();

        Util.Println("Digite Estado:");
        Util.Println("1-Disponível)");
        Util.Println("2-Divulgavel):");
        var status = _scanner.nextInt();

        Util.Println("Digite as copetencias separado por virgula:");
        var competences = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "atualizarVaga";
        request.token = token;
        request.idVaga = Integer.parseInt(id);
        request.email = email;
        request.faixaSalarial = salarial;
        request.descricao = description;
        request.estado = status == 1 ? "Disponível" : "Divulgavel";
        request.competencias = List.of(competences.split(","));

        return _gson.toJson(request);
    }

    public static String Delete(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite id da vaga:");
        var id = _scanner.nextInt();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new OpportunityRequest();
        request.operacao = "apagarVaga";
        request.token = token;
        request.idVaga = id;
        request.email = email;

        return _gson.toJson(request);
    }
}
