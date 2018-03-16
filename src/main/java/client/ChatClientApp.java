package client;

import java.io.*;
import java.net.Socket;

public class ChatClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            Thread transmitterThread = new Thread(new ClientTransmitter(socket));
            transmitterThread.start();
            Thread recieverThread = new Thread(new ClientReciever(socket));
            recieverThread.start();
            while (true) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
