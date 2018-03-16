package server;

import java.io.*;
import java.net.Socket;

class ClientSession implements Session {
    private Socket socket;
    private PrintWriter out;
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
            try {
                this.out = out;
                while (true) {
                    String temp = in.readLine();

                    System.out.println(temp);

                    messageHandler.handleMsg(temp, out);
//                    messageHandler.handleMsg(in.readLine(), out);
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
