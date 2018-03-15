package client;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientReciever implements Runnable {
    Socket socket;

    public ClientReciever(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(1);
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
