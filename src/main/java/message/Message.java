package message;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Message implements Serializable {

    private String message;
    private Date date = new Date();

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  date + " " + message  ;
    }
}
