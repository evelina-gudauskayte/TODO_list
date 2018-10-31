package Util;

import java.io.PrintStream;

public class Logger{
    public static void measureTime(TransactionWithException transaction, PrintStream stream){
        stream.println("Start of transaction");
        try{
            double startTime = System.nanoTime();
            transaction.run();
            System.out.println("Time: " + (System.nanoTime() - startTime) / 1e9);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
