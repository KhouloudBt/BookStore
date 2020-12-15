package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.entities.Cart;
import bookstore.services.CartCRUD;
import bookstore.services.CategoryCRUD;
import bookstore.utilities.CustomAlert;
import bookstore.utilities.MyListener;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

public class ItemController {

    @FXML
    private Rating avgRating;
    @FXML
    private JFXButton btn_rate;

    private Book book;
    private MyListener myListener;
    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label bcategory;
    @FXML
    private Label price;
    @FXML
    private Label nbRating;
    @FXML
    private ImageView img;
    
    CategoryCRUD cr = new CategoryCRUD();
    @FXML
    private ImageView btn_cart;
    

    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(book);
    }

    public void setData(Book book, MyListener myListener) throws FileNotFoundException {
        this.book = book;
        this.myListener = myListener;
        System.out.println(book.getTitle());
        title.setText(book.getTitle());
        System.out.println(title.getText());
        price.setText("" + book.getPrice());
        System.out.println("here1 ");
        System.out.println(book.getCover());
               Cart cart = new Cart();
               cart.setId(1);
            CartCRUD cc = new CartCRUD();
            if(cc.exsistInCart(cart,this.book))
                btn_cart.setImage(null);
        try {
            File file = new File("C:\\\\Books\\\\"+book.getCover().trim());
            InputStream stream = new FileInputStream(file);
            Image image = new Image(stream);
            img.setImage(image);
        } catch (Exception ex) {
            CustomAlert.showErrorAlert("Error image", "error while reading image" + ex.getMessage());
        }

        avgRating.setRating(book.getAverageRatings());
        nbRating.setText(book.getNbRatings() + "");
        author.setText(book.getAuthor());
        String categories="";
        
//        for (int i =0; i<(cr.ListByBook(book.getIsbn(); i++)
//        {
//            categories=categories+book.getCategories().
//        }
//        {
//            
//        }
//        bcategory.setText(book.getCategories());
        

        title.setText(book.getTitle());

    }

    @FXML
    private void Rate(ActionEvent event) {
        int nb_ratings = this.book.getNbRatings();
        double new_rating = avgRating.getRating();
        double avg_rating = this.book.getAverageRatings();
        System.out.println("average rating"+avg_rating);
        this.book.setNbRatings(nb_ratings + 1);
        this.book.setAverageRatings((double) (avg_rating + new_rating) / book.getNbRatings());
        nbRating.setText(book.getNbRatings()+"");
    }

    @FXML
    private void addCart(MouseEvent event) {
        Cart cart = new Cart();
        cart.setId(1);
            CartCRUD cc = new CartCRUD();
            cc.addBook(this.book, cart);
              System.out.println("OPEN CART");
         Parent user;
        try {
            user = FXMLLoader.load(getClass().getResource("/bookstore/gui/xml/Cart.fxml"));
     
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();  
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
