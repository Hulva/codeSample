/**
 * 
 */
package hulva.luva.learn.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月13日
 *
 */
public class CreateDbDemo {
  private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    String dbUrl = null;
    if (args.length > 0) {
      dbUrl = "jdbc:hive://" + args[0];
    } else {
      dbUrl = "jdbc:hive://localhost:10000/default";
    }
    // Register driver and create driver instance
    Class.forName(driverName);
    // get connection
    Connection con = DriverManager.getConnection(dbUrl, "", "");
    Statement stmt = con.createStatement();

    stmt.executeQuery("CREATE DATABASE userdb");
    System.out.println("Database userdb created successfully.");

    con.close();
  }
}
