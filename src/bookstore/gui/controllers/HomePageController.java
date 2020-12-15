/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.gui.controllers;

import bookstore.entities.Book;
import bookstore.services.BookCRUD;
import bookstore.utilities.MyListener;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class HomePageController implements Initializable {

    @FXML
    private GridPane grid;

    BookCRUD book_crud = new BookCRUD();
    Image chosen_book_image;
    Image temp_img;
    private MyListener myListener;
    private ArrayList<Book> books_list = new ArrayList<>();
    @FXML
    private JFXButton return_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        books_list.addAll(book_crud.listBooks());

        AnchorPane anchorPane;
        FXMLLoader fxmlLoader;
        ItemController itemController = new ItemController();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < books_list.size(); i++) {
                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ItemController.class.getResource("item.fxml"));
                System.out.println("Location");
                fxmlLoader.getLocation();
                System.out.println("loaded");
                //fxmlLoader.setController(itemController);

                anchorPane = fxmlLoader.load();
                System.out.println("AnchorPane");
                System.out.println("get i");
                itemController = fxmlLoader.getController();
                itemController.setData(books_list.get(i), myListener);
                System.out.println("get i"+i);

                if (column == 4) {
                    column = 1;
                    row++;
                }
                System.out.println("hné " );

                grid.add(anchorPane, column++, row);
                System.out.println("anchor id " + anchorPane.getId());
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                System.out.println(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_PREF_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private Color getRandomColor() {
        Random rand = new Random();
        float r = (float) (rand.nextFloat() / 2f + 0.5);
        float g = (float) (rand.nextFloat() / 2f + 0.5);
        float b = (float) (rand.nextFloat() / 2f + 0.5);
        return new Color(r, g, b);
    }

    @FXML
    private void Return(ActionEvent event) throws IOException {
        Parent user = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));
        Scene scene = new Scene(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();}
           
    }

