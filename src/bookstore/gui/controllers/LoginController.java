package bookstore.gui.controllers;

import bookstore.UserSession;
import bookstore.services.LoginService;
import bookstore.utilities.CustomAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfcpassword;
    @FXML
    private Label tsignup;
    @FXML
    private Button btLog;
    @FXML
    private TabPane tabPaneLogin;
    @FXML
    private Tab tabadmin;
    @FXML
    private Tab tabuser;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblStatus;
    @FXML
    private JFXPasswordField pwdUser;
    @FXML
    private JFXTextField usernameUser;
    @FXML
    private Button LoginUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goSignUp(MouseEvent event) throws IOException{
        Parent log = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene scene = new Scene(log);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException{
        Window owner =btLog.getScene().getWindow();
        LoginService logService = new LoginService();
        //admin
        String adminname = tfusername.getText();
        String adminpass = tfpassword.getText();
        String c_password = tfcpassword.getText();
        if (adminpass.matches(c_password) == false){
            showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                "Please check your password!");
            return;
        }
        //user
        String nameUser = usernameUser.getText();
        String pwduser = pwdUser.getText();

        LoginService lc  = new LoginService();
        if(lc.authenticate(adminname, adminpass)){
            Parent log = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));
            Scene scene = new Scene(log);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            UserSession.getInstance();
            UserSession.getInstance(adminname);
        }
        else if((logService.authority(nameUser).equals("Role_MEMBER_PREMIUM")) && lc.authenticate(nameUser, pwduser)){
            Parent log = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(log);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            UserSession.getInstance().cleanUserSession();
            UserSession.getInstance();
            UserSession.getInstance(nameUser);
            System.out.println(UserSession.getInstance().getUserName());}
        else {
            CustomAlert.showWarningAlert("check infos", "You are not allowed to login! you may want to create an account first");
        }
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
    private void openAdminTab(MouseEvent event) {
           TranslateTransition toLeftAnimation= new TranslateTransition(new Duration(300),lblStatus);
         toLeftAnimation.setToX(slidingPane.getTranslateX());
         toLeftAnimation.play();
         toLeftAnimation.setOnFinished((ActionEvent event2)->{
            lblStatus.setText("ADMINISTRATOR");
        });
         tabPaneLogin.getSelectionModel().select(tabadmin);
    }

    @FXML
    private void openUserTab(MouseEvent event) {
         //Using translation animation
        TranslateTransition toRightAnimation= new TranslateTransition(new Duration(300),lblStatus);
        toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1)->{
            lblStatus.setText("USER");
        });
        //set selected tab as USER
        tabPaneLogin.getSelectionModel().select(tabuser);
    }
    
}