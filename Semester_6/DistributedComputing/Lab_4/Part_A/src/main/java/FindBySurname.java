import java.io.*;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;

public class FindBySurname implements Runnable {
    private Random random;
    private File file;

    private ReadWriteLock rwLock;

    FindBySurname(String fName, ReadWriteLock rwLock) {
        random = new Random();
        file = new File(fName);

        this.rwLock = rwLock;
    }

    @Override
    public void run() {
        int pos;
        String surname, line;
        boolean flag = false;
        while (!Thread.currentThread().isInterrupted()) {
            rwLock.readLock().lock();

            pos = random.nextInt(Constants.surnames.size());
            surname = Constants.surnames.get(pos);
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    String[] str = line.split(" ");
                    if (str[0].equals(surname)) {
                        System.out.println("FindBySurname thread found person with surname: " + str[0] + " " + str[1] + " " + str[2]);
                        flag = true;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!flag) System.out.println("FindBySurname thread. Not found: " + surname);
            rwLock.readLock().unlock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}