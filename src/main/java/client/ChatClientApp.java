package client;

import java.io.*;
import java.net.Socket;

import static java.lang.Runtime.getRuntime;

/***
 * Клиент чата
 */
public class ChatClientApp {
    private ChatClientApp() {
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            createTransmitter(socket);
            createReciever(socket);
            while (true) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createReciever(Socket socket) {
        Reciever reciever = new ClientReciever(socket);
        Thread recieverThread = new Thread(reciever);
        recieverThread.start();
    }

    private static void createTransmitter(Socket socket) {
        Transmitter transmitter = new ClientTransmitter(socket);
        Thread transmitterThread = new Thread(transmitter);
        transmitterThread.start();
    }
}
