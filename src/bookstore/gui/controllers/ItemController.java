package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.utilities.MyListener;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;


public class ItemController{
    private Label nameLabel;

    private Label priceLable;

    private ImageView img;
    
    private Book book;
    @FXML
    private Rating avgRating;
    @FXML
    private JFXButton btn_rate;


    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(book);
    }

    private MyListener myListener;

    public void setData(Book book, MyListener myListener) {
        this.book = book;
        this.myListener = myListener;
        nameLabel.setText(book.getTitle());
        priceLable.setText(bookstore.BookStore.CURRENCY + book.getPrice());
        Image image = new Image(getClass().getResourceAsStream(book.getCover()));
        avgRating.setRating(book.getPrice());
        img.setImage(image);
         
    }
    
    @FXML
    private void Rate(ActionEvent event) {
        int nb_ratings= this.book.getNbRatings();
        double new_rating=avgRating.getRating();
        double avg_rating=this.book.getAverageRatings();
        System.out.println(avg_rating);
        this.book.setNbRatings(nb_ratings+1);
        this.book.setAverageRatings((double)(avg_rating+new_rating)/nb_ratings);
        System.out.println(this.book.getAverageRatings());
    }

    @FXML
    private void rate(MouseDragEvent event) {
    }

    @FXML
    private void rate(DragEvent event) {
    }

    @FXML
    private void rate(MouseEvent event) {
    }

    @FXML
    private void tare(MouseDragEvent event) {
    }
}
