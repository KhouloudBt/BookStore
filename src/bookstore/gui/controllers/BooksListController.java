/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.services.BookCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class BooksListController implements Initializable {

    @FXML
    private TableView<Book> tableview;
    @FXML
    private TableColumn<Book, String> isbn;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, Integer> nbRatings;
    @FXML
    private TableColumn<Book, Double> AverageRating;
    @FXML
    private TableColumn<Book, String> descriptionl;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, Float> price;

    ObservableList<Book> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        nbRatings.setCellValueFactory(new PropertyValueFactory<>("nbRatings"));
        AverageRating.setCellValueFactory(new PropertyValueFactory<>("averageRatings"));
        descriptionl.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Book, Book> deleteUser = new TableColumn<>("DeleteBook");
        
        
        deleteUser.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteUser.setCellFactory(param -> {
            return new TableCell<Book,Book>() {
                private final Button deleteButton = new Button("delete");

              
                @Override
                protected void updateItem(Book book, boolean empty) {
                    super.updateItem(book, empty);
                    if (book == null) {
                        setGraphic(null);
                        return;
                    }
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> {
                        BookCRUD uc = new BookCRUD();
                        uc.deleteBook(book);
                        list.remove(book);
                    });
                }
            };
        });
        tableview.getColumns().addAll(deleteUser);
    }

    private void loadData() {
        list.clear();
        BookCRUD book_crud = new BookCRUD();
        list = book_crud.listBooksObservable();
        //System.out.println(list);
        tableview.setItems(list);
    }

}
