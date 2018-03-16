package server;

/***
 * Сервер чата
 */
public class ChatServerApp {
    private ChatServerApp() {
    }

    public static void main(String[] args) {
        Acceptor acceptor = new ClientAcceptor();
        acceptor.start();
    }
}
