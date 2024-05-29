package Cliente;

import Cliente.Services.CandidateService;
import Cliente.Services.CompanyService;
import Infrastructure.Requests.CompanyRequest;
import Servidor.Controllers.CandidateController;
import Utils.Util;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Startup {
    private final String _host;
    private final int _port;

    public Startup(String host, int port) throws IOException {
        _host = host;
        _port = port;
    }

    public void Start() {

        var scanner = new Scanner(System.in);
        var running = true;

        do  {
            Options();
            var choice = scanner.nextInt();

            var json = "exit";
            switch (choice){
                case 0 ->  running = false;
                case 1 -> json = CandidateService.Read();
                case 2 -> json = CandidateService.Create();
                case 3 -> json = CandidateService.Update();
                case 4 -> json = CandidateService.Delete();
                case 5 -> json = CandidateService.SignIn();
                case 6 -> json = CandidateService.Logou();
                case 7 -> json = CompanyService.Read();
                case 8 -> json = CompanyService.Create();
                case 9 -> json = CompanyService.Update();
                case 10 -> json = CompanyService.Delete();
                case 11 -> json = CompanyService.SignIn();
                case 12 -> json = CompanyService.Logou();
                default -> Util.Println("Opcao invalida:");
            }

            Send(json);

        } while (running);
    }

    private void Send(String json){
        try (var socket = new Socket(_host, _port)) {
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
            Util.Println("\nJSON enviado para o servidor." + json);

            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var resposta = in.readLine();
            Util.Println("\nResposta do servidor: " + resposta);

            Util.Println("\n");

        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void Options(){
        Util.Println("0 - Encerrar");
        Util.Println("1 - Ler Candidato");
        Util.Println("2 - Cadastrar Candidato");
        Util.Println("3 - Editar Candidato");
        Util.Println("4 - Deletar Candidato");
        Util.Println("5 - Login por Candidato");
        Util.Println("6 - Logout por Candidato");
        Util.Println("7 - Ler Empresa");
        Util.Println("8 - Cadastrar Empresa");
        Util.Println("9 - Editar Empresa");
        Util.Println("10 - Deletar Empresa");
        Util.Println("11 - Login por Empresa");
        Util.Println("12 - Logout por Empresa");
        Util.Println("Escolha uma opcao:");
    }
}
