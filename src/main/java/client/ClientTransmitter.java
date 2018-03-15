package client;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTransmitter implements Runnable {
    Socket socket;

    public ClientTransmitter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(2);
        try (OutputStream outputStream = socket.getOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(outputStream)) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                out.writeObject(new Message(line));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
