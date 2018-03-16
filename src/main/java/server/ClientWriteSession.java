package server;

import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import static java.lang.Thread.sleep;

class ClientWriteSession implements Runnable  {
    private ClientSession clientSession;
    private Message message;

    public ClientWriteSession(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        try (OutputStream outputStream = clientSession.getSocket().getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
            while(!Thread.interrupted()) {
                Message message = getMessage();
                if (message != null) {
                    out.writeObject(message);
                    setMessage(null);
                }

                //TODO need wait+notify
                sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
