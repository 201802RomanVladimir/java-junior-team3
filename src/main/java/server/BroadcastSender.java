package server;

import java.util.Set;

class BroadcastSender implements Sender {
    private Set<Session> sessionPool;

    public BroadcastSender(Set<Session> sessionPool) {
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
