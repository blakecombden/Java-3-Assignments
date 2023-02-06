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
        Connection connection = DBConfig.getConnection();
        // Load in the book and author lists
        library.setBookList(loadBookList(connection));
        library.setAuthorList(loadAuthorList(connection));

        buildRelationships(connection, library.getBookList(), library.getAuthorList());

        return library;
    }

    /**
     * Load all books into a List (without relationships)
     * @return List of all books
     */
    private static List<Book> loadBookList(Connection connection){
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        try{
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

    //TODO perhaps make this private and call it in the previous methods

    /**
     * Build the relationships between the objects passed using the bridge table
     * @param connection
     * @param bookList
     * @param authorList
     */
    private static void buildRelationships(Connection connection, List<Book> bookList, List<Author> authorList){
        //TODO write this method: query the bridge table, query the two object lists that you pass in, and connect.
        try{
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

    //TODO write the unload / save version of these methods

    //TODO write the private query methods
}
