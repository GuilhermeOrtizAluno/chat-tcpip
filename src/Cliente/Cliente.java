package Cliente;

import java.io.*;
import java.util.Scanner;

import Utils.Util;

public class Cliente extends Thread {
    public static void main(String[] args) {
        var defaultPort = 22222;
        var defaultHost = "localhost";

        var scanner = new Scanner(System.in);

        Util.Println("Enter port number (current default: " + defaultPort + "):");
        var portInput = scanner.nextLine();
        int port = portInput.isEmpty() ? defaultPort : Integer.parseInt(portInput);

        Util.Println("Enter host (current default: " + defaultHost + "):");
        var host = scanner.nextLine();
        host = host.isEmpty() ? defaultHost : host;

        try {
            var client = new Startup(host, port);
            client.Start();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

