package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn;
    try {
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=" + "db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
      //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");//
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                      "user=root&password=");
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list");
      Groups groups = new Groups();
      while (resultSet.next()){
        groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));
      }
      resultSet.close();
      statement.close();
      conn.close();
      System.out.println(groups);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
