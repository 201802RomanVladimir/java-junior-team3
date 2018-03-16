package server;

import message.Message;

import java.net.Socket;

class ClientSession {
    private Socket socket;
    private ClientReadSession clientReadSession;
    private ClientWriteSession clientWriteSession;

    ClientSession(Socket socket) {
        this.socket = socket;
        this.clientWriteSession = new ClientWriteSession(this);
        this.clientReadSession = new ClientReadSession(this);
    }

    public Socket getSocket() {
        return socket;
    }

    public ClientReadSession getClientReadSession() {
        return clientReadSession;
    }

    public ClientWriteSession getClientWriteSession() {
        return clientWriteSession;
    }

    public void send(Message message) {
        getClientWriteSession().setMessage(message);
    }
}
