/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.services.BookCRUD;
import bookstore.utilities.CustomAlert;
import bookstore.utilities.MyListener;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class UpdateBookController  {

    @FXML
    private ImageView img;
    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label bcategory;
    @FXML
    private Label price;
    @FXML
    private Rating avgRating;
    @FXML
    private JFXButton btn_rate;
    @FXML
    private ImageView btn_delete;
    @FXML
    private ImageView btn_update;
    @FXML
    private ImageView btn_cart;
    @FXML
    private Label nbRating;
    
    private Book book;
    private MyListener myListener;
    BookCRUD book_crud = new BookCRUD();
    
      private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(book);
    }
    

    /**
     * Initializes the controller class.
     */
 
 //khouloud! okng ?se3a what ama i doing!!idk !

    @FXML
    private void delete(MouseEvent event) {
        CustomAlert.showConfirmationAlert("Confirm delete", "The book "+book.getTitle()+"is going to be deleted");
        try{
        book_crud.deleteBook(book);
        }catch (Exception ex)
        {
            CustomAlert.showErrorAlert("Error delete", "Error while deleting book"+ex.getMessage());
        }
    }

    @FXML
    private void update(MouseEvent event) {
        BookCRUD cr = new BookCRUD();
        
    }

   
    
}
