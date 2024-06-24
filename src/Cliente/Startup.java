package Cliente;

import Cliente.Services.AuthService;
import Cliente.Services.CandidateService;
import Cliente.Services.CompanyService;
import Cliente.Services.OpportunityService;
import Utils.Util;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Startup {
    private final String _host;
    private final int _port;
    private final Scanner _scanner = new Scanner(System.in);

    public Startup(String host, int port) throws IOException {
        _host = host;
        _port = port;
    }

    public void Run(){
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/cliente", new LoginHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                handleOptionsRequest(exchange);
            } else if ("GET".equals(exchange.getRequestMethod())) {
                handleGetRequest(exchange);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                handlePostRequest(exchange);
            } else {
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

        private void handleOptionsRequest(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type");
            exchange.sendResponseHeaders(204, -1); // 204 No Content
        }

        private void handleGetRequest(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private void handlePostRequest(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            String response = "Hello, " + body + "!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public void Start() {

        var running = true;

        do  {
            Options();
            var menu = _scanner.nextInt();

            var json = "exit";
            switch (menu){
                case 0 ->  running = false;
                case 1 -> {
                    OptionsCandidate();

                    var choice = _scanner.nextInt();
                    switch (choice){
                        case 1 -> json = AuthService.SignInCandidate();
                        case 2 -> json = AuthService.Logout();
                        case 3 -> json = CandidateService.Read();
                        case 4 -> json = CandidateService.Create();
                        case 5 -> json = CandidateService.Update();
                        case 6 -> json = CandidateService.Delete();
                        case 7 -> json = CandidateService.ReadCompetency();
                        case 8 -> json = CandidateService.CreateCompetency();
                        case 9 -> json = CandidateService.UpdateCompetency();
                        case 10 -> json = CandidateService.DeleteCompetency();
                        default -> Util.Println("Opcao invalida:");
                    }
                }
                case 2 -> {
                    OptionsCompany();

                    var choice = _scanner.nextInt();
                    switch (choice){
                        case 1 -> json = AuthService.SignInCompany();
                        case 2 -> json = AuthService.Logout();
                        case 3 -> json = CompanyService.Read();
                        case 4 -> json = CompanyService.Create();
                        case 5 -> json = CompanyService.Update();
                        case 6 -> json = CompanyService.Delete();
                        case 7 -> json = OpportunityService.Read();
                        case 8 -> json = OpportunityService.Create();
                        case 9 -> json = OpportunityService.All();
                        case 10 -> json = OpportunityService.Filter();
                        case 11 -> json = OpportunityService.Update();
                        case 12 -> json = OpportunityService.Delete();
                        default -> Util.Println("Opcao invalida:");
                    }
                }
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

            Util.Println("\nAperte enter para continuar");
            _scanner.nextLine();
            _scanner.nextLine();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void Options(){
        Util.Println("0 - Encerrar");
        Util.Println("1 - Candidato");
        Util.Println("2 - Empresa");
        Util.Println("Escolha uma opcao:");
    }

    private void OptionsGeneric(){
        Util.Println("1 - Login");
        Util.Println("2 - Logout");
        Util.Println("3 - Visualizar");
        //Util.Println("4 - Listar");
        Util.Println("4 - Cadastrar");
        Util.Println("5 - Atualizar");
        Util.Println("6 - Apagar");
    }

    private void OptionsCandidate(){
        OptionsGeneric();
        Util.Println("7 - Visualizar Competencias");
        Util.Println("8 - Adicionar Competencias");
        Util.Println("9 - Editar Competencias");
        Util.Println("10 - Deletar Competencias");
        Util.Println("Escolha uma opcao:");
    }

    private void OptionsCompany(){
        OptionsGeneric();
        Util.Println("7 - Visualizar Vaga");
        Util.Println("8 - Listar Vaga");
        Util.Println("9 - Filtrar Vaga");
        Util.Println("10 - Adicionar Vaga");
        Util.Println("11 - Editar Vaga");
        Util.Println("12 - Deletar Vaga");
        Util.Println("12 - Selecionar candidato");
        Util.Println("9 - Filtrar candidato");
        Util.Println("Escolha uma opcao:");
    }
}
