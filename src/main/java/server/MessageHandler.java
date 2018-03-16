package server;

import helper.CommandHelper;

import java.io.PrintWriter;
import java.util.HashSet;

class MessageHandler {

    private Storage storage = new FileStorage();
    private HashSet<Session> sessionPool;
    private Sender sender;

    public MessageHandler(HashSet<Session> sessionPool) {
        this.sessionPool = sessionPool;
        sender = new BroadcastSender(sessionPool);
    }

    public synchronized void handleMsg(String message, PrintWriter out) {
        String command = CommandHelper.TryParseCommand(message);
        if (command == null){
            return;
        }

        switch (command) {
            case "/snd": {
                String formattedString = getFormattedString(message);
                storage.saveMessage(formattedString);
                sender.handleNewMsg(formattedString);
                break;
            }
            case "/hist": {
                storage.outputHistory(out);
                break;
            }
        }
    }

    private String getFormattedString(String message) {
        String[] parts = message.split(" ");
        StringBuilder formattedString = new StringBuilder(parts[parts.length-2]);
        formattedString.append(" ").append(parts[parts.length-1]).append(" ");
        for (int i = 1; i < parts.length-2; i++) {
            formattedString.append(parts[i]);
        }
        return formattedString.toString();
    }
}