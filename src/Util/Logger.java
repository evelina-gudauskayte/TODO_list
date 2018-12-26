package Util;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
            alert(message);
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
            alert(message);
        }
        return null;
    }
    private void alert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message+" ERROR");
        alert.showAndWait();
    }

}
