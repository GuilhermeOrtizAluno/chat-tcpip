package Cliente;

import java.io.*;
import Utils.Util;

public class Cliente extends Thread {
    public static void main(String[] args) {
        var port = 22222;
        var host = "localhost";
        try {
            var client = new Startup(host, port);
            client.Start();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

