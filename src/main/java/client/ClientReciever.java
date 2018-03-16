package client;


import java.io.*;
import java.net.Socket;

public class ClientReciever implements Runnable {
    Socket socket;

    public ClientReciever(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
              {

            while (true) {
                System.out.println(in.readLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
