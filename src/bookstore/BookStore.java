/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.entities.Book;
import bookstore.entities.Category;
import bookstore.services.BookCRUD;
import bookstore.services.CategoryCRUD;
import bookstore.utilities.RegexTests;

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
//        MyConnection cn = MyConnection.getInstance();
//        Book bk = new Book(67889,"Khouloud","Rami",5.f,"hello");
//        BookCRUD br= new BookCRUD();
//        br.addBook(bk);
//        Category cat= new Category("Romance","For romantic books");
//        CategoryCRUD catcr= new  rgxCategoryCRUD();
//        catcr.addCategory(cat);
    RegexTests rgx= new RegexTests();
        System.out.println(rgx.containsOnlyLettersAndSpaces("Sience fiction"));

    }
    
}
