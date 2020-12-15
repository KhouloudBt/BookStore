/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.MyConnection;
import bookstore.entities.Category;
import bookstore.services.CategoryCRUD;
import bookstore.utilities.CustomAlert;
import bookstore.utilities.RegexTests;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextArea description;
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
            CustomAlert.showErrorAlert("Inpur error", "Ivalid category");
        } else if (cr.CategoryExists(Name.getText())) {
            CustomAlert.showErrorAlert("Input Error", "Category already exists");
        } else {
            try {
                MyConnection cnx = MyConnection.getInstance();
                Category cat = new Category(Name.getText(), description.getText().trim());
                cr.addCategory(cat);
                CustomAlert.showInformationAlert("Succes", "Category added succefully!");
                 this.clear();

            } catch (Exception ex) {
                CustomAlert.showErrorAlert("Error", "Error while adding category: " + ex.getMessage());

            }
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    private void clear()
    {
        Name.clear();
        description.clear();
    }


}
