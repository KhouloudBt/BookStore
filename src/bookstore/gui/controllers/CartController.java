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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    Cart cart = new Cart();
    
    @FXML
    private ImageView bookCover;
    @FXML
    private Label cartQTE;
    @FXML
    private Label worth;
    @FXML
    private Label bookListText;
    @FXML
    private ImageView homeImg;
    
    // init Class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newlist();
        selectBook();
    }    
//update cart
   private void updateC(){
        ArrayList<Book> lv = cc.cartBooks(cart);
        bookList.getItems().clear();
        bookList.getItems().addAll(lv);
        cart.setBooks(lv);
           noBookSelected();
       cart =cc.updateCart(cart);
       if (cart.getBooks().isEmpty()){
           cartQTE.setText("No Books In Your Cart !!!");
        worth.setText("");
        bookListText.setText("");
        noBookSelected();
   }
       else if (cart.getBooks().size()==1){
        cartQTE.setText("Your Cart only Contains 1 Book");
        worth.setText("Your Cart Total Worth is "+cart.getCartWorth()+" DTN");
        bookListText.setText("Your Cart's Book is :");
       }
       else{
        cartQTE.setText("Your Cart Contains "+cart.getQte()+" Books");
        worth.setText("Your Cart Total Worth is "+cart.getCartWorth()+" DTN");
        bookListText.setText("Your Cart's Books Are :");
               }
       
   }
    
    //Cart Books
    private void newlist() {
        cart.setId(1);
         updateC();
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
                   noBookSelected();
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
                      noBookSelected();
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
           searchBar.setText("");
           noBookSelected();
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
             usr.setText("Containt Creator : "+b.getId_owner());
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

    @FXML
    private void goHome(MouseEvent event) {
        System.out.println("go home");
        Parent user;
        try {
            user = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();  
        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void noBookSelected() {
        
          name.setText("");
          title.setText("No Book Selected");
         writer.setText("");
          type.setText("");
            disc.setText("");
             rating.setText("");
             usr.setText("");
             house.setText("");
             price.setText("");
            bookCover.setImage(null);
    }

    
}
