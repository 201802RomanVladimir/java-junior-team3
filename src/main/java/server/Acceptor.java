package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor {
    public static void main(String[] args) {
        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                try (Socket clientSession = portListener.accept();
                     InputStream inputStream = clientSession.getInputStream();
                     ObjectInputStream in = new ObjectInputStream(inputStream)) {
                    try {
                        while (true) {
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
