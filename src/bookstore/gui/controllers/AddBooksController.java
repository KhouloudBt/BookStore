/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.entities.Category;
import bookstore.services.BookCRUD;
import bookstore.services.CategoryCRUD;
import bookstore.services.RegexTests;
import bookstore.services.CustomAlert;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddBooksController implements Initializable {

    @FXML
    private TextField isbn_txt;
    @FXML
    private TextField title_txt;
    @FXML
    private TextField author_txt;
    @FXML
    private TextField price_txt;
    @FXML
    private TextField editingHouse_txt;
    @FXML
    private Button btn_resources;
    @FXML
    private Button btn_resources1;
    @FXML
    private Button btn_save;

    RegexTests rgx = new RegexTests();
    CustomAlert alert = new CustomAlert();
    CategoryCRUD category_crud= new CategoryCRUD();
    BookCRUD book_crud= new BookCRUD();
    @FXML
    private CheckComboBox<String> category_ch;
    private List<Category>categories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryCRUD cr = new CategoryCRUD();
        ObservableList<String> obs = cr.ListNames();
        category_ch.getItems().addAll(obs);
        

    }

    @FXML
    private void resourceChoser(ActionEvent event) {

    }

    @FXML
    private void CoverChoser(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
        ObservableList<String> checked_categories_name = category_ch.getCheckModel().getCheckedItems();
        if (isbn_txt.getText().isEmpty()
                || author_txt.getText().isEmpty()
                || price_txt.getText().isEmpty()
                || editingHouse_txt.getText().isEmpty()
                || price_txt.getText().isEmpty()
                || checked_categories_name.isEmpty()) {
            alert.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all the fields");
        }
        
        if (!(rgx.IsvalidIsbn(isbn_txt.getText())
                && rgx.isValidPrice(price_txt.getText())
                && rgx.containsOnlyLettersAndSpaces(editingHouse_txt.getText())
                &&rgx.containsOnlyLettersAndSpaces(author_txt.getText()))) {
            alert.showAlert(Alert.AlertType.ERROR, "Form Error!", "Invalid data");
            return;
        }
        categories = new ArrayList<Category>();
        for (Object cat_name : checked_categories_name) {
            categories.add(category_crud.SearchByName(cat_name.toString()));
        }
        
    }



}
