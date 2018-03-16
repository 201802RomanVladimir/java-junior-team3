package client;

import static java.lang.Runtime.getRuntime;

public class ChatClientApp {
    public static void main(String[] args) {
        Thread clientLoop = new Thread(new ServerConnector());
        clientLoop.start();

        //TODO выяснить способ закрытия сервера
        getRuntime().addShutdownHook(
                new Thread(clientLoop::interrupt)
        );
    }
}
