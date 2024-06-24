package Servidor.Controllers;

import Infrastructure.Requests.BaseRequest;

import java.io.PrintWriter;

public class Controller extends BaseController {
    public static void Route(String request, PrintWriter out) {
        var route = _gson.fromJson(request, BaseRequest.class);

        switch (route.operacao) {
            case "cadastrarCandidato" -> CandidateController.Create(out, request);

            case "visualizarCandidato" -> {
                if(isAuthenticatedCandidate(out, request))
                    CandidateController.Read(out, request);
            }

            case "atualizarCandidato" -> {
                if(isAuthenticatedCandidate(out, request))
                    CandidateController.Update(out, request);
            }

            case "apagarCandidato" -> {
                if(isAuthenticatedCandidate(out, request))
                    CandidateController.Delete(out, request);
            }

            case "cadastrarEmpresa" -> CompanyController.Create(out, request);

            case "visualizarEmpresa" -> {
                if(isAuthenticatedCompany(out, request))
                    CompanyController.Read(out, request);
            }

            case "atualizarEmpresa" -> {
                if(isAuthenticatedCompany(out, request))
                    CompanyController.Update(out, request);
            }

            case "apagarEmpresa" -> {
                if(isAuthenticatedCompany(out, request))
                    CompanyController.Delete(out, request);
            }

            case "cadastrarVaga" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.Create(out, request);
            }

            case "visualizarVaga" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.Read(out, request);
            }

            case "listarVagas" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.All(out, request);
            }

            case "filtrarVagas" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.Filter(out, request);
            }

            case "atualizarVaga" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.Update(out, request);
            }

            case "apagarVaga" -> {
                if(isAuthenticatedCompany(out, request))
                    OpportunityController.Delete(out, request);
            }

            case "cadastrarCompetenciaExperiencia" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.CreateCompetences(out, request);
            }

            case "visualizarCompetenciaExperiencia" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.ReadCompetences(out, request);
            }

            case "atualizarCompetenciaExperiencia" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.UpdateCompetences(out, request);
            }

            case "apagarCompetenciaExperiencia" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.DeleteCompetences(out, request);
            }

            case "selecionarCandidato" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.Read(out, request);
            }

            case "filtrarCandidato" -> {
                if(isAuthenticatedCompany(out, request))
                    CandidateController.Read(out, request);
            }


            case "loginCandidato" -> AuthController.SignInCandidate(out, request);

            case "loginEmpresa" -> AuthController.SignInCompany(out, request);

            case "logout" -> AuthController.Logout(out, request);

        }
    }
}
