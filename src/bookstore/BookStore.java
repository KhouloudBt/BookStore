/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.services.CategoryCRUD;

/**
 *
 * @author khoul
 */
public class BookStore {

    /**
     * @param args the command line arguments
     */
    public static final String CURRENCY = "TND";

    public static void main(String[] args) {

        CategoryCRUD vr = new CategoryCRUD();
        System.out.println(vr.ListByBook("4569871236548"));
        

    }
    
}
