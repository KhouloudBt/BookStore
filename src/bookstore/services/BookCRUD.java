/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.Book;
import bookstore.entities.Category;
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
 * @author khoul
 */
public class BookCRUD {   
      
     Connection cnx = MyConnection.getInstance().getCnx();

     
     //ADD_Book
     public void addBook(Book book){
        try {
            String request = "INSERT INTO book(isbn,title,author,price,EditingHouse) VALUES"
                    +"('"+book.getIsbn()+"'"
                    +",'"+book.getTitle()+"'"
                    +",'"+book.getAuthor()+"'"
                    +",'"+book.getPrice()+"'"
                    +",'"+book.getEditingHouse()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(request);

            for (Category cat: book.getCategories())
            {
                String request2 = "INSERT INTO book_category() VALUES"
                    +"('"+book.getIsbn()+"'"
                    +",'"+cat.getId()+"')";
                st.executeUpdate(request2);
            }
            System.out.println("Book added !");
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    public ArrayList<Book> listBooks(){
        ArrayList<Book> myList = new ArrayList<Book>();
        try {
            String request = "select * from book";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
            Book b = new Book();
            b.setIsbn(rs.getInt(1));
            b.setTitle(rs.getString(2));
            b.setAuthor(rs.getString(3));
            b.setPrice(rs.getFloat(4));
            b.setNumberTimesBought(rs.getInt(5));
            b.setAverageRatings(rs.getFloat(6));
            b.setNbRatings(rs.getInt(7));
            b.setEditingHouse(rs.getString(8));
            b.setOwner((User)rs.getObject(9));           
            myList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        //delete
    public void deleteBook(Book b){
        try {
            String request = "DELETE FROM book where isbn =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, b.getIsbn());
           
            pst.executeUpdate();
            System.out.println("Book deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    
        public void UpdateBook(Book b,int isbn){
        try {
            String request="UPDATE book set title=?,author=?, price=? WHERE isbn=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getAuthor());
            pst.setInt(3, isbn);
            pst.executeUpdate();
            System.out.println("Book updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
        
        public boolean BookExits(int isbn)
        {
             List <Book> listBook = this.listBooks();
            return (listBook.stream().filter(c -> c.getIsbn()==isbn).count()!=0);
            
        }
        public List<Book> SearchByTitle(String title)
        {
            ArrayList <Book> l = this.listBooks();
           return  l.stream().filter(p->p.getTitle().equals(title)).collect(Collectors.toList());
        }
         public List<Book> SearchByAuthor(String Author)
        {
            ArrayList <Book> l = this.listBooks();
           return  l.stream().filter(p->p.getAuthor().equals(Author)).collect(Collectors.toList());
        }
         public List<Book> SearchByISBN(int isbn)
        {
            ArrayList <Book> l = this.listBooks();
           return  l.stream().filter(p->p.getIsbn()==isbn).collect(Collectors.toList());
        }
}
