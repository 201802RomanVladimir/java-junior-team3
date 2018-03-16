package server;

import java.io.*;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ClientAcceptor implements Acceptor {
    private Executor sessionsThreadPool = Executors.newFixedThreadPool(1000);
    private HashSet<Session> sessionPool= new HashSet<>(1000);
    private MessageHandler messageHandler = new MessageHandler(sessionPool);

    public void start() {
        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                Session session = new ClientSession(portListener.accept(), messageHandler);
                sessionPool.add(session);
                sessionsThreadPool.execute(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
