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

    Storage storage = new FileStorage();
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
                String[] parts = message.split(" ");
                String modify = parts[parts.length-2] + " " + parts[parts.length-1] + "  ";
                for (int i = 1; i < parts.length-2; i++) {
                    modify += parts[i];
                }
                storage.saveMessage(modify);
                sender.handleNewMsg(modify);
            }
            case ("/hist"): {
                storage.outputHistory(out);
            }
            default: {

            }
        }

    }

}
