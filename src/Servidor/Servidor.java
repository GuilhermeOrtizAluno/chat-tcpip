package Servidor;

import Utils.Util;
import java.io.IOException;
import java.util.Scanner;

public class Servidor extends Thread{
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var defaultPort = 22222;

        Util.Println("Enter port number (current default: " + defaultPort + "):");
        var portInput = scanner.nextLine();
        int port = portInput.isEmpty() ? defaultPort : Integer.parseInt(portInput);

        try {
            var server = new Startup(port);
            server.Start();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
