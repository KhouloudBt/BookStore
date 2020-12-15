/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.entities.Cart;
import bookstore.services.CartCRUD;
import bookstore.utilities.CustomAlert;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    CartCRUD cc = new CartCRUD();
    Cart cart;
    
    @FXML
    private ImageView bookCover;
    @FXML
    private Label cartQTE;
    @FXML
    private Label worth;
    @FXML
    private Label bookListText;
    
    // init Class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newlist();
        selectBook();
    }    
//update cart
   private void updateC(){
       cart =cc.updateCart(cart);
   }
    
    //Cart Books
    private void newlist() {
        cart = new Cart();
        //get the user cart
        cart.setId(1);
        ArrayList<Book> lv = cc.cartBooks(cart);
        cart.setBooks(lv);
         updateC();
        cartQTE.setText("Your Cart Contains "+cart.getQte()+" Books");
        worth.setText("Your Cart Total Worth is "+cart.getCartWorth()+" DTN");
        bookListText.setText("Your Cart's Books Are :");
        bookList.getItems().clear();
        bookList.getItems().addAll(lv);
        bookList.getSelectionModel().select(0);
    }
    //Next Book
    @FXML
    private void next(ActionEvent event) {
        int n=bookList.getSelectionModel().getSelectedIndex()+1;
        bookList.getSelectionModel().select(n);
        bookList.getFocusModel().focus(n);
   selectBook();
    }
    //Last Book
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
    //Remove
    @FXML
    private void btnr(ActionEvent event) {
                   cc.deleteBook(bookList.getSelectionModel().getSelectedItem(),cart);
                   bookList.getItems().remove(bookList.getSelectionModel().getSelectedIndex());
                   updateC();
    }
    //Buy
    @FXML
    private void btnb(ActionEvent event) {
                System.out.println("buy");
    }
    //Clear
    @FXML
    private void btnc(ActionEvent event) {
                      cc.emptyCart(cart);
                      bookList.getItems().clear();
                      updateC();

    }
    //Search
    @FXML
    private void btns(ActionEvent event) {
              newlist();
             ListView<Book> bl = null;
          bl = new ListView<Book>();
             for (Book b : bookList.getItems()) {
            
                 if (b.getTitle().contains(searchBar.getText())) {
                    bl.getItems().add(b);
                 }
            
        }
             bookList.getItems().clear();
             bookList.getItems().addAll(bl.getItems());
                   
    }
        //Cancel Search
    @FXML
    private void btncnl(ActionEvent event) {
           newlist();
           selectBook();
    }
    //Show Book
    @FXML
    private void myBooks(MouseEvent event) {    
        selectBook();
    }

    private void selectBook() {
               Book b=bookList.getSelectionModel().getSelectedItem();
        System.out.println(b);

          name.setText("title : "+b.getTitle());
          title.setText(b.getTitle());
         writer.setText("Writer : "+b.getAuthor());
        ObservableList<String> listCat= b.getCategories().stream()
                .map(c -> c.getName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
          type.setText("Categories : "+listCat);
            disc.setText("Description : \n"+b.getDesciption());
             rating.setText("Rating : "+b.getAverageRatings());
             usr.setText("Containt Creator : "+b.getOwner());
             house.setText("Editing House : "+b.getEditingHouse());
             price.setText("-- Price : "+b.getPrice()+" DTN --");
             System.out.println(b.getCover());

        try {
            File file = new File("C:\\\\Books\\\\"+b.getCover().trim());
            InputStream stream = new FileInputStream(file);
            Image image = new Image(stream);
            bookCover.setImage(image);
        } catch (Exception ex) {
            CustomAlert.showErrorAlert("Error image", "error while reading image" + ex.getMessage());
        }

        
    }

    
}
