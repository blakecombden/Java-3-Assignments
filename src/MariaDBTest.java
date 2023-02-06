import java.sql.*;

public class MariaDBTest {

    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3308/javatest";

    //Database credentials
    static final String DB_USER = "root";
    static final String DB_PASS = "RykeDrask36!!";
    public static void main(String[] args) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            System.out.println("Connected database successfully...");

            conn.close();

            System.out.println("It worked!");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}