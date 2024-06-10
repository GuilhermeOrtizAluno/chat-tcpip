package Servidor.Controllers;

import Infrastructure.Requests.BaseRequest;
import Infrastructure.Responses.AuthResponse;
import Servidor.Services.AuthService;
import com.google.gson.Gson;

import java.io.PrintWriter;

public class BaseController {
    protected static final Gson _gson = new Gson();

    protected static boolean isAuthenticated(PrintWriter out, String request){
        var route = _gson.fromJson(request, BaseRequest.class);

        var authenticated = true;
        var response = new AuthResponse();
        if(route.token == null) {
            response.status = 404;
            response.mensagem = "Campo token obrigatorio";
            authenticated = false;
        }
        else{
            authenticated = AuthService.Exists(route.token);
            if(!authenticated) {
                response.status = 404;
                response.mensagem = "Token nao existe";
            }
        }

        if(!authenticated) {
            var result = _gson.toJson(response);
            out.println(result);
        }

        return authenticated;
    }
}
