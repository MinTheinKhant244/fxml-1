package hmi.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hmi.model.DiscountDAO;
import hmi.model.Product;
import hmi.model.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DiscountController implements Initializable{
	
	ArrayList<Product> selectedProducts = new ArrayList<Product>();

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker endDate;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField txtPercentage;

    @FXML
    private VBox vbContainer;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveDiscount(ActionEvent event) throws SQLException {
    	for (Product p:selectedProducts) {
    		System.out.println(p.getName());
    	}
    	int percentage = Integer.parseInt(txtPercentage.getText());
    	LocalDate sdate = startDate.getValue();
    	LocalDate edate = endDate.getValue();

    	boolean status = DiscountDAO.addDiscounts(selectedProducts, percentage, sdate, edate);
    	if (status) {
    		Alert alert = new Alert (AlertType.INFORMATION,"Successful");
    		alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert (AlertType.ERROR,"Failed");
    		alert.showAndWait();
    	}
    }
                      
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<Product> pList;
		try {
			pList = ProductDAO.getAllProducts();
			for (Product p:pList) {
	        	CheckBox cb = new CheckBox(p.getName());
	        	vbContainer.getChildren().add(cb);
	        	cb.setOnAction(event -> {
	        		if (cb.isSelected()) {
	        			System.out.println(p.getName());
	        			selectedProducts.add(p);
	        			System.out.println(selectedProducts.size());
	        		}
	        		else {
	        			selectedProducts.remove(p);
	        			System.out.println(p.getName());
	        		}
	        	});
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
//    public void handleClick (Product p) {
//    	System.out.println(p.getName());
//    }
    

}
