package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

class ServerReadSession implements Runnable {
    private Socket socket;

    ServerReadSession(Socket socket) {
        this.socket = socket;
    }

    Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = getSocket().getInputStream();
             ObjectInputStream in = new ObjectInputStream(inputStream)) {
            while(!Thread.interrupted()) {
                System.out.println(in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
