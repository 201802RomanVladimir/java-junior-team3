package server;

import java.util.Set;

class Sender {
    private Set<Session> sessionPool;

    Sender(Set<Session> sessionPool) {
        this.sessionPool = sessionPool;
    }

    public void handleNewMsg(String message) {
        for (Session elem : sessionPool) {
            elem.getOutputStream().println(message);
            elem.getOutputStream().flush();
        }
    }
}
