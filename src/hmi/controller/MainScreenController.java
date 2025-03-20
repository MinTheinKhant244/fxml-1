package hmi.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainScreenController {

    @FXML
    private Button manCategory;

    @FXML
    private Button manDiscount;

    @FXML
    private Button manProduct;

    @FXML
    private Button manStock;

    @FXML
    private BorderPane borderPane;

    @FXML
    void btnManCategory(ActionEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/application/category.fxml"));
    	borderPane.setCenter(root);
    }

    @FXML
    void btnManDiscount(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/discount.fxml"));
    	borderPane.setCenter(root);
    }

    @FXML
    void btnManProduct(ActionEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/application/product.fxml"));
    	borderPane.setCenter(root);
    }

    @FXML
    void btnManStock(ActionEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/application/stock.fxml"));
    	borderPane.setCenter(root);
    }

}
