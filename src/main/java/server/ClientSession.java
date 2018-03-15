package server;

import message.Message;

import java.io.*;
import java.net.Socket;

public class ClientSession implements Runnable {
    Socket socket;
    Sender sender;
    ObjectOutputStream out;

    public ClientSession(Socket newSocket, Sender sender) throws IOException {
        socket = newSocket;
        this.sender = sender;
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(out);
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             ObjectInputStream in = new ObjectInputStream(inputStream)) {
            try {
                while (true) {
                    Message message = (Message) in.readObject();
                    sender.handleNewMsg(message);
                }
            } catch (EOFException e) {
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOutputStream() {
        return out;
    }
}
