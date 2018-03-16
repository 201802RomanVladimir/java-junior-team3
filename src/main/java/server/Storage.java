package server;

import java.io.PrintWriter;

public interface Storage {
    void saveMessage(String s);
    void outputHistory(PrintWriter out);
}
