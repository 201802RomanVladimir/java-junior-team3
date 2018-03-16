package server;

import message.Message;

import java.util.HashMap;
import java.util.Map;

class ClientSessionStorage {

    //TODO синглтон
    public static ClientSessionStorage instance = new ClientSessionStorage();

    private Map<Integer, ClientSession> clientSessions = new HashMap();

    public void add(Integer id, ClientSession clientSessionInfo) {
        clientSessions.put(id, clientSessionInfo);
    }

    public ClientSession get(Integer id) {
        return clientSessions.get(id);
    }

    public int size() {
        return clientSessions.size();
    }

    public void sendAll(Message message) {
        clientSessions.forEach((k, v) -> v.send(message));
    }
}
