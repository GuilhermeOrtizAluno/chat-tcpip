package Servidor;

import Utils.Util;
import java.io.IOException;

public class Servidor extends Thread{
    public static void main(String[] args) {
        var port = 22222;
        try {
            var server = new Startup(port);
            server.Start();
        } catch (IOException e) {
            Util.PrintError("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
