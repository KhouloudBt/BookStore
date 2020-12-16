package bookstore.gui.controllers;

import bookstore.entities.User;
import bookstore.services.RegisterService;
import bookstore.utilities.CustomAlert;
import bookstore.utilities.RegexTests;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if (tfname.getText().isEmpty() || tfphone.getText().isEmpty()
                || tfmail.getText().isEmpty()
                || tfpassword.getText().isEmpty()
                || tfcpassword.getText().isEmpty()) {
            CustomAlert.showErrorAlert("Data errors!","Please fill all fields");
        }
        
        else {
        String p = tfpassword.getText();
        String pc = tfcpassword.getText();
        if (p.matches(pc) == false){
        CustomAlert.showErrorAlert("Passwords error", "Passwords don't match");
           }
//        else if (RegexTests.isValidPassword(tfpassword.getText())==false)
//        {
//            CustomAlert.showErrorAlert("Error","Invalid password" );
//        }
        else if (RegexTests.isAvalidPhone(tfphone.getText())==false)
                {
                 CustomAlert.showErrorAlert("Error","Invalid phone" );

                }
        else if (tfusername.getText().length()<4)
        {
         CustomAlert.showErrorAlert("Error","Username should contain at least 4 characters" );
        }
        else{
            boolean logged=true;
       try{
        u.setFirstName(tfname.getText());
        u.setLastName(tflastName.getText());
        u.setUsername(tfusername.getText());
        u.setPhone(tfphone.getText());
        u.setEmail(tfmail.getText());
        u.setPassword(tfpassword.getText());
        RegisterService rc = new RegisterService();
        rc.insertUser(u);
        CustomAlert.showInformationAlert("Succes","User added successfully!");

         } catch (Exception ex)
            {logged=false;
                CustomAlert.showErrorAlert("Failure", "Error while adding the user"+ex.getMessage());
            }
       if (logged==true){
        Parent user = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();}
           
        }
        
    }
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