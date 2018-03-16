package server;

import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MessageHandler {

    private List<Message> historyList = new ArrayList<>();
    private HashSet<ClientSession> sessionPool;
    Sender sender;

    public MessageHandler(HashSet<ClientSession> sessionPool) {
        this.sessionPool = sessionPool;
        sender = new Sender(sessionPool);
    }

    public synchronized void handleMsg(Message message, ObjectOutputStream out) throws IOException {
        switch (message.getCommand()) {
            case ("/snd"): {
                historyList.add(message);
                sender.handleNewMsg(message);
                break;
            }
            case ("/hist"): {
                for (Message elem : historyList) {
                    out.writeObject(elem);
                }
                break;
            }

        }

    }
}
