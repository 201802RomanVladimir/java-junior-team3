package client;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientTransmitter implements Runnable {

    private Socket socket;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");
    private InputMessageFilter inputMessageFilter;


    public ClientTransmitter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
             PrintWriter printWriter = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()))
        ){

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter you nickname: ");
            String userName = "/chid " + scanner.nextLine();

            System.out.print("Choose chatroom: ");
            String room = "/chroom " + scanner.nextLine();

            while (true) {
                String line = scanner.nextLine();
                if(inputMessageFilter.isCorrect(line)){
                    printWriter.println(line+" "+ dateFormat.format(new Date()));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
