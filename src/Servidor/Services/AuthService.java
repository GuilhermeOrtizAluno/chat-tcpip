package Servidor.Services;

import Infrastructure.Requests.SignInRequest;
import Infrastructure.Responses.AuthResponse;
import Servidor.DB.CandidateDB;
import Servidor.Entitites.User;

public class AuthService {
    public static AuthResponse SignIn(SignInRequest request){
        var user = new User(request.email, request.senha);
        var result = CandidateDB.SignIn(user);
        var response = new AuthResponse();
        if(result){
            response.status = 200;
            response.token = "Login cadastrado com Sucesso!";
        }else {
            response.status = 401;
            response.mensagem = "Login ou senha incorretos";
        }

        return response;
    }
}
