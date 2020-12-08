/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.MyConnection;
import bookstore.entities.Category;
import bookstore.services.CategoryCRUD;
import bookstore.services.RegexTests;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddBookController implements Initializable {

  @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField price;
    @FXML
    private TextField EditingHouse;
    @FXML
    private Button resource;
    @FXML
    private Button cover;
    @FXML
    private ChoiceBox<?> category;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryCRUD cr = new CategoryCRUD();
        category.setItems( cr.ListNames());
        
        
    }  
    
    private  final String isbn_txt = isbn.getText();
    private final String title_txt = title.getText();
    private final String author_txt = author.getText();
    private final String  price_txt= price.getText();
    private final String EditingHouse_txt= EditingHouse.getText();
   
    @FXML
    private void Save(ActionEvent event) {
        CategoryCRUD cr = new CategoryCRUD();
        RegexTests rgx = new RegexTests();

        if (!rgx.IsvalidIsbn(isbn_txt)) {
            System.out.println("Invalid Isbn!");

        } else if (cr.CategoryExists(Name.getText())) {
            System.out.println("Category already exists !");
        } else {
            MyConnection cnx = MyConnection.getInstance();
            Category cat = new Category(Name.getText(), description.getText().trim());
            cr.addCategory(cat);
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        Name.setText("");
        description.setText("");
    }
    
}
