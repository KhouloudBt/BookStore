/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoul
 */
public class Book {

    private String isbn;
    private String title;
    private String Author;
    private float price = 0.0f;    
    private int numberTimesBought=0;
    private double averageRatings=0.0f;
    private int nbRatings=0;
    private String EditingHouse;
    private int id_owner;
    private String cover;
    private String desciption;

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    private List<Category> categories= new ArrayList<Category>();
    private List<Resource> resourcesList = new ArrayList<Resource>();

    public Book() {
    }

    public Book(String isbn,float price, String title, String Author, String EditingHouse,List <Category> categories,List<Resource> resourcesList, String cover, String description) {
        this.isbn = isbn;
        this.price=price;
        this.title = title;
        this.Author = Author;
        this.EditingHouse = EditingHouse;
        this.cover = cover;
        this.resourcesList=resourcesList;
        this.categories=categories;
        this.desciption= description;
    }
       public Book(String isbn,float price, String title, String Author, String EditingHouse,List <Category> categories,List<Resource> resourcesList, String cover, String description, int id_owner) {
        this.isbn = isbn;
        this.price=price;
        this.title = title;
        this.Author = Author;
        this.EditingHouse = EditingHouse;
        this.cover = cover;
        this.resourcesList=resourcesList;
        this.categories=categories;
        this.desciption= description;
        this.id_owner=id_owner;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumberTimesBought() {
        return numberTimesBought;
    }

    public void setNumberTimesBought(int numberTimesBought) {
        this.numberTimesBought = numberTimesBought;
    }

    public double getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(double averageRatings) {
        this.averageRatings = averageRatings;
    }

    public int getNbRatings() {
        return nbRatings;
    }

    public void setNbRatings(int nbRatings) {
        this.nbRatings = nbRatings;
    }

    public String getEditingHouse() {
        return EditingHouse;
    }

    public void setEditingHouse(String EditingHouse) {
        this.EditingHouse = EditingHouse;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

   
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Resource> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<Resource> resourcesList) {
        this.resourcesList = resourcesList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.isbn != other.isbn) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", Author=" + Author + ", price=" + price + ", numberTimesBought=" + numberTimesBought + ", averageRatings=" + averageRatings + ", nbRatings=" + nbRatings + ", EditingHouse=" + EditingHouse + ", owner=" + id_owner + ", cover=" + cover + ", desciption=" + desciption + ", categories=" + categories + ", resourcesList=" + resourcesList + '}';
    }

}
