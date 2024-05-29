package Servidor.Controllers;

import Infrastructure.Requests.BaseRequest;
import com.google.gson.Gson;

import java.io.PrintWriter;

public class Controller {
    public static void Route(String request, PrintWriter out) {
        var gson = new Gson();
        var route = gson.fromJson(request, BaseRequest.class);

        switch (route.operacao) {
            case "cadastrarCandidato" -> CandidateController.Create(out, request);

            case "visualizarCandidato" -> CandidateController.Read(out, request);

            case "atualizarCandidato" -> CandidateController.Update(out, request);

            case "apagarCandidato" -> CandidateController.Delete(out, request);

            case "cadastrarEmpresa" -> CompanyController.Create(out, request);

            case "visualizarEmpresa" -> CompanyController.Read(out, request);

            case "atualizarEmpresa" -> CompanyController.Update(out, request);

            case "apagarEmpresa" -> CompanyController.Delete(out, request);

            case "cadastrarVaga" -> OpportunityController.Create();

            case "visualizarVaga" -> OpportunityController.Read();

            case "atualizarVaga" -> OpportunityController.Update();

            case "deletarVaga" -> OpportunityController.Delete();

            case "loginCandidato" -> AuthController.SignIn(out, request);

            case "loginEmpresa" -> AuthController.SignIn(out, request);

            case "logout" -> AuthController.Logout();

        }
    }
}
