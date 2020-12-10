/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.Book;
import bookstore.entities.Cart;
import bookstore.temp.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PORTGASDACE
 */
public class CartCRUD {
       Connection cnx = MyConnection.getInstance().getCnx();

     
     //ADD_Book
     public void newCart(){
        try {
            String req = "INSERT INTO CART(qte,cartWorth) VALUES(0,0) )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Cart added !");
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    public void addBook(Book b , Cart c){
        List<Book> myList = c.getBooks();
        try {
            String req = "insert into cart_book Values"
                     +"('"+b.getIsbn()+"'"
                     +",'"+c.getId()+"')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //add in cart
            myList.add(b);
            c.setBooks(myList);
            System.out.println("Book added !");
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        //delete cart
    public void deleteCart(Cart c){
        try {
            String request = "DELETE FROM Cart where id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setLong(1, c.getId());
           
            pst.executeUpdate();
            System.out.println("Cart deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
           //delete book
    public void deleteBook(Book b , Cart c){
        try {
            String request = "DELETE FROM cart_book  where "
                    +"id_book='"+b.getIsbn()+"'"
                     +"and id_cart='"+c.getId()+"'";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.executeUpdate();
            System.out.println("Book deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
               //Empty cart
    public void emptyBook(Cart c){
        try {
            String request = "DELETE FROM cart_book  where "
                     +"and id_cart='"+c.getId()+"'";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.executeUpdate();
            System.out.println("cart empty !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       
}
