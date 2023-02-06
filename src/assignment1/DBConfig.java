package assignment1;

import java.sql.*;

/**
 * Stores database parameters and registers DB driver.
 *
 * @author blake
 */

public class DBConfig {

    // JDBC driver name and database URL
    protected static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mariadb://localhost:3308";

    // database credentials
    protected static final String DB_USER = "root";
    protected static final String DB_PASSWORD = "RykeDrask36!!";

    // database name
    protected static final String DB_BOOKS = "/books";

    // titles table name and attributes
    protected static final String DB_BOOKS_TITLES_TABLE_NAME = "titles";
    protected static final String DB_BOOKS_TITLES_ISBN = "isbn";
    protected static final String DB_BOOKS_TITLES_TITLE = "title";
    protected static final String DB_BOOKS_TITLES_EDITION_NUMBER = "editionNumber";
    protected static final String DB_BOOKS_TITLES_COPYRIGHT = "copyright";

    // authors table name and attributes
    protected static final String DB_BOOKS_AUTHORS_TABLE_NAME = "authors";
    protected static final String DB_BOOKS_AUTHORS_AUTHOR_ID = "authorID";
    protected static final String DB_BOOKS_AUTHORS_FIRST_NAME = "firstName";
    protected static final String DB_BOOKS_AUTHORS_LAST_NAME = "lastName";

    // authorsISBN table name and attributes
    protected static final String DB_BOOKS_AUTHORS_ISBN_TABLE_NAME = "authorISBN";
    protected static final String DB_BOOKS_AUTHORS_ISBN_AUTHOR_ID = "authorID";
    protected static final String DB_BOOKS_AUTHORS_ISBN_ISBN = "isbn";

    /**
     * Option 1:
     * Register driver using Class.forName()
     * Dynamically loads driver's class file into memory and registers it.
     */
    public static void doClassForNameRegistration(){
        try {
            Class.forName(DBConfig.JDBC_DRIVER);
            System.out.println("DB driver registered using Class.forName()");
        }
        catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException: unable to load driver class.");
            System.err.println("Driver name: " + DBConfig.JDBC_DRIVER);
            System.exit(1);
        }
    }

    /**
     * Option 2:
     * Register driver using registerDriver()
     */
    public static void doRegisterDriverMethodRegistration(){
        try {
            Driver myDriver = new org.mariadb.jdbc.Driver();
            DriverManager.registerDriver( myDriver );
            System.out.println("DB driver registered using registerDriver()");
        }
        catch (SQLException e) {
            System.err.println("SQLException: trying to register MariaDB driver.");
            e.printStackTrace();
        }
    }

    /**
     * Generate a connection to the books database
     * @return connection
     */
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    DBConfig.DB_URL + DBConfig.DB_BOOKS,
                    DBConfig.DB_USER,
                    DBConfig.DB_PASSWORD);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }
}
