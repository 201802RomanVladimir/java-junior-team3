package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7779)) {
            try (OutputStream outputStream = socket.getOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
//                out.writeObject(new IntMessage(7));
                //out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
