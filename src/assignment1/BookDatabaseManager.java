package assignment1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains all database queries and methods to retrieve data
 * Establish connection with books database to retrieve all books and authors
 *
 * @author blake
 */
public class BookDatabaseManager {

    /** Build library from database
     *
     * @return library
     */

    public static Library loadLibrary(){
        Library library = new Library();
        try{
            Connection connection = DBConfig.getConnection();

            // load in the book and author lists
            library.setBookList(loadBookList(connection));
            library.setAuthorList(loadAuthorList(connection));

            // build relationships between tables
            buildRelationships(connection, library.getBookList(), library.getAuthorList());

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return library;
    }

    /**
     * Load all books into a List (without relationships)
     * @return List of all books
     */
    private static List<Book> loadBookList(Connection connection){
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        try{
            // create query string and execute
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfig.DB_BOOKS_TITLES_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            // create book objects using result set and add to list
            while(resultSet.next()){
                bookLinkedList.add(
                        new Book(
                                resultSet.getString(DBConfig.DB_BOOKS_TITLES_ISBN),
                                resultSet.getString(DBConfig.DB_BOOKS_TITLES_TITLE),
                                resultSet.getInt(DBConfig.DB_BOOKS_TITLES_EDITION_NUMBER),
                                resultSet.getString(DBConfig.DB_BOOKS_TITLES_COPYRIGHT))
                );
            }

            statement.close();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return bookLinkedList;
    }

    /**
     * Load all authors into List without relationships
     * @return List of all authors
     */
    private static List<Author> loadAuthorList(Connection connection){
        LinkedList<Author> authorLinkedList = new LinkedList<>();
        try{
            // create query string and execute
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfig.DB_BOOKS_AUTHORS_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            // create author objects using result set and add to list
            while(resultSet.next()){
                authorLinkedList.add(
                        new Author(
                                resultSet.getInt(DBConfig.DB_BOOKS_AUTHORS_AUTHOR_ID),
                                resultSet.getString(DBConfig.DB_BOOKS_AUTHORS_FIRST_NAME),
                                resultSet.getString(DBConfig.DB_BOOKS_AUTHORS_LAST_NAME))
                );
            }

            statement.close();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return authorLinkedList;
    }

    /**
     * Build the relationships between the objects passed using the bridge table
     * @param connection
     * @param bookList
     * @param authorList
     */
    private static void buildRelationships(Connection connection, List<Book> bookList, List<Author> authorList){
        //TODO write this method: query the bridge table, query the two object lists that you pass in, and connect.
        try{
            // create query string and execute
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfig.DB_BOOKS_AUTHORS_ISBN_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                // get IDs
                int authorID = resultSet.getInt(DBConfig.DB_BOOKS_AUTHORS_AUTHOR_ID);
                String isbn = resultSet.getString(DBConfig.DB_BOOKS_TITLES_ISBN);

                // find objects in lists using filters
                Author author = authorList.stream().filter(a -> a.getAuthorID() == authorID).findAny().get();
                Book book = bookList.stream().filter(b -> b.getIsbn().equals(isbn)).findAny().get();

                // link objects
                author.addBook(book);
                book.addAuthor(author);
            }

            statement.close();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public static void saveBook(Book book, Author author){
        try{
            Connection connection = DBConfig.getConnection();

            // initialize query and preparedstatement variables
            String sql = "";
            PreparedStatement preparedStatement = null;

            // code INSERT INTO statement for titles table and corresponding indexes
            sql = "INSERT INTO titles (isbn, title, editionNumber, copyright) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());

            // execute statement
            preparedStatement.executeUpdate();

            // code INSERT INTO statement for authorISBN table and corresponding indexes
            sql = "INSERT INTO authorISBN (authorID, isbn) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, author.getAuthorID());
            preparedStatement.setString(2, book.getIsbn());

            // execute statement
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public static void saveAuthor(Author author){
        try{
            Connection connection = DBConfig.getConnection();

            // initialize query and preparedstatement variables
            String sql = "";
            PreparedStatement preparedStatement = null;

            // code insert into statement for authors table and corresponding indexes
            sql = "INSERT INTO authors (firstName, lastName) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            //preparedStatement.setString(1, DBConfig.DB_BOOKS_AUTHORS_TABLE_NAME);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());

            // execute statement
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
