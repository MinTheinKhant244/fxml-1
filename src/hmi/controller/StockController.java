package hmi.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hmi.model.Category;
import hmi.model.CategoryDAO;
import hmi.model.Product;
import hmi.model.ProductDAO;
import hmi.model.Stock;
import hmi.model.StockDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class StockController implements Initializable {

	 @FXML
	 private Button btnCancel;
		
	 @FXML
	 private Button btnSave;
		
	 @FXML
	 private Button btnUpdate;
		
	 @FXML
	 private ComboBox<Product> cboProduct;
		
	 @FXML
	 private TableColumn<Stock, Integer> colNo;
		
	 @FXML
	 private TableColumn<Stock, String> colPName;
		
	 @FXML
	 private TableColumn<Stock, Integer> colPrice;
		
	 @FXML
	 private TableColumn<Stock, Integer> colQty;
		
	 @FXML
	 private TableColumn<Stock, Date> colDate;
		
	 @FXML
	 private DatePicker stockDate;
		
	 @FXML
	 private TableView tblStock;
	 
	 @FXML
	 private TextField txtPrice;
		
	 @FXML
	 private TextField txtQuantity;
	 
	 private int updatedsid;
		
	 @FXML
	 void cancel(ActionEvent event) throws SQLException {
		ArrayList<Stock> stocks = StockDAO.getAllStocks();
		cboProduct.setValue(null);
 		txtPrice.clear();
 		txtQuantity.clear();
 		stockDate.setValue(null);
 		ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
		tblStock.setItems(data);
	 }
	 
	 @FXML
	 void saveStock(ActionEvent event) throws SQLException {
		Product pid = (Product) cboProduct.getSelectionModel().getSelectedItem();
		int price = Integer.parseInt(txtPrice.getText());
		int qty = Integer.parseInt(txtQuantity.getText());
		Date datepk = Date.valueOf(stockDate.getValue());
		Stock newStock = new Stock(pid.getId(), pid.getName(), price, qty, datepk);
		if (StockDAO.addStock(newStock)) {
			ArrayList<Stock> stocks = StockDAO.getAllStocks();
			Alert alert = new Alert(AlertType.INFORMATION, "Successful");
    		alert.showAndWait();
    		cboProduct.setValue(null);
    		txtPrice.clear();
    		txtQuantity.clear();
    		stockDate.setValue(null);
    		ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
			tblStock.setItems(data);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Failed");
    		alert.showAndWait();
		}
	 }
	 
	 @FXML
	 void updateStock(ActionEvent event) throws SQLException {
		Product pid = (Product) cboProduct.getSelectionModel().getSelectedItem();
		int price = Integer.parseInt(txtPrice.getText());
		int qty = Integer.parseInt(txtQuantity.getText());
		Date datepk = Date.valueOf(stockDate.getValue());
		Stock uStock = new Stock(updatedsid, pid.getId(), pid.getName(), price, qty, datepk);
		if (StockDAO.updateStock(uStock)) {
			ArrayList<Stock> stocks = StockDAO.getAllStocks();
			Alert alert = new Alert(AlertType.INFORMATION, "Successful");
			alert.showAndWait();
			cboProduct.setValue(null);
			txtPrice.clear();
			txtQuantity.clear();
			stockDate.setValue(null);
			ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
			tblStock.setItems(data);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Failed");
			alert.showAndWait();
		}
	 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colPName.setCellValueFactory(new PropertyValueFactory<>("pname"));
			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
			colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
			addButtonToTable();
			ArrayList<Product> products = ProductDAO.getAllProducts();
			ArrayList<Stock> stockList = StockDAO.getAllStocks();
			ObservableList<Product> dataP = FXCollections.observableArrayList(products);
			ObservableList<Stock> data = FXCollections.observableArrayList(stockList);
			tblStock.setItems(data);
			cboProduct.setItems(dataP);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addButtonToTable() {
		TableColumn<Stock, Void> colActions = new TableColumn("Actions");
        Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>> cellFactory = new Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>>() {
            @Override
            public TableCell<Stock, Void> call(final TableColumn<Stock, Void> param) {
                final TableCell<Stock, Void> cell = new TableCell<Stock, Void>() {
                	Button btnEdit=new Button("Edit"); 
                	Button btnDelete=new Button("Delete"); 

                    {
                    	btnEdit.setOnAction((ActionEvent event) -> {
                            Stock stock = getTableView().getItems().get(getIndex());
                            txtPrice.setText(String.valueOf(stock.getPrice()));
                            txtQuantity.setText(String.valueOf(stock.getQty()));
                            Date sqlDate = (Date) stock.getDate();
                            LocalDate localDate = sqlDate.toLocalDate();
                            stockDate.setValue(localDate);
                            int pid = stock.getPid();
                            Product pdt = null;
							try {
								pdt = ProductDAO.getProduct(pid);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            cboProduct.setValue(pdt);
                            updatedsid = stock.getId();
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                        	Stock stock = getTableView().getItems().get(getIndex());
                            try {
								boolean status = StockDAO.deleteStock(stock.getId());
								if (status) {
	                        		ArrayList<Stock> stocks = StockDAO.getAllStocks();
	                        		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
	                        		alert.showAndWait();
	                        		cboProduct.setValue(null);
	                        		txtPrice.clear();
	                        		txtQuantity.clear();
	                        		stockDate.setValue(null);
	                        		ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
	                    			tblStock.setItems(data);
	                        	}
	                        	else {
	                        		Alert alert = new Alert(AlertType.ERROR, "Failed");
	                        		alert.showAndWait();
	                        	}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            
                        });
                    }
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	 HBox managebtn = new HBox(btnEdit, btnDelete);
                             managebtn.setStyle("-fx-alignment:center");
                             btnDelete.setStyle("-fx-background-color: red;");
                             btnEdit.setStyle("-fx-background-color: yellow;");
                             HBox.setMargin(btnEdit, new Insets(2, 2, 0, 3));
                             HBox.setMargin(btnDelete, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                           
                        }
                    }
                };
                return cell;
            }
        };
        
        colActions.setCellFactory(cellFactory);

        tblStock.getColumns().add(colActions);
	}
	 

}
