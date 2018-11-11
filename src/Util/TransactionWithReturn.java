package Util;

import java.sql.SQLException;

public interface TransactionWithReturn<T> {
    T run() throws SQLException;
}
