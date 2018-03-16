package server;

import helper.CommandHelper;
import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

    public synchronized void handleMsg(String message, PrintWriter out) throws IOException {
        String command = CommandHelper.TryParseCommand(message);
        if (command == null) return;

        switch (command) {
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
