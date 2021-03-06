package server;

import java.io.*;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ClientAcceptor implements Acceptor {
    private Executor sessionsThreadPool = Executors.newFixedThreadPool(1000);
    private Set<Session> sessionPool = new HashSet<>(1000);
    private MessageHandler messageHandler = MessageHandler.getInstance(sessionPool);

    @Override
    public void start() {
        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                Session session = new ClientSession(portListener.accept(), messageHandler);
                synchronized (sessionPool) {
                    if (sessionPool.add(session)) {
                        sessionsThreadPool.execute(session);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
