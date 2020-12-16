package bookstore.gui.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import bookstore.gui.controllers.UpdateBookController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class TestController implements Initializable {

    /**
     * Initializes the controller class.
     */UpdateBookController update;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         update = new UpdateBookController();
         
                
        // TODO
    }    
    
}
