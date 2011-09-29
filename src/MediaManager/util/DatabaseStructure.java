package MediaManager.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: richard
 * Date: 2011/09/21
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseStructure {
    private static String [] tableNamesA  =
            {
                    "User",
                    "Role",
                    "UserRole"
            };





    public static ArrayList<String> tableNames = new ArrayList<String>(Arrays.asList(tableNamesA));

    public static String UserCreateStatement = "CREATE TABLE `User` (\n" +
            "\t`id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`username` VARCHAR(150) NOT NULL,\n" +
            "\t`password` VARCHAR(150) NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ")\n" +
            "COLLATE='latin1_swedish_ci'\n" +
            "ENGINE=InnoDB\n" +
            "ROW_FORMAT=DEFAULT\n" +
            "AUTO_INCREMENT=3";


}
