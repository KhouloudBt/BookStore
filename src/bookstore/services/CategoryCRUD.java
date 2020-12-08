/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.Book;
import bookstore.entities.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author khoul
 */
public class CategoryCRUD {
    Connection cnx = MyConnection.getInstance().getCnx();

     
     //ADD_Book
     public void addCategory(Category cat){
        try {
            String request = "INSERT INTO category(id,name,description) VALUES"
                    +"('"+cat.getId()+"'"
                    +",'"+cat.getName()+"'"
                    +",'"+cat.getDescription()+"')";
            
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Category added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    public List<Category> listCategories(){
        List<Category> myList = new ArrayList<Category>();
        try {
            String request = "select * from category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
            Category cat = new Category();
            cat.setId(rs.getInt(1));
            cat.setName(rs.getString(2));
            cat.setDescription(rs.getString(3));
                     
            myList.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        //delete
    public void deleteCategory(Category cat){
        try {
            String request = "DELETE FROM category where id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, cat.getId());
           
            pst.executeUpdate();
            System.out.println("Category deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    
        public void UpdateCategory(Category cat,int id){
        try {
            String request="UPDATE category set name=?, description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, cat.getName());
            pst.setString(2, cat.getDescription());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("Category updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
        public ObservableList<String> ListNames()
        {
           return this.listCategories().stream()
                   .map(c->c.getName())
                   .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
    
        public boolean CategoryExists(String Name)
        {
            //Return True if the Category already exists
            List <Category> listCat = this.listCategories();
            return (listCat.stream().filter(c -> c.getName().equals(Name)).count()!=0);
        }
        
        public Category SearchByName(String name)
        {   List <Category> listCat = this.listCategories();

            return (listCat.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList()).get(0));
        }
        
        
        
    
}
