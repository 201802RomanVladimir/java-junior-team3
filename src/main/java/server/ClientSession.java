package server;

import message.Message;

import java.io.*;
import java.net.Socket;

public class ClientSession implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private MessageHandler messageHandler;

    public ClientSession(Socket newSocket, MessageHandler messageHandler) throws IOException {
        socket = newSocket;
        out = new ObjectOutputStream(socket.getOutputStream());
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             ObjectInputStream in = new ObjectInputStream(inputStream)) {
            try {
                while (true) {
                    Message message = (Message) in.readObject();
                    messageHandler.handleMsg(message,out);
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
