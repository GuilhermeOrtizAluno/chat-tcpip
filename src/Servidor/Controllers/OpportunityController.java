package Servidor.Controllers;


import Infrastructure.Requests.OpportunityRequest;
import Servidor.Services.OpportunityService;

import java.io.PrintWriter;

public class OpportunityController extends BaseController {
    public static void Read(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.Read(request);
        response.operacao = "visualizarVaga";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void All(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.All(request);
        response.operacao = "listarVagas";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Filter(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.Filter(request);
        response.operacao = "filtrarVagas";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Create(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.Create(request);
        response.operacao = "cadastrarVaga";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Update(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.Update(request);
        response.operacao = "atualizarVaga";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Delete(PrintWriter out, String json){
        var request = _gson.fromJson(json, OpportunityRequest.class);

        var response = OpportunityService.Delete(request);
        response.operacao = "apagarVaga";
        var result = _gson.toJson(response);
        out.println(result);
    }
}
