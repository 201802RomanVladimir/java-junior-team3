package client;

import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class ServerWriteSession implements Runnable {
    private Socket socket;

    ServerWriteSession(Socket socket) {
        this.socket = socket;
    }

    Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try (OutputStream outputStream = getSocket().getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
            while(!Thread.interrupted()) {
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                out.writeObject(new Message(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
