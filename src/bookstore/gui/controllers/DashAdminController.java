 
package bookstore.gui.controllers;

import bookstore.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class DashAdminController implements Initializable {

    private Label lblUserName;

    /**
     * Initializes the controller class.
     */
      

    @FXML
    private JFXButton btMember;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton btHome;
    @FXML
    private JFXButton btBooks;
    @FXML
    private JFXButton btCategories;
    @FXML
    private JFXButton btOffers;
    @FXML
    private HBox top;
    @FXML
    private StackPane r;
    @FXML
    private ImageView logout;
    @FXML
    private JFXButton homePage;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            String currentUser = UserSession.getInstance().getUserName();
            Label b = new Label(currentUser);
            b.setTextFill(Color.web("#FFFFFF"));
            r.getChildren().add(b); 
            
    }  
    
    @FXML
    private void showUsers(MouseEvent event) throws IOException{
            Parent user = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
            borderPane.setCenter(user);
    }

    @FXML
    private void showBooks(MouseEvent event) throws IOException{
        Parent books = FXMLLoader.load(getClass().getResource("BooksList.fxml"));
        borderPane.setCenter(books);
    }

    @FXML
    private void showCategories(MouseEvent event) throws IOException{
        Parent categories = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
        borderPane.setCenter(categories);
    }

    @FXML
    private void showOffers(MouseEvent event) throws IOException{
        Parent offers = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
        borderPane.setCenter(offers);
    }

    @FXML
    private void showHome(MouseEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
        borderPane.setCenter(home);
    }

    @FXML
    private void logOut(MouseEvent event) throws IOException {
        UserSession.getInstance().cleanUserSession();
         Parent user = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        Parent user = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
           
    }
