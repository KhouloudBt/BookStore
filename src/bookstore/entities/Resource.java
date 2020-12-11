/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author khoul
 */
public class Resource {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private String path;

    public Resource() {
        this.id = count.incrementAndGet();

    }

    public Resource(String path) {
        this.id = count.incrementAndGet();
        this.path = path;
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
