package Servidor;

import Servidor.Controllers.Controller;
import Utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Startup {
    private final ServerSocket serverSocket;

    public Startup(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void Start() {
        try {
            var running = true;
            Util.Println("Aguardando conexão do cliente...");
            var socket = serverSocket.accept();
            Util.Println("Cliente conectado.");
            while (running) {


                var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var json = in.readLine();
                Util.Println("JSON recebido: \n" + json);

                Util.Println("\n");

                if (json.equals("exit")) {
                    running = false;
                } else {
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    Controller.Route(json, out);
                }

            }
            
            serverSocket.close();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
