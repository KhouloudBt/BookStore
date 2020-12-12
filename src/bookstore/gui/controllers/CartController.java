/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.services.CartCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PORTGASDACE
 */
public class CartController implements Initializable {

    @FXML
    private ListView<Book> bookList;
    @FXML
    private Button nextBook;
    @FXML
    private Button lastBook;
    @FXML
    private Pane bookCover;
    @FXML
    private Label name;
    @FXML
    private Label writer;
    @FXML
    private Label type;
    @FXML
    private Label price;
    @FXML
    private Label disc;
    @FXML
    private Button remove;
    @FXML
    private Button buy;
    @FXML
    private Button clearCart;
    @FXML
    private Button search;
    @FXML
    private TextField searchBar;
    @FXML
    private AnchorPane containtCreator;
    @FXML
    private Label title;
    @FXML
    private Label house;
    @FXML
    private Label rating;
    @FXML
    private Label usr;
    @FXML
    private Button cnl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newlist();
        selectBook();
    }    
   
    private void newlist() {
        CartCRUD cc = new CartCRUD();
        ArrayList<Book> lv = cc.cartBooks();
        bookList.getItems().clear();
        bookList.getItems().addAll(lv);
        bookList.getSelectionModel().select(0);
    }
    @FXML
    private void next(ActionEvent event) {
        int n=bookList.getSelectionModel().getSelectedIndex()+1;
        bookList.getSelectionModel().select(n);
        bookList.getFocusModel().focus(n);
   selectBook();
    }

    @FXML
    private void last(ActionEvent event) {

        int l=bookList.getSelectionModel().getSelectedIndex()-1;       
        if(l>=0)
        {
              bookList.getSelectionModel().select(l);
              bookList.getFocusModel().focus(l);
         selectBook();
        }
    }

    @FXML
    private void btnr(ActionEvent event) {
                   bookList.getItems().remove(bookList.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void btnb(ActionEvent event) {
                System.out.println("buy");
    }

    @FXML
    private void btnc(ActionEvent event) {
                      bookList.getItems().clear();
    }

    @FXML
    private void btns(ActionEvent event) {
              newlist();
             ListView<Book> bl = null;
          bl = new ListView<Book>();
             for (Book b : bookList.getItems()) {
            
                 if (searchBar.getText().contains(b.getTitle())) {
                    bl.getItems().add(b);
                 }
            
        }
             bookList.getItems().clear();
             bookList.getItems().addAll(bl.getItems());
                   
    }


    @FXML
    private void myBooks(MouseEvent event) {    
        selectBook();
    }

    private void selectBook() {
               Book b=bookList.getSelectionModel().getSelectedItem();
        System.out.println(b);

          name.setText("title : "+b.getTitle());
          title.setText("title : "+b.getTitle());
         writer.setText("Writer : "+b.getAuthor());
          type.setText("Categories : "+b.getCategories());
            disc.setText("Description : \n"+b.getTitle()+" "+b.getAuthor()+"\n\t"+b.getPrice()+"DTN");
             rating.setText("Rating : "+b.getAverageRatings());
             usr.setText("Containt Creator : "+b.getOwner());
             price.setText("-- Price : "+b.getPrice()+" DTN--");
    }

    @FXML
    private void btncnl(ActionEvent event) {
           newlist();
           selectBook();
    }

    
}
