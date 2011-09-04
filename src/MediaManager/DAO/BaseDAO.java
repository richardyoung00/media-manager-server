package MediaManager.DAO;

import MediaManager.util.PropertyFetcher;
import cat.quickdb.db.AdminBase;


public abstract class BaseDAO {

    private PropertyFetcher properties = new PropertyFetcher();

    AdminBase dbAdmin;


    public BaseDAO() {

        dbAdmin = AdminBase.initialize(AdminBase.DATABASE.MYSQL,
                properties.getString("databaseServer"),
                properties.getString("databasePort"),
                properties.getString("databaseName"),
                properties.getString("databaseUser"),
                properties.getString("databasePassword"));
    }
}
