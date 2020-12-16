/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Category;
import bookstore.services.CategoryCRUD;
import bookstore.utilities.CustomAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class CategoriesListController implements Initializable {

    @FXML
    private TableView<Category> tableview;
    ObservableList<Category> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Category, String> nom;
    @FXML
    private TableColumn<Category, String> description;
    @FXML
    private TableColumn<Category, String> id;
    @FXML
    private JFXButton btAdd;
    @FXML
    private JFXTextField search;
    CategoryCRUD cat = new CategoryCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Category, Category> deleteCat = new TableColumn<>("DeleteCategory");
        TableColumn<Category, Category> updateCat = new TableColumn<>("UpdateCategory");

        deleteCat.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCat.setCellFactory(param -> {
            return new TableCell<Category, Category>() {
                private final Button deleteButton = new Button("delete");

                @Override
                protected void updateItem(Category cat, boolean empty) {
                    super.updateItem(cat, empty);
                    if (cat == null) {
                        setGraphic(null);
                        return;
                    }
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> {
                        CustomAlert.showConfirmationAlert("Confirm Delete", "The category"+cat.getName()+"is going to be deleted");
                        try{
                        CategoryCRUD uc = new CategoryCRUD();
                        uc.deleteCategory(cat);
                        list.remove(cat);
                        }catch(Exception ex)
                        {
                            CustomAlert.showInformationAlert("Delete failed", "Can't delete category ");
                        }
                    });
                }
            };
        });
        deleteCat.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCat.setCellFactory(param -> {
            return new TableCell<Category, Category>() {
                private final Button updateButton = new Button("update");

                @Override
                protected void updateItem(Category cat, boolean empty) {
                    super.updateItem(cat, empty);
                    if (cat == null) {
                        setGraphic(null);
                        return;
                    }
                    setGraphic(updateButton);
                    updateButton.setOnAction(event -> {
                        try{
                        CategoryCRUD uc = new CategoryCRUD();
                        uc.UpdateCategory(cat);
                        }catch(Exception ex)
                        {
                            CustomAlert.showInformationAlert("Update failed", "Can't delete category ");
                        }
                    });
                }
            };
        });
                tableview.getColumns().addAll(updateCat);

        tableview.getColumns().addAll(deleteCat);
    }

    private void loadData() {
        list.clear();
        list = cat.listCategoryObservable();
        tableview.setItems(list);
    }

    @FXML
    private void Search(ActionEvent event) {

        ObservableList<Category> cat_list = cat.SearchByNameObservable(search.getText());
        list = cat_list;
        loadData();
    }

    @FXML
    private void AddCategory(ActionEvent event) throws IOException {
        Parent log = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        Scene scene = new Scene(log);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
