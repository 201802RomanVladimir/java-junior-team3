package server;

import helper.CommandHelper;
import java.io.IOException;
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

    public synchronized void handleMsg(String message, PrintWriter out) throws IOException {
        String command = CommandHelper.TryParseCommand(message);
        if (command == null) return;

        switch (command) {
            case ("/snd"): {
                String[] parts = message.split(" ");
                String modify = parts[parts.length-2] + " " + parts[parts.length-1] + "  ";
                for (int i = 1; i < parts.length-2; i++) {
                    modify += parts[i];
                }
                storage.saveMessage(modify);
                sender.handleNewMsg(modify);
                break;
            }
            case ("/hist"): {
                storage.outputHistory(out);
                break;
            }
            default: {
                break;
            }
        }
    }
}