package client;

import java.io.IOException;
import java.net.Socket;

class ServerConnector implements Runnable {
    @Override
    public void run() {
        //TODO порт -  в настройки
        try (Socket serverSocket = new Socket("localhost",7777)) {

            Thread outputThread = new Thread(new ServerWriteSession(serverSocket));
            outputThread.start();

            Thread inputThread = new Thread(new ServerReadSession(serverSocket));
            inputThread.start();

            while (!Thread.interrupted()) {
                ; //TODO
            }

            outputThread.interrupt();
            inputThread.interrupt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
