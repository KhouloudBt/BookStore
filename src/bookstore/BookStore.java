/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.entities.Book;
import bookstore.services.BookCRUD;

/**
 *
 * @author khoul
 */
public class BookStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        MyConnection cn = MyConnection.getInstance();
        Book bk = new Book(5423,"Khouloud","Rami",5.f,"hello");
        BookCRUD br= new BookCRUD();
        br.addBook(bk);
        

    }
    
}
