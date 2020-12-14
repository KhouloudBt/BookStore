/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import com.jfoenix.controls.JFXButton;
import bookstore.UserSession;
import bookstore.services.UserCRUD;
import bookstore.entities.User;
import java.io.IOException;
import java.net.URL;
import javafx.scene.layout.BorderPane;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class AdminDashController implements Initializable {

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
        Parent books = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
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
}
