package Cliente.Services;

import Utils.Util;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BaseService {
    protected static final Gson _gson = new Gson();
    protected static final Scanner _scanner = new Scanner(System.in);

    private final String _host;
    private final int _port;

    public BaseService(String host, int port) throws IOException {
        _host = host;
        _port = port;
    }

    protected void Send(String json){
        try (var socket = new Socket(_host, _port)) {
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
            Util.Println("\nJSON enviado para o servidor." + json);

            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var resposta = in.readLine();
            Util.Println("\nResposta do servidor: " + resposta);

        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
