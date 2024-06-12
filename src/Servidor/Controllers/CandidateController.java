package Servidor.Controllers;

import Infrastructure.Requests.CandidateCompetenciesRequest;
import Infrastructure.Requests.CandidateRequest;
import Servidor.Services.CandidateService;

import java.io.PrintWriter;

public class CandidateController extends BaseController {

    public static void Read(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateRequest.class);

        var response = CandidateService.Read(request);
        response.operacao = "visualizarCandidato";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void All(PrintWriter out, String json){

    }

    public static void Create(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateRequest.class);

        var response = CandidateService.Create(request);
        response.operacao = "cadastrarCandidato";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Update(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateRequest.class);

        var response = CandidateService.Update(request);
        response.operacao = "atualizarCandidato";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Delete(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateRequest.class);

        var response = CandidateService.Delete(request);
        response.operacao = "apagarCandidato";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void ReadCompetences(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateCompetenciesRequest.class);

        var response = CandidateService.ReadCompetences(request);
        response.operacao = "visualizarCompetenciaExperiencia";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void CreateCompetences(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateCompetenciesRequest.class);

        var response = CandidateService.CreateCompetences(request);
        response.operacao = "cadastrarCompetenciaExperiencia";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void UpdateCompetences(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateCompetenciesRequest.class);

        var response = CandidateService.UpdateCompetences(request);
        response.operacao = "atualizarCompetenciaExperiencia";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void DeleteCompetences(PrintWriter out, String json){
        var request = _gson.fromJson(json, CandidateCompetenciesRequest.class);

        var response = CandidateService.DeleteCompetences(request);
        response.operacao = "apagarCompetenciaExperiencia";
        var result = _gson.toJson(response);
        out.println(result);
    }
}
