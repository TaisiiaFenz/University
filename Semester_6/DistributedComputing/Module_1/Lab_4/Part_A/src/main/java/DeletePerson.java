import java.io.*;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;

public class DeletePerson extends Thread {
    ReadWriteLock rwLock;

    private Random random;
    private File file;
    private File tmpFile;

    DeletePerson(String fName, ReadWriteLock rwLock) {
        random = new Random();
        file = new File(fName);
        tmpFile = new File(fName + ".tmp");

        this.rwLock = rwLock;
    }

    @Override
    public void run() {
        int pos;
        String surname, line;
        boolean flag = false;
        while (!Thread.currentThread().isInterrupted()) {
            rwLock.writeLock().lock();

            pos = random.nextInt(Constants.surnames.size());
            surname = Constants.surnames.get(pos);
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter(tmpFile));
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    String[] str = line.split(" ");
                    if (!str[1].equals(surname)) {
                        printWriter.println(line);
                    } else {
                        flag = true;
                    }
                }

                printWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (flag) {
                if (!file.delete()) System.out.println("Can't delete file");
                if (!tmpFile.renameTo(file)) System.out.println("Can't rename file");
                System.out.println("DeletePerson thread. Removed: " + surname);
            } else {
                if (!tmpFile.delete()) System.out.println("Can't delete file");
                System.out.println("DeletePerson thread. Not found: " + surname);
            }

            rwLock.writeLock().unlock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
