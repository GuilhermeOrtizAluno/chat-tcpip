package Servidor.Services;

import Infrastructure.Requests.CompanyRequest;
import Infrastructure.Responses.AuthResponse;
import Infrastructure.Responses.BaseResponse;
import Infrastructure.Responses.CompanyResponse;
import Servidor.DB.CompanyDB;
import Servidor.DB.TokenDB;
import Servidor.Entitites.Company;
import Servidor.Services.Validations.CompanyValidation;

import java.util.UUID;

public class CompanyService {
    public static CompanyResponse Read(CompanyRequest request) {

        var company = CompanyDB.Read(request.email);
        var response = new CompanyResponse();

        if(company != null){
            response.status = 201;
            response.senha = company.Password;
            response.descricao = company.Description;
            response.ramo = company.Ramo;
            response.cnpj = company.CNPJ;
            response.razaoSocial = company.CorporateName;

        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

    public static AuthResponse Create(CompanyRequest request) {
        var validateEmail = CompanyValidation.ValidateEmail(request.email);
        var validatePassword = CompanyValidation.ValidatePassword(request.senha);

        var response = new AuthResponse();

        if(!validateEmail){
            response.status = 422;
            response.mensagem = "Formato de email incorreto";
            return response;
        }

        if(!validatePassword){
            response.status = 422;
            response.mensagem = "Formato de senha incorreto";
            return response;
        }

        var company = new Company(request.email, request.senha,
                request.ramo, request.descricao, request.cnpj, request.razaoSocial);
        var companyId = CompanyDB.Create(company);

        var uuid = UUID.randomUUID();
        var token = uuid.toString();
        TokenDB.Create(token, null, companyId);

        response.status = 201;
        response.token = token;
        return response;
    }

    public static BaseResponse Update(CompanyRequest request)  {
        var validateEmail = CompanyValidation.ValidateEmail(request.email);
        var validatePassword = CompanyValidation.ValidatePassword(request.senha);

        var response = new BaseResponse();
        if(validateEmail && validatePassword){
            var company = new Company(request.email, request.senha,
                    request.ramo, request.descricao, request.cnpj, request.razaoSocial);
            var result = CompanyDB.Update(company);
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

    public static BaseResponse Delete(CompanyRequest request)  {
        var result = CompanyDB.Delete(request.email);

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
