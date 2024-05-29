package Cliente.Services;

import Infrastructure.Requests.CandidateRequest;
import Utils.Util;

public class CandidateService extends BaseService {
    public static String SignIn() {
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "loginCandidato";
        request.email = email;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Read() {
        Util.Println("Digite email a ser buscado:");
        var email = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "visualizarCandidato";
        request.email = email;

        return _gson.toJson(request);
    }

    public static String Create() {
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite nome:");
        var nome = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "cadastrarCandidato";
        request.email = email;
        request.nome = nome;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Update() {
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite nome:");
        var nome = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "atualizarCandidato";
        request.email = email;
        request.nome = nome;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Delete() {
        Util.Println("Digite email para ser deletado:");
        var email = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "apagarCandidato";
        request.email = email;

        return _gson.toJson(request);
    }

    public static String Logou(){
        Util.Println("Nao exagere nao tive tempo");
        return "";
    }
}
