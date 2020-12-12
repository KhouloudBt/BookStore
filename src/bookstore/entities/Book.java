/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoul
 */
public class Book {

    private int isbn;
    private String title;
    private String Author;
    private float price = 0.0f;    
    private int numberTimesBought=0;
    private float averageRatings=0.0f;
    private int nbRatings=0;
    private String EditingHouse;
    private User owner;
    private BufferedImage cover;
    private List<Category> categories= new ArrayList<Category>();
    private List<Resource> ResourcesList = new ArrayList<Resource>();

    public Book() {
    }

    public Book(int isbn, String Author, List<Resource> ResourcesList) {
        this.isbn = isbn;
        this.Author = Author;
        this.ResourcesList = ResourcesList;
    }

    public Book(int isbn, String title, String Author, float price, String EditingHouse) {
        this.isbn = isbn;
        this.title = title;
        this.Author = Author;
        this.price = price;
        this.EditingHouse = EditingHouse;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Resource> getResourcesList() {
        return ResourcesList;
    }

    public void setResourcesList(List<Resource> ResourcesList) {
        this.ResourcesList = ResourcesList;
    }

    public int getNumberTimesBought() {
        return numberTimesBought;
    }

    public void setNumberTimesBought(int numberTimesBought) {
        this.numberTimesBought = numberTimesBought;
    }

    public float getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(float averageRatings) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BufferedImage getCover() {
        return cover;
    }

    public void setCover(BufferedImage cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return String.format("isbn : %10s",isbn) + String.format("| title : %s\t",title) + String.format("| Author : %s\t",Author)  +String.format("| price : %s\t",price)
                + String.format("| average Ratings : %s\t",averageRatings)+String.format("| Editing House : %s\t |",EditingHouse);
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

}
