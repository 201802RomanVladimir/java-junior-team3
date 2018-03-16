package server;

import message.Message;

import java.io.*;
import java.net.Socket;

public class ClientSession implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private MessageHandler messageHandler;

    public ClientSession(Socket newSocket, MessageHandler messageHandler) throws IOException {
        socket = newSocket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             BufferedReader in =  new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            try {
                this.out = out;
                while (true) {
                    messageHandler.handleMsg(in.readLine(), out);
                }
            } catch (EOFException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public PrintWriter getOutputStream() {
        return out;
    }
}
