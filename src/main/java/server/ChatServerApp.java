package server;

import static java.lang.Runtime.getRuntime;

public class ChatServerApp {
    public static void main(String[] args) {
        Thread connectionLoop = new Thread(new ServerAcceptor());
        connectionLoop.start();

        //TODO выяснить способ закрытия сервера
        getRuntime().addShutdownHook(
                new Thread(connectionLoop::interrupt)
        );
    }
}
