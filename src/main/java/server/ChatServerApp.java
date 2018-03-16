package server;

public class ChatServerApp {
    public static void main(String[] args) {
        Acceptor acceptor = new ClientAcceptor();
        acceptor.start();
    }
}
