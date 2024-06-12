package Servidor.Controllers;

import Infrastructure.Requests.CompanyRequest;
import Servidor.Services.CompanyService;

import java.io.PrintWriter;

public class CompanyController extends BaseController {
    public static void Read(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Read(request);
        response.operacao = "visualizarEmpresa";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void All(PrintWriter out, String json){

    }

    public static void Create(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Create(request);
        response.operacao = "cadastrarEmpresa";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Update(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Update(request);
        response.operacao = "atualizarEmpresa";
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Delete(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Delete(request);
        response.operacao = "apagarEmpresa";
        var result = _gson.toJson(response);
        out.println(result);
    }
}
