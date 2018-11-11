package Util;

import java.io.OutputStream;
import java.io.PrintStream;

public class Logger {
    private static Logger logger = new Logger();
    private PrintStream stream = System.out;

    private Logger() {
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }

    public static Logger getInstance() {
        return logger;
    }

    public void log(TransactionWithException transaction, String message) {
        stream.println("Start of transaction");
        try {
            double startTime = System.nanoTime();
            transaction.run();
            stream.println(message);
            stream.println("Time: " + (System.nanoTime() - startTime) / 1e9);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T logWithReturn(TransactionWithReturn<T> transaction, String message) {
        stream.println("Start of transaction");
        try {
            double startTime = System.nanoTime();
            T object = transaction.run();
            stream.println(message);
            stream.println("Time: " + (System.nanoTime() - startTime) / 1e9);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
