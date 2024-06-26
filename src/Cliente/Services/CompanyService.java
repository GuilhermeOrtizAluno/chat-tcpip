package Cliente.Services;

import Infrastructure.Requests.CompanyRequest;
import Utils.Util;

import java.io.IOException;

public class CompanyService extends BaseService {
    public CompanyService(String host, int port) throws IOException {
        super(host, port);
    }

    public static String Read(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email a ser buscado:");
        var email = _scanner.nextLine();

        var request = new CompanyRequest();
        request.operacao = "visualizarEmpresa";
        request.token = token;
        request.email = email;

        return _gson.toJson(request);
    }

    public static String Create(){
        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        Util.Println("Digite CNPJ:");
        var cnpj = _scanner.nextLine();

        Util.Println("Digite Descricao:");
        var descricao = _scanner.nextLine();

        Util.Println("Digite Ramo:");
        var ramo = _scanner.nextLine();

        Util.Println("Digite Razao Social:");
        var razaoSocial = _scanner.nextLine();

        var request = new CompanyRequest();
        request.operacao = "cadastrarEmpresa";
        request.email = email;
        request.senha = senha;
        request.cnpj = cnpj;
        request.descricao = descricao;
        request.ramo = ramo;
        request.razaoSocial = razaoSocial;

        return _gson.toJson(request);
    }

    public static String Update(){
        Util.Println("Digite token:");
        var token = _scanner.nextLine();

        Util.Println("Digite email:");
        var email = _scanner.nextLine();

        Util.Println("Digite nome:");
        var nome = _scanner.nextLine();

        Util.Println("Digite senha:");
        var senha = _scanner.nextLine();

        Util.Println("Digite CNPJ:");
        var cnpj = _scanner.nextLine();

        Util.Println("Digite Descricao:");
        var descricao = _scanner.nextLine();

        Util.Println("Digite Ramo:");
        var ramo = _scanner.nextLine();

        Util.Println("Digite Razao Social:");
        var razaoSocial = _scanner.nextLine();

        var request = new CompanyRequest();
        request.operacao = "atualizarEmpresa";
        request.token = token;
        request.email = email;
        request.senha = senha;
        request.cnpj = cnpj;
        request.descricao = descricao;
        request.ramo = ramo;
        request.razaoSocial = razaoSocial;

        return _gson.toJson(request);
    }

    public static String Delete(){
        Util.Println("Digite email para ser deletado:");
        var email = _scanner.nextLine();

        var request = new CompanyRequest();
        request.operacao = "apagarEmpresa";
        request.email = email;

        return _gson.toJson(request);
    }
}
