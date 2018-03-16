package server;

import java.io.PrintWriter;

interface Storage {
    void saveMessage(String s);
    void outputHistory(PrintWriter out);
}
