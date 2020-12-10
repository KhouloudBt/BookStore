/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import javafx.stage.Window;

/**
 *
 * @author khoul
 */
public class CustomAlert {

    public CustomAlert() {
    }
    
    
    
    
    public void showAlert(javafx.scene.control.Alert.AlertType alertType,String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
}
