/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddBookController implements Initializable {

    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField price;
    @FXML
    private TextField title1;
    @FXML
    private TextField category;
    @FXML
    private Button resource;
    @FXML
    private Button cover;



    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
