package Servidor.Services;

import Infrastructure.Requests.CandidateCompetenciesRequest;
import Infrastructure.Requests.CandidateRequest;
import Infrastructure.Responses.AuthResponse;
import Infrastructure.Responses.BaseResponse;
import Infrastructure.Responses.CandidateCompetenciesResponse;
import Infrastructure.Responses.CandidateResponse;
import Servidor.DB.CandidateDB;
import Servidor.Entitites.Candidate;
import Servidor.Services.Validations.CandidateValidation;

import java.util.Objects;

public class CandidateService {

    public static CandidateResponse Read(CandidateRequest request) {

        var candidate = CandidateDB.Read(request.email);
        var response = new CandidateResponse();

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

    public static AuthResponse Create(CandidateRequest request) {
        var validateEmail = CandidateValidation.ValidateEmail(request.email);
        var validatePassword = CandidateValidation.ValidatePassword(request.senha);

        var response = new AuthResponse();
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

        var response = new BaseResponse();
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

    public static CandidateCompetenciesResponse ReadCompetences(CandidateCompetenciesRequest request) {
        var response = new CandidateCompetenciesResponse();

        if(!Objects.equals(request.email, "")){
            var candidate = CandidateDB.Read(request.email);

            if(candidate == null){
                response.status = 422;
                response.mensagem = "Candidato nao encontrada";
                return response;
            }

            var competencies = CandidateDB.GetCompetencies(candidate.Id);

            response.status = 201;
            response.competenciaExperiencia = competencies;
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }

    public static BaseResponse CreateCompetences(CandidateCompetenciesRequest request) {
        var response = new BaseResponse();
        if(!Objects.equals(request.email, "")){
            var candidate = CandidateDB.Read(request.email);

            if(candidate == null){
                response.status = 422;
                response.mensagem = "Candidato nao encontrada";
                return response;
            }

            CandidateDB.CreateCompetencies(candidate.Id,request);
            response.status = 201;
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }

    public static BaseResponse UpdateCompetences(CandidateCompetenciesRequest request)  {
        var response = new BaseResponse();
        if(!Objects.equals(request.email, "")){
            var candidate = CandidateDB.Read(request.email);

            if(candidate == null){
                response.status = 422;
                response.mensagem = "Candidato nao encontrada";
                return response;
            }

            CandidateDB.UpdateCompetencies(candidate.Id, request);
            response.status = 201;
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }

    public static BaseResponse DeleteCompetences(CandidateCompetenciesRequest request)  {
        var response = new BaseResponse();
        if(!Objects.equals(request.email, "")){
            var candidate = CandidateDB.Read(request.email);

            if(candidate == null){
                response.status = 422;
                response.mensagem = "Candidato nao encontrada";
                return response;
            }

            CandidateDB.DeleteCompetencies(candidate.Id);
            response.status = 201;
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }
}



