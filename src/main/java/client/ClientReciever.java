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
        try (InputStream inputStream = socket.getInputStream();
             ObjectInputStream in = new ObjectInputStream(inputStream)) {

            while (true) {
                System.out.println(in.readObject());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
