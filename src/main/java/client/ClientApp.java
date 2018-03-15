package client;

import message.Message;

import java.io.*;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            try (OutputStream outputStream = socket.getOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(outputStream);
                 InputStream inputStream = socket.getInputStream();
                 ObjectInputStream in = new ObjectInputStream(inputStream)){
                 out.writeObject(new Message("что то"));
                 System.out.println(in.readObject());
//                out.writeObject(new IntMessage(7));
                //out.flush();
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
