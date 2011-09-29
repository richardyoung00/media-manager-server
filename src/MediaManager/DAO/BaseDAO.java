package MediaManager.DAO;

import MediaManager.util.DatabaseUtils;
import MediaManager.util.PropertyFetcher;
import cat.quickdb.db.AdminBase;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import java.sql.*;


public abstract class BaseDAO {

    private PropertyFetcher properties = new PropertyFetcher();


    Connection con = null;

    public BaseDAO() {

        String url = "jdbc:mysql://"+properties.getString("databaseServer")+":"+properties.getString("databasePort")+"/"+properties.getString("databaseName");
        String user = properties.getString("databaseUser");
        String password = properties.getString("databasePassword");
        try{
           Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){

        }

        try {
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true);
            DatabaseUtils dbUtils = new DatabaseUtils(con);

        } catch (SQLException e) {
            if (e.getSQLState().equals("XJ004")){
                //TODO: Database doesn't exist - recreate
            }
            System.out.println(e.getMessage());
        }


    }
}
