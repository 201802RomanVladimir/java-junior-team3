package server;

import message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor {
    public static void main(String[] args) {
        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                try (Socket clientSession = portListener.accept();
                     InputStream inputStream = clientSession.getInputStream();
                     ObjectInputStream in = new ObjectInputStream(inputStream);
                     OutputStream outputStream = clientSession.getOutputStream();
                     ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
                    try {
                        while (true) {
                            Object message = in.readObject();
                            System.out.println(message);
                            out.writeObject(message);
                            //in.readObject();
                           // loggerController.log((Message) in.readObject(), new PrefixFormatVisitor());
                        }
                    }
                    catch (EOFException e) {
                        //   loggerController.flush();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
