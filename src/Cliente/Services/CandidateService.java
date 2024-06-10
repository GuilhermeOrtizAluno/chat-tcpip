package Cliente.Services;

import Infrastructure.Requests.CandidateCompetenciesRequest;
import Infrastructure.Requests.CandidateCompetencyRequest;
import Infrastructure.Requests.CandidateRequest;
import Utils.Util;

import java.util.ArrayList;

public class CandidateService extends BaseService {
    public static String Read() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email a ser buscado:");
        var email = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "visualizarCandidato";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String Create() {
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite nome:");
        var nome = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "cadastrarCandidato";
        request.email = email;
        request.nome = nome;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Update() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite nome:");
        var nome = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "atualizarCandidato";
        request.token = token;
        request.email = email;
        request.nome = nome;
        request.senha = senha;

        return _gson.toJson(request);
    }

    public static String Delete() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email para ser deletado:");
        var email = _scanner.nextLine();

        var request = new CandidateRequest();
        request.operacao = "apagarCandidato";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String ReadCompetency() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new CandidateCompetenciesRequest();
        request.operacao = "visualizarCompetenciaExperiencia";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String CreateCompetency() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new CandidateCompetenciesRequest();
        request.operacao = "cadastrarCompetenciaExperiencia";
        request.token = token;
        request.email = email;
        request.competenciaExperiencia = new ArrayList<>() {};

        var isCompetencies = true;
        do {
            Util.Println("Competencias:");
            Util.Println("0 - Continuar");
            Util.Println("1 - Adicionar");
            var choice = _scanner.nextInt();
            _scanner.nextLine();
            if(choice == 0){
                isCompetencies = false;
            }
            else if(choice == 1){
                Util.Println("Digite a Competencia:");
                var competency = _scanner.nextLine();

                Util.Println("Digite quantos anos possui de experiencia:");
                var experience = _scanner.nextInt();

                request.competenciaExperiencia.add(new CandidateCompetencyRequest (competency, experience));
            }
            else Util.Println("Opcao invalida");

        }while (isCompetencies);

        return _gson.toJson(request);
    }

    public static String UpdateCompetency() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new CandidateCompetenciesRequest();
        request.operacao = "atualizarCompetenciaExperiencia";
        request.token = token;
        request.email = email;
        request.competenciaExperiencia = new ArrayList<>() {};

        var isCompetencies = true;
        do {
            Util.Println("Competencias:");
            Util.Println("0 - Continuar");
            Util.Println("1 - Adicionar");
            var choice = _scanner.nextInt();
            if(choice == 0){
                isCompetencies = false;
            }
            else if(choice == 1){
                Util.Println("Digite a Competencia:");
                var competency = _scanner.nextLine();

                Util.Println("Digite quantos anos possui de experiencia:");
                var experience = _scanner.nextInt();

                request.competenciaExperiencia.add(new CandidateCompetencyRequest (competency, experience));
            }
            else Util.Println("Opcao invalida");

        }while (isCompetencies);

        return _gson.toJson(request);
    }

    public static String DeleteCompetency() {
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        var request = new CandidateCompetenciesRequest();
        request.operacao = "apagarCompetenciaExperiencia";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }
}
