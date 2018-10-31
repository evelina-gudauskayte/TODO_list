package Util;

import java.sql.SQLException;

public interface TransactionWithException<T> {
    void run() throws SQLException;
}
