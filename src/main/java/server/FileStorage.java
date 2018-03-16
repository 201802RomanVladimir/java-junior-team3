package server;

import java.io.*;

public class FileStorage implements Storage {
    File file = new File("history.txt");
    @Override
    public void saveMessage(String s) {
        try(PrintWriter out = new PrintWriter(new FileOutputStream(file,true))) {
            out.println(s);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void outputHistory(PrintWriter out) {

        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = null;
            while ((line = in.readLine()) !=null) {
                out.println(line);
                out.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
