package Servidor.Controllers;

import Infrastructure.Requests.CompanyRequest;
import Servidor.Services.CompanyService;

import java.io.PrintWriter;

public class CompanyController extends BaseController {
    public static void Read(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Read(request);
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void All(PrintWriter out, String json){

    }

    public static void Create(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Create(request);
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Update(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Update(request);
        var result = _gson.toJson(response);
        out.println(result);
    }

    public static void Delete(PrintWriter out, String json){
        var request = _gson.fromJson(json, CompanyRequest.class);

        var response = CompanyService.Delete(request);
        var result = _gson.toJson(response);
        out.println(result);
    }
}
