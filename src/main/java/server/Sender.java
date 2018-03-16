package server;

import message.Message;

import java.io.IOException;
import java.util.HashSet;

public class Sender {
    private HashSet<ClientSession> sessionPool;

    public Sender(HashSet<ClientSession> sessionPool) {
        this.sessionPool = sessionPool;
    }


    public void handleNewMsg(String message) throws IOException {
        for (ClientSession elem : sessionPool) {
            elem.getOutputStream().println(message);
        }
    }
}
