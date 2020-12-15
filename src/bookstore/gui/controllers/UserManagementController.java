package bookstore.gui.controllers;

import bookstore.entities.User;
import bookstore.services.UserCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class UserManagementController implements Initializable {

     @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> cuserId;
    @FXML
    private TableColumn<User, String> cusername;
    @FXML
    private TableColumn<User, String> cuserfirstname;
    @FXML
    private TableColumn<User, String> cuserlastname;
    @FXML
    private TableColumn<User, String> cuseremail;
    @FXML
    private TableColumn<User, String> cuserephone;

    ObservableList<User> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initCol();
         loadData();
    }  
    
    private void initCol() {
        cuserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        cuserfirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cuserlastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cuserephone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cuseremail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn<User, User> deleteUser = new TableColumn<>("DeleteUser");
        deleteUser.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteUser.setCellFactory(param -> {
            return new TableCell<User, User>() {
                private final Button deleteButton = new Button("delete");
                //deleteButton.setBackground(Background.Color(green));
                @Override
                protected void updateItem(User user, boolean empty) {
                    super.updateItem(user, empty);
                    if (user == null) {
                        setGraphic(null);
                        return;
                    }
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> {
                        UserCRUD uc = new UserCRUD();
                        uc.deleteUser(user);
                        list.remove(user);
                                });
                }
            };
        });
          tableview.getColumns().addAll(deleteUser);
    }
   private void loadData() {
        list.clear();
        UserCRUD uc = new UserCRUD();
        list = uc.ObListUsers();
       //System.out.println(list);
        tableview.setItems(list);
    } 
   
   
    
}