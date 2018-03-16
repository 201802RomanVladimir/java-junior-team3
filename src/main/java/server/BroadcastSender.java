package server;

import java.util.HashSet;

class BroadcastSender implements Sender {
    private HashSet<Session> sessionPool;

    public BroadcastSender(HashSet<Session> sessionPool) {
        this.sessionPool = sessionPool;
    }

    @Override
    public void handleNewMsg(String message) {
        for (Session elem : sessionPool) {
            elem.getOutputStream().println(message);
            elem.getOutputStream().flush();
        }
    }

}
