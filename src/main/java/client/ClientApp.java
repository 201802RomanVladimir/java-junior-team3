package client;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            try (OutputStream outputStream = socket.getOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(outputStream);
                 InputStream inputStream = socket.getInputStream();
                 ObjectInputStream in = new ObjectInputStream(inputStream)){

                while(true) {
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    out.writeObject(new Message(line));

                    System.out.println(in.readObject());
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
