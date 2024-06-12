package Servidor.Services;

import Infrastructure.Requests.OpportunityRequest;
import Infrastructure.Responses.BaseResponse;
import Infrastructure.Responses.OpportunityListItenResponse;
import Infrastructure.Responses.OpportunityListResponse;
import Infrastructure.Responses.OpportunityResponse;
import Servidor.DB.CompanyDB;
import Servidor.DB.OpportunityDB;
import Servidor.Entitites.Opportunity;

import java.util.Objects;
import java.util.stream.Collectors;

public class OpportunityService {
    public static OpportunityResponse Read(OpportunityRequest request) {
        var opportunity = OpportunityDB.Read(request.idVaga, request.email);
        var response = new OpportunityResponse();

        if(opportunity != null){
            response.status = 201;
            response.idVaga = opportunity.Id;
            response.faixaSalarial = opportunity.SalaryRange;
            response.descricao = opportunity.Description;
            response.estado = opportunity.Status;
            response.competencias  = opportunity.Competences.stream()
                    .map(comp -> comp.Title)
                    .collect(Collectors.toList());
        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

    public static OpportunityListResponse All(OpportunityRequest request) {
        var opportunities = OpportunityDB.All();
        var response = new OpportunityListResponse();

        if(opportunities != null){
            response.status = 201;
            response.vagas = opportunities.stream()
                    .map(opportunity -> new OpportunityListItenResponse(opportunity.Id, opportunity.Description))
                    .collect(Collectors.toList());
        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

    public static OpportunityListResponse Filter(OpportunityRequest request) {
        var opportunities = OpportunityDB.Filter(request);
        var response = new OpportunityListResponse();

        if(opportunities != null){
            response.status = 201;
            response.vagas = opportunities.stream()
                    .map(opportunity -> new OpportunityListItenResponse(opportunity.Id, opportunity.Description))
                    .collect(Collectors.toList());
        }else {
            response.status = 404;
            response.mensagem = "E-mail não encontrado";
        }

        return response;
    }

    public static BaseResponse Create(OpportunityRequest request) {

        var response = new BaseResponse();
        if(!Objects.equals(request.email, "")){
            var company = CompanyDB.Read(request.email);

            if(company == null){
                response.status = 422;
                response.mensagem = "Empressa nao encontrada";
                return response;
            }

            var opportunity = new Opportunity(request.descricao, request.estado,
                    request.faixaSalarial, company.Id);

            var idOpportunity = OpportunityDB.Create(opportunity);

            if(idOpportunity == 0){
                response.status = 422;
                response.mensagem = "Erro ao criar vaga";
            }

            OpportunityDB.AddCompetences(idOpportunity, request.competencias);

            response.status = 201;
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }

    public static BaseResponse Update(OpportunityRequest request)  {

        var response = new BaseResponse();
        if(!Objects.equals(request.email, "")){
            var company = CompanyDB.Read(request.email);

            if(company == null){
                response.status = 422;
                response.mensagem = "Empressa nao encontrada";
                return response;
            }

            var opportunity = new Opportunity(request.idVaga ,request.descricao, request.estado,
                    request.faixaSalarial,  1);

            var result = OpportunityDB.Update(opportunity);
            OpportunityDB.UpdateCompetences(request.idVaga, request.competencias);

            if(result){
                response.status = 201;
            }else {
                response.status = 404;
                response.mensagem = "E-mail não encontrado";
            }
        }else {
            response.status = 422;
            response.mensagem = "Campo email obrigatorio";
        }

        return response;
    }

    public static BaseResponse Delete(OpportunityRequest request)  {
        var result = OpportunityDB.Delete(request.idVaga);

        var response = new BaseResponse();
        if(result){
            response.status = 201;
            response.mensagem = "Vaga apagada com sucesso";
        }else {
            response.status = 422;
            response.mensagem = "Vaga não encontrado";
        }

        return response;
    }
}
