package server;

import message.Message;

import java.io.IOException;
import java.util.HashSet;

public class Sender {
    private HashSet<ClientSession> sessionPool;

    public Sender(HashSet<ClientSession> sessionPool) {
        this.sessionPool = sessionPool;
    }


    public void handleNewMsg(Message message) throws IOException {
        System.out.println(sessionPool);
        for (ClientSession elem : sessionPool) {
            elem.getOutputStream().writeObject(message);
        }
    }
}
