package client;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            ClientTransmitter clientTransmitter = new ClientTransmitter(socket);
            ClientReciever clientReciever = new ClientReciever(socket);
            Thread transmitterThread = new Thread(clientTransmitter);
            transmitterThread.start();
            Thread recieverThread = new Thread(clientReciever);
            recieverThread.start();
            while(true){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
