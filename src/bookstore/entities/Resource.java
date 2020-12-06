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

    private File file;

    public Resource() {
        this.id = count.incrementAndGet();

    }

    public Resource(int id, File file) {
        this.id = count.incrementAndGet();
        this.file = file;
    }

}
