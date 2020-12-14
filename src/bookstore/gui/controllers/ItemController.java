package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.utilities.MyListener;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
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

//        try {
//            Image image = new Image(getClass().getResourceAsStream(book.getCover()));
//            img.setImage(image);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
        File file = new File(book.getCover().trim());
        InputStream stream = new FileInputStream(file);
        Image image = new Image(stream);
        img.setImage(image);
//  String urll=rs.getString("image");
//               File file = new File(urll);
//              
//            InputStream stream = new FileInputStream(file);
//            Image image = new Image(stream);
//            imageView.setImage(image);
        System.out.println("here3 ");

        avgRating.setRating(book.getAverageRatings());
        nbRating.setText(book.getNbRatings() + "");

        title.setText(book.getTitle());
        System.out.println("here 2");

    }

    @FXML
    private void Rate(ActionEvent event) {
        int nb_ratings = this.book.getNbRatings();
        double new_rating = avgRating.getRating();
        double avg_rating = this.book.getAverageRatings();
        System.out.println(avg_rating);
        this.book.setNbRatings(nb_ratings + 1);
        this.book.setAverageRatings((double) (avg_rating + new_rating) / nb_ratings);
        System.out.println(this.book.getAverageRatings());
    }

    @FXML
    private void Rate(MouseDragEvent event) {
        int nb_ratings = this.book.getNbRatings();
        double new_rating = avgRating.getRating();
        double avg_rating = this.book.getAverageRatings();
        System.out.println(avg_rating);
        this.book.setNbRatings(nb_ratings + 1);
        this.book.setAverageRatings((double) (avg_rating + new_rating) / nb_ratings);
        System.out.println(this.book.getAverageRatings());
    }

    @FXML
    private void Rate(DragEvent event) {
        int nb_ratings = this.book.getNbRatings();
        double new_rating = avgRating.getRating();
        double avg_rating = this.book.getAverageRatings();
        System.out.println(avg_rating);
        this.book.setNbRatings(nb_ratings + 1);
        this.book.setAverageRatings((double) (avg_rating + new_rating) / nb_ratings);
        System.out.println(this.book.getAverageRatings());
    }

    @FXML
    private void Rate(MouseEvent event) {
        int nb_ratings = this.book.getNbRatings();
        double new_rating = avgRating.getRating();
        double avg_rating = this.book.getAverageRatings();
        System.out.println(avg_rating);
        this.book.setNbRatings(nb_ratings + 1);
        this.book.setAverageRatings((double) (avg_rating + new_rating) / nb_ratings);
        System.out.println(this.book.getAverageRatings());
    }

}
