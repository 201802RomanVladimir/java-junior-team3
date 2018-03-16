package server;

import message.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

class ClientReadSession implements Runnable  {
    private ClientSession clientSession;

    ClientReadSession(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSession.getSocket().getInputStream();
             ObjectInputStream in = new ObjectInputStream(inputStream)) {
            while(!Thread.interrupted()) {
                Message message = (Message)in.readObject();
                //TODO parse command, save to storage, notify writer ClientSessionStorage.instance
                //System.out.println(message);
                ClientSessionStorage.instance.sendAll(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
