package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StaticConnection {

    private static Connection connection = null;

    private StaticConnection() {}

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samadb?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("خطا در اتصال");
            e.printStackTrace();
        }
    }
    public static Connection getStaticConnection() {
        return connection;
    }
}
