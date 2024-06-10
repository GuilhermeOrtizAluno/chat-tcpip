package Servidor.Services;

import Infrastructure.Requests.AuthRequest;
import Infrastructure.Responses.AuthResponse;
import Infrastructure.Responses.BaseResponse;
import Servidor.DB.CandidateDB;
import Servidor.DB.CompanyDB;
import Servidor.DB.TokenDB;
import Servidor.Entitites.User;
import Utils.Util;

import java.util.UUID;

public class AuthService {
    public static boolean Exists(String token){
        var response = new BaseResponse();
        return TokenDB.Exists(token);
    }

    public static AuthResponse SignInCandidate(AuthRequest request){
        var user = new User(request.email, request.senha);
        var result = CandidateDB.SignIn(user);
        var response = new AuthResponse();
        if(result){
            var uuid = UUID.randomUUID();
            var token = uuid.toString();
            var candidate = CandidateDB.Read(request.email);
            TokenDB.Create(token, candidate.Id,null);

            response.status = 200;
            response.token = token;
        }else {
            response.status = 401;
            response.mensagem = "Login ou senha incorretos";
        }

        return response;
    }

    public static AuthResponse SignInCompany(AuthRequest request){
        var user = new User(request.email, request.senha);
        var result = CompanyDB.SignIn(user);
        var response = new AuthResponse();
        if(result){
            var uuid = UUID.randomUUID();
            var token = uuid.toString();
            var company = CompanyDB.Read(request.email);
            TokenDB.Create(token, null, company.Id);

            response.status = 200;
            response.token = token;
        }else {
            response.status = 401;
            response.mensagem = "Login ou senha incorretos";
        }

        return response;
    }

    public static BaseResponse Logout(String token){
        var response = new BaseResponse();
        var result = TokenDB.Delete(token);

        if(result){
            response.status = 204;
        }else {
            response.status = 401;
            response.mensagem = "Token inválido";
        }

        return response;
    }
}
