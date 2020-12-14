/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.entities.Category;
import bookstore.entities.Resource;
import bookstore.services.BookCRUD;
import bookstore.services.CategoryCRUD;
import bookstore.services.ResourceCRUD;
import bookstore.utilities.CustomAlert;
import bookstore.utilities.RegexTests;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddBooksController implements Initializable {

    @FXML
    private TextField isbn_txt;
    @FXML
    private TextField title_txt;
    @FXML
    private TextField author_txt;
    @FXML
    private TextField price_txt;
    @FXML
    private TextField editingHouse_txt;
    @FXML
    private Button btn_resources;
    @FXML
    private Button btn_resources1;
    @FXML
    private Button btn_save;
    @FXML
    private Label cover_path;
    @FXML
    private CheckComboBox<String> category_ch;
    List<String> resourceExtensions;
    List<Category> checked_categories;
    List<String> coverExtensions;
    List<Resource> resources_list;
    String cover_path_txt;
    RegexTests rgx = new RegexTests();
    CustomAlert alert = new CustomAlert();
    CategoryCRUD category_crud = new CategoryCRUD();
    BookCRUD book_crud = new BookCRUD();
    ResourceCRUD resource_crud = new ResourceCRUD();

    private List<Category> categories;
    @FXML
    private Button cancel_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cover_path_txt = "";
        resourceExtensions = new ArrayList();
        resources_list= new ArrayList<Resource>();
        checked_categories = new ArrayList<Category>();
        ObservableList<String> obs = category_crud.ListNames();
        if(obs.isEmpty()){
            alert.showWarningAlert("Warning", "You have no categories added !");
        }
        else{
        category_ch.getItems().addAll(obs);}
               
        resourceExtensions = new ArrayList<>();
        coverExtensions = new ArrayList<>();
        resourceExtensions.add("*.pdf");
        resourceExtensions.add("*.epub");
        resourceExtensions.add("*.fb2");
        resourceExtensions.add("*.html");
        coverExtensions.add("*.jpg");
        coverExtensions.add("*.png");
        coverExtensions.add("*.jpeg");

    }

    @FXML
    private void resourceChoser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().add(new ExtensionFilter("Book files", resourceExtensions));
        List<File> f = fc.showOpenMultipleDialog(null);
        Resource resource=new Resource();
        for (File file : f) {
            try {
             resource = new Resource(file.getAbsolutePath());
             resource_crud.addResource(resource);
             resources_list.add(resource);
            } catch (Exception ex)
            {
                alert.showErrorAlert("Error", "Error while creating resource"+ex.getMessage());
            }            
            
        }
    }

    @FXML
    private void CoverChoser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image files", coverExtensions));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            try{
            cover_path.setText(f.getAbsolutePath());
            cover_path_txt = f.getAbsolutePath();
            }catch (Exception ex)
            {
                alert.showErrorAlert("Error", "Error while adding image:"+ex.getMessage());
            }
        }
    }

    @FXML
    private void Save(ActionEvent event) {
        ObservableList<String> checked_categories_name = category_ch.getCheckModel().getCheckedItems();
        for (Object cat_name : checked_categories_name) {
            checked_categories.add(category_crud.SearchByName(cat_name.toString()));
        }
        if (isbn_txt.getText().isEmpty()
                || author_txt.getText().isEmpty()
                || price_txt.getText().isEmpty()
                || editingHouse_txt.getText().isEmpty()
                || price_txt.getText().isEmpty()
                || checked_categories_name.isEmpty()
                || resources_list.isEmpty()
                || cover_path_txt.equals("") == true) {
            alert.showErrorAlert("Form Error!", "Please fill all the fields");
        } else if (!(rgx.IsvalidIsbn(isbn_txt.getText())
                && rgx.isValidPrice(price_txt.getText())
                && rgx.containsOnlyLettersAndSpaces(editingHouse_txt.getText())
                && rgx.containsOnlyLettersAndSpaces(author_txt.getText()))) {
            alert.showErrorAlert("Form Error!", "Invalid data");
            return;
        } else {
                Boolean book_created = true;

            if (book_crud.BookExits(isbn_txt.getText()))
            {
                alert.showErrorAlert("Error", "Book Already exists!");
                book_created=false;
                
            }
            else {try {
                Book b = new Book(isbn_txt.getText(),
                         Float.parseFloat(price_txt.getText()),
                         title_txt.getText(),
                         author_txt.getText(),
                         editingHouse_txt.getText(),
                         checked_categories,
                         resources_list,
                         cover_path_txt);
                book_crud.addBook(b);
            } catch (Exception ex) {
                alert.showErrorAlert("Error", "Error while creating Book"+ex.getMessage());
                book_created = false;

            }
            }
            if (book_created) {
                alert.showInformationAlert("Book created ", "The book was created successfully!");
            }
        }
        categories = new ArrayList<>();
        for (Object cat_name : checked_categories_name) {
            categories.add(category_crud.SearchByName(cat_name.toString()));
        }

    }

    @FXML
    private void closeAction(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();

    }

}
