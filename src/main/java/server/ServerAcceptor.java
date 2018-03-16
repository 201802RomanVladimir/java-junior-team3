package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class ServerAcceptor implements Runnable {

    private Map<Integer, Thread> clientSessionThreads = new HashMap();

    @Override
    public void run() {
        //TODO порт -  в настройки
        try (ServerSocket serverSocket = new ServerSocket(7777)) {

            //TODO SoTimeout -  в настройки
            //serverSocket.setSoTimeout(1000);

            while (!Thread.interrupted()) {

                Socket clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    Integer clientId = ClientSessionStorage.instance.size();
                    ClientSessionStorage.instance.add(clientId, new ClientSession(clientSocket));

                    ClientSession currentSessionInfo = ClientSessionStorage.instance.get(clientId);

                    Thread writeThread = new Thread(currentSessionInfo.getClientWriteSession());
                    clientSessionThreads.put(clientId, writeThread);
                    writeThread.start();

                    Thread readThread = new Thread(currentSessionInfo.getClientReadSession());
                    clientSessionThreads.put(clientId, readThread);
                    readThread.start();

                    //TODO for debug
                    System.out.println(String.format("Client %d connected", clientId));
                }
            }

            clientSessionThreads.forEach((k, v) -> v.interrupt());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
