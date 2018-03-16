package server;


import java.io.*;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Acceptor {
    private Executor sessionsThreadPool = Executors.newFixedThreadPool(1000);
    private HashSet<ClientSession> sessionPool= new HashSet<>(1000);
    private MessageHandler messageHandler = new MessageHandler(sessionPool);
    public void start() {
        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                ClientSession session = new ClientSession(portListener.accept(),messageHandler);
                sessionsThreadPool.execute(session);
                sessionPool.add(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
