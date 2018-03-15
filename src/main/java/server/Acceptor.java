package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Acceptor {
    Executor sessionsThreadPool = Executors.newFixedThreadPool(1000);
    HashSet<ClientSession> sessionPool= new HashSet<>(1000);
    Sender sender = new Sender(sessionPool);
    public void start() {

        try (ServerSocket portListener = new ServerSocket(7779)) {
            while (true) { //Session loop
                ClientSession session = new ClientSession(portListener.accept(),sender);
                sessionsThreadPool.execute(session);
                sessionPool.add(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
