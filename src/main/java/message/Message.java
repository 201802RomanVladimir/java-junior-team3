package message;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {



    private String command;
    private String message = "";
    private Date date;

    public Message(String text) {
        parse(text);
        date = new Date();
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return date + " " + message;
    }

    private void parse(String text) {
        String[] parsedText = text.split(" ");
        command = parsedText[0];
        for (int i = 1; i < parsedText.length; i++) {
            message = message + " " + parsedText[i];
        }
    }
}
