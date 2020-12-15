/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.utilities.CustomAlert;
import bookstore.utilities.RegexTests;
import bookstore.services.RegisterService;
import bookstore.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TextField tflastName;
    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfcpassword;
    @FXML
    private Button btSave;
    @FXML
    private TextField tfusername;
    @FXML
    private Label flogin;
    @FXML
    private TextField id_user;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
      
    }    

    @FXML
    private void signIn(ActionEvent event)throws IOException {
        Window owner =btSave.getScene().getWindow();

        User u = new User();
        if (tfname.getText().isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your name");
            return;
        }
        if (tflastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your las name");
            return;
        }
        if (tfphone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your phone");
            return;
        }
        if (tfmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Mail Error!",
                "Please enter your mail");
            return;
        }
        if (tfpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                "Please enter your password");
            return;
        }
        if (tfcpassword.getText().isEmpty() ) {
            
            showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                "Please confirm your password!");
            return;
        }
        String p = tfpassword.getText();
        String pc = tfcpassword.getText();
        if (p.matches(pc) == false){
            showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                "Please check your password!");
            return;
        }
//        if (!RegexTests.isValidMail(tfmail.getText()));
//        {CustomAlert.showErrorAlert("Email error", " invalid error");
//            
//        }
        u.setFirstName(tfname.getText());
        u.setId(Integer.parseInt(id_user.getText()));
        u.setLastName(tflastName.getText());
        u.setUsername(tfusername.getText());
        u.setPhone(tfphone.getText());
        u.setEmail(tfmail.getText());
        u.setPassword(tfpassword.getText());
        RegisterService rc = new RegisterService();
        rc.insertUser(u);
       
        Parent user = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void goLogin(MouseEvent event) throws IOException{
        Parent log = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(log);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    
    
}
