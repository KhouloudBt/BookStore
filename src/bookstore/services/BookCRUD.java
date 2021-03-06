/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.Book;
import bookstore.entities.Category;
import bookstore.entities.Resource;
import bookstore.utilities.CustomAlert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author khoul
 */
public class BookCRUD {

    Connection cnx = MyConnection.getInstance().getCnx();
    CustomAlert alert = new CustomAlert();
    ResourceCRUD resource_crud = new ResourceCRUD();

    //ADD_Book
    public void addBook(Book book) {
        try {
            
            String request = "Insert into book (isbn, title, author, price, EditingHouse, cover, description) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());
            pst.setFloat(4, book.getPrice());
            pst.setString(5, book.getEditingHouse());
            pst.setString(6, book.getCover());
            pst.setString(7, book.getDesciption());
            pst.executeUpdate();
            System.out.println("Book Added");
      
            Statement st = cnx.createStatement();

            for (Category cat : book.getCategories()) {
                String request2 = "INSERT INTO book_category VALUES"
                        + "('" + book.getIsbn() + "'"
                        + ",'" + cat.getId() + "')";
                st.executeUpdate(request2);
            }
            
            for (Resource resource : book.getResourcesList())
            {
                try{
                   String request3 = "INSERT INTO resource VALUES"
                        + "('" + book.getIsbn() + "'"
                        + ",'" + resource.getPath() + "')";
                st.executeUpdate(request3);
                } catch(Exception ex)
                {
                    System.out.println("Error while adding resource"+ex.getMessage());
                }
            }
            
            
          
            System.out.println("categories added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            alert.showErrorAlert("Failure", "Error while adding the book"+ex.getMessage());
                
        }
    }

    public ArrayList<Book> listBooks() {
        ArrayList<Book> myList = new ArrayList<Book>();
        try {
            String request = "select * from book";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                Book b = new Book(); 
                System.out.println("HI");
                b.setIsbn(rs.getString(1));
                System.out.println(b.getIsbn());

                b.setTitle(rs.getString(2));

                b.setAuthor(rs.getString(3));

                b.setPrice(rs.getFloat(4));

                b.setNumberTimesBought(rs.getInt(5));

                b.setAverageRatings(rs.getFloat(6));

                b.setNbRatings(rs.getInt(7));
                b.setEditingHouse(rs.getString(8));
                b.setCover(rs.getString(10));
                b.setDesciption(rs.getString(11));
//                User owner= user_crud.searchById(rs.getInt(9));
//                                System.out.println("owner");                
//
//                b.setOwner(owner) ; 
                System.out.println(b);

                myList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Book> listBooksObservable() {
        return this.listBooks().stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    //delete

    public void deleteBook(Book b) {
        try {
            String request = "DELETE FROM book where isbn =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, b.getIsbn());

            pst.executeUpdate();
            System.out.println("Book deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateBook(Book b, int isbn) {
        try {
            String request = "UPDATE book set title=?,author=?, price=? WHERE isbn=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getAuthor());
            pst.setInt(3, isbn);
            pst.executeUpdate();
            System.out.println("Book updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean BookExits(String isbn) {
        List<Book> listBook = this.listBooks();
        return (listBook.stream().filter(c -> c.getIsbn() == isbn).count() != 0);

    }

    public ObservableList<Book> SearchByTitle(String title) {
        ArrayList<Book> l = this.listBooks();
        return l.stream().filter(p -> p.getTitle().equals(title))
                .collect(
                        Collectors
                                .toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Book> SearchByAuthor(String Author) {
        ArrayList<Book> l = this.listBooks();
        return l.stream().filter(p -> p.getAuthor().equals(Author)).collect(Collectors
                .toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Book> SearchByISBN(String isbn) {
        ArrayList<Book> l = this.listBooks();
        return l.stream().filter(p -> p.getIsbn() == isbn).collect(Collectors
                .toCollection(FXCollections::observableArrayList));
    }
}
