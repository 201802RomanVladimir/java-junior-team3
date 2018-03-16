package server;

import java.util.HashSet;

class Sender {
    private HashSet<Session> sessionPool;

    public Sender(HashSet<Session> sessionPool) {
        this.sessionPool = sessionPool;
    }

    public void handleNewMsg(String message) {
        for (Session elem : sessionPool) {
            elem.getOutputStream().println(message);
            elem.getOutputStream().flush();
        }
    }

}
