package MediaManager.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Common methods for the database
 */
public class DatabaseUtils {
    private Connection connection;


    public DatabaseUtils(Connection connection){
        this.connection = connection;
    }

    public void recreateDatabase(){

    }

    public void rewriteTables(){

    }
}
