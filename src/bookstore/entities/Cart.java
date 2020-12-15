/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

import bookstore.entities.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PORTGASDACE
 */
public class Cart {
    private long id;
    private List<Book> books = new ArrayList<Book>();
    private int qte=0;
    private float cartWorth=0;

    public Cart() {
    }

    public long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getQte() {
        return qte;
    }

    public float getCartWorth() {
        return cartWorth;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setCartWorth(float cartWorth) {
        this.cartWorth = cartWorth;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", books=" + books + ", qte=" + qte + ", cartWorth=" + cartWorth + '}';
    }
    
}
