package server;

import java.io.*;
import java.net.Socket;

class ClientSession implements Session {
    private Socket socket;
    private PrintWriter writer;
    private MessageHandler messageHandler;

    ClientSession(Socket newSocket, MessageHandler messageHandler) {
        socket = newSocket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             BufferedReader in =  new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            writer = out;
            while (true) {
                String temp = in.readLine();
                System.out.println(temp);
                messageHandler.handleMsg(temp, out);
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

    @Override
    public PrintWriter getOutputStream() {
        return writer;
    }
}
