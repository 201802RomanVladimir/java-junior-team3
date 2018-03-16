package server;

import java.io.IOException;
import java.io.PrintWriter;

interface Storage {
    void saveMessage(String s);
    void outputHistory(PrintWriter out, Integer integer) throws IOException;
}
