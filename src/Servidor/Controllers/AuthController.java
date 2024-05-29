package Servidor.Controllers;

import Infrastructure.Requests.CandidateRequest;
import Servidor.Services.CandidateService;

import java.io.PrintWriter;

public class AuthController extends BaseController {

    public static void SignIn(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateRequest.class);

        var response = CandidateService.Read(request);
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Logout(){

    }
}
