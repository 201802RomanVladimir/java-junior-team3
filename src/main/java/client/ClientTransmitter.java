package client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class ClientTransmitter implements Transmitter {
    private Socket socket;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");
    private InputMessageFilter inputMessageFilter = new InputMessageFilter();

    ClientTransmitter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
             PrintWriter printWriter = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()))){
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String line = scanner.nextLine();
                if(inputMessageFilter.isCorrect(line)){
                    printWriter.println(line + " [" + dateFormat.format(new Date()) + "]");
                    printWriter.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
