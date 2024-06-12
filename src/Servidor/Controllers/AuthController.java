package Servidor.Controllers;

import Infrastructure.Requests.AuthRequest;
import Servidor.Services.AuthService;

import java.io.PrintWriter;

public class AuthController extends BaseController {

    public static void SignInCandidate(PrintWriter out, String json){
        var request = _gson.fromJson(json, AuthRequest.class);

        var response = AuthService.SignInCandidate(request);
        response.operacao = "loginCandidato";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void SignInCompany(PrintWriter out, String json){
        var request = _gson.fromJson(json, AuthRequest.class);

        var response = AuthService.SignInCompany(request);
        response.operacao = "loginEmpresa";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Logout(PrintWriter out, String json){
        var request = _gson.fromJson(json, AuthRequest.class);

        var response = AuthService.Logout(request.token);
        response.operacao = "logout";
        var result = _gson.toJson(response);
        out.println(result);
    }
}
