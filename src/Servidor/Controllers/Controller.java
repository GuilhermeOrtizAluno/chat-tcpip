package Servidor.Controllers;

import Infrastructure.Requests.BaseRequest;

import java.io.PrintWriter;

public class Controller extends BaseController {
    public static void Route(String request, PrintWriter out) {
        var route = _gson.fromJson(request, BaseRequest.class);

        switch (route.operacao) {
            case "cadastrarCandidato" -> CandidateController.Create(out, request);

            case "visualizarCandidato" -> {
                if(isAuthenticated(out, request))
                    CandidateController.Read(out, request);
            }

            case "atualizarCandidato" -> {
                if(isAuthenticated(out, request))
                    CandidateController.Update(out, request);
            }

            case "apagarCandidato" -> {
                if(isAuthenticated(out, request))
                    CandidateController.Delete(out, request);
            }

            case "cadastrarEmpresa" -> CompanyController.Create(out, request);

            case "visualizarEmpresa" -> {
                if(isAuthenticated(out, request))
                    CompanyController.Read(out, request);
            }

            case "atualizarEmpresa" -> {
                if(isAuthenticated(out, request))
                    CompanyController.Update(out, request);
            }

            case "apagarEmpresa" -> {
                if(isAuthenticated(out, request))
                    CompanyController.Delete(out, request);
            }

            case "cadastrarVaga" -> {
                if(isAuthenticated(out, request))
                    OpportunityController.Create(out, request);
            }

            case "visualizarVaga" -> {
                if(isAuthenticated(out, request))
                    OpportunityController.Read(out, request);
            }

            case "atualizarVaga" -> {
                if(isAuthenticated(out, request))
                    OpportunityController.Update(out, request);
            }

            case "apagarVaga" -> {
                if(isAuthenticated(out, request))
                    OpportunityController.Delete(out, request);
            }

            case "cadastrarCompetenciaExperiencia" -> {
                if(isAuthenticated(out, request))
                    CandidateController.CreateCompetences(out, request);
            }

            case "visualizarCompetenciaExperiencia" -> {
                if(isAuthenticated(out, request))
                    CandidateController.ReadCompetences(out, request);
            }

            case "atualizarCompetenciaExperiencia" -> {
                if(isAuthenticated(out, request))
                    CandidateController.UpdateCompetences(out, request);
            }

            case "apagarCompetenciaExperiencia" -> {
                if(isAuthenticated(out, request))
                    CandidateController.DeleteCompetences(out, request);
            }

            case "loginCandidato" -> AuthController.SignInCandidate(out, request);

            case "loginEmpresa" -> AuthController.SignInCompany(out, request);

            case "logout" -> AuthController.Logout(out, request);

        }
    }
}
