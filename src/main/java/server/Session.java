package server;

import java.io.PrintWriter;

public interface Session extends Runnable{
    PrintWriter getOutputStream();
}
