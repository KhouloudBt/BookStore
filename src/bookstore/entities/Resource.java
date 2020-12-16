/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author khoul
 */
public class Resource {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private String path;
    private String id_book;

    public String getId_book() {
        return id_book;
    }

    public void setId_book(String id_book) {
        this.id_book = id_book;
    }



    public Resource() {
        this.id = count.incrementAndGet();

    }

    public Resource(String path) {
        this.id = count.incrementAndGet();
        this.path = path;
    }

    public Resource(String path, String id_book) {
        this.path = path;
        this.id_book = id_book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

 
    
    

}
