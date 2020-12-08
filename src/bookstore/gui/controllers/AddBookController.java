/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.MyConnection;
import bookstore.entities.Book;
import bookstore.entities.Category;
import bookstore.services.BookCRUD;
import bookstore.services.CategoryCRUD;
import bookstore.services.RegexTests;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class AddBookController implements Initializable {

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField price;
    @FXML
    private TextField EditingHouse;
    @FXML
    private Button resource;
    @FXML
    private Button cover;
    @FXML
    private ChoiceBox<String> category;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryCRUD cr = new CategoryCRUD();
        category.setItems(cr.ListNames());
        

    }

    private final String isbn_txt = isbn.getText();
    private final String title_txt = title.getText();
    private final String author_txt = author.getText();
    private final String price_txt = price.getText();
    private final String EditingHouse_txt = EditingHouse.getText();

    @FXML
    private void Save(ActionEvent event) {
        BookCRUD br = new BookCRUD();
        RegexTests rgx = new RegexTests();

        if (!rgx.IsvalidIsbn(isbn_txt)) {
            System.out.println("Invalid Isbn!");
        } else if (br.BookExits(Integer.parseInt(isbn_txt))) {
            System.out.println("Book already exists !");
        } else if (!rgx.isValidPrice(price_txt)) {
            System.out.println("Book already exists !");
        }
        List<Category> categories;
        categories = new ArrayList<Category>();
        MyConnection cnx = MyConnection.getInstance();
        Book b = new Book(Integer.parseInt(isbn_txt), title_txt,);
        br.addBook(b);
    }
    Book b = new Book
    
}

@FXML
        private void cancel(ActionEvent event) {
        Name.setText("");
        description.setText("");
    }


