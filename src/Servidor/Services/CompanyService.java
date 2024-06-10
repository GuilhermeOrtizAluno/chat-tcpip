package Servidor.Services;

import Infrastructure.Requests.CompanyRequest;
import Infrastructure.Responses.AuthResponse;
import Infrastructure.Responses.BaseResponse;
import Infrastructure.Responses.CompanyResponse;
import Servidor.DB.CompanyDB;
import Servidor.Entitites.Company;
import Servidor.Services.Validations.CompanyValidation;

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
        if(validateEmail && validatePassword){
            var company = new Company(request.email, request.senha,
                    request.ramo, request.descricao, request.cnpj, request.razaoSocial);
            CompanyDB.Create(company);
            response.status = 200;
            response.token = "Empresa Cadastrada com Sucesso!";
        }else {
            response.status = 404;
            response.mensagem = "";
        }

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
