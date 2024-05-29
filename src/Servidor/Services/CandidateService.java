package Servidor.Services;

import Infrastructure.Requests.CandidateRequest;
import Infrastructure.Responses.BaseResponse;
import Infrastructure.Responses.Candidate.*;

import Servidor.DB.CandidateDB;
import Servidor.Entitites.Candidate;
import Servidor.Services.Validations.CandidateValidation;

public class CandidateService {

    public static ReadCandidateResponse Read(CandidateRequest request) {

        var candidate = CandidateDB.Read(request.email);
        var response = new ReadCandidateResponse();

        if(candidate != null){
            response.status = 201;
            response.nome = candidate.Name;
            response.senha = candidate.Password;
        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

    public static CreateCandidateResponse Create(CandidateRequest request) {
        var validateEmail = CandidateValidation.ValidateEmail(request.email);
        var validatePassword = CandidateValidation.ValidatePassword(request.senha);

        var response = new CreateCandidateResponse();
        if(validateEmail && validatePassword){
            var candidate = new Candidate(request.nome, request.email, request.senha);
            CandidateDB.Create(candidate);
            response.status = 200;
            response.token = "Usuario Cadastrado com Sucesso!";
        }else {
            response.status = 404;
            response.mensagem = "";
        }

        return response;
    }

    public static BaseResponse Update(CandidateRequest request)  {
        var validateEmail = CandidateValidation.ValidateEmail(request.email);
        var validatePassword = CandidateValidation.ValidatePassword(request.senha);

        var response = new CreateCandidateResponse();
        if(validateEmail && validatePassword){
            var candidate = new Candidate(request.nome, request.email, request.senha);
            var result = CandidateDB.Update(candidate);
            if(result){
                response.status = 201;
            }else {
                response.status = 404;
                response.mensagem = "E-mail não encontrado";
            }
        }else {
            response.status = 404;
            response.mensagem = "";
        }

        return response;
    }

    public static BaseResponse Delete(CandidateRequest request)  {
        var result = CandidateDB.Delete(request.email);

        var response = new BaseResponse();
        if(result){
            response.status = 201;
        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

}



