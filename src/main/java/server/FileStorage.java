package server;

import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class FileStorage implements Storage {

    private File file = new File("history.txt");
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void saveMessage(String s) {
        lock.writeLock().lock();
        try(PrintWriter out = new PrintWriter(new FileOutputStream(file,true))) {
            out.println(s);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void outputHistory(PrintWriter out, Integer pageNumber) throws IOException {
        lock.readLock().lock();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            for (int i = 0; i < (pageNumber-1)*100; i++) {
                line = in.readLine();
                if (line == null) {
                    out.println("Page not found");
                    out.flush();
                    return;
                }
            }
            for (int i = 0; i < 100 ; i++) {
                if ((line = in.readLine()) != null) {
                    out.println(line);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}
