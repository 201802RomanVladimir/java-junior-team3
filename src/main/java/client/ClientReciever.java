package client;


import java.io.*;
import java.net.Socket;

class ClientReciever implements Reciever {
    private Socket socket;

    ClientReciever(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
              {
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            System.out.println("Server is down!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}