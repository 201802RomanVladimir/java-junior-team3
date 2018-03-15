package server;

import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;

public class Sender {
    private HashSet<ClientSession> sessionPool;

    public Sender(HashSet<ClientSession> sessionPool) {
        this.sessionPool = sessionPool;
    }

    public void handleNewMsg(Message message) {
        for (ClientSession elem : sessionPool) {
            try {
                elem.getOutputStream().writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
