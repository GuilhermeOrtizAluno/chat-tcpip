package Cliente.Services;

import Infrastructure.Requests.AuthRequest;
import Infrastructure.Requests.CandidateRequest;
import Infrastructure.Requests.CompanyRequest;
import Utils.Util;

import java.io.IOException;

public class AuthService extends BaseService  {

    public AuthService(String host, int port) throws IOException {
        super(host, port);
    }



    public static String SignInCandidate() {
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

    public static String SignInCompany(){
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CompanyRequest();
        request.operacao = "loginEmpresa";
        request.email = email;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Logout(){
        Util.Println("Digite token logado:");
        var token = _scanner.nextLine();

        var request = new AuthRequest();
        request.operacao = "logout";
        request.token = token;

        return _gson.toJson(request);
    }
}
