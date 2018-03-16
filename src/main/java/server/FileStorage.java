package server;

import java.io.*;

public class FileStorage implements Storage {
    File file = new File("history.txt");
    @Override
    public void saveMessage() {
        try(PrintWriter out = new PrintWriter(new FileOutputStream(file,true))) {
            out.write();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void outputMessage() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
