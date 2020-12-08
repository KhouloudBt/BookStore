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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddCategoryController implements Initializable {

    private TextField Name;
    private TextArea description;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField price;

    @FXML
    private Button resource;
    @FXML
    private Button cover;
    @FXML
    private ChoiceBox<?> category;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Save(ActionEvent event) {
        CategoryCRUD cr = new CategoryCRUD();
        RegexTests rgx = new RegexTests();

        if (!rgx.isAvalidCategory(Name.getText())) {
            System.out.println("Invalid Name!");

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
