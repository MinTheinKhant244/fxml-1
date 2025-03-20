package hmi.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hmi.model.Category;
import hmi.model.CategoryDAO;
import hmi.model.Product;
import hmi.model.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ProductController implements Initializable{

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colActions;

    @FXML
    private ComboBox cboCategory;
    
    @FXML
    private TableColumn<Product, String> colCategory;

    @FXML
    private TableColumn<Product, String> colDescription;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Integer> colNo;

    @FXML
    private TableColumn<Product, Integer> colPrice;

    @FXML
    private TableView tblProduct;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;
    
    private int updatedPid;
    
    @FXML
    void cancel(ActionEvent event) {
    	
    }

    @FXML
    void saveUser(ActionEvent event) throws SQLException {
    	String name = txtProductName.getText();
    	int price = Integer.parseInt(txtPrice.getText());
    	Category cid = (Category) cboCategory.getSelectionModel().getSelectedItem(); 
    	String description = txtDescription.getText();
    	Product newProduct = new Product(name, price, cid.getId(), description);
    	boolean status = ProductDAO.addUser(newProduct);
    	if (status) {
    		ArrayList<Product> products = ProductDAO.getAllProducts();
    		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
    		alert.showAndWait();
    		txtProductName.clear();
    		txtPrice.clear();
    		txtDescription.setText("");
    		tblProduct.refresh();
    		ObservableList<Product> data = FXCollections.observableArrayList(products);
			tblProduct.setItems(data);
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR, "Failed");
    		alert.showAndWait();
    	}
    }

    @FXML
    void updateUser(ActionEvent event) throws SQLException {
    	String name = txtProductName.getText();
    	int price = Integer.parseInt(txtPrice.getText());
    	Category cid = (Category) cboCategory.getSelectionModel().getSelectedItem();
    	String description = txtDescription.getText();
    	Product uProduct = new Product(updatedPid, name, price, cid.getId(), description);
    	boolean status = ProductDAO.updateProduct(uProduct);
    	if (status) {
    		ArrayList<Product> products = ProductDAO.getAllProducts();
    		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
    		alert.showAndWait();
    		txtProductName.clear();
    		txtPrice.clear();
    		txtDescription.setText("");
    		tblProduct.refresh();
    		ObservableList<Product> data = FXCollections.observableArrayList(products);
			tblProduct.setItems(data);
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR, "Failed");
    		alert.showAndWait();
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ArrayList<Category> categories = CategoryDAO.getAllCategories();
			ArrayList<Product> products = ProductDAO.getAllProducts();
			colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			colCategory.setCellValueFactory(new PropertyValueFactory<>("cname"));
			colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
			addButtonToTable();
			ObservableList<Product> data = FXCollections.observableArrayList(products);
			ObservableList<Category> dataC = FXCollections.observableArrayList(categories);
			tblProduct.setItems(data);
			cboCategory.setItems(dataC);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addButtonToTable() {
		TableColumn<Product, Void> colActions = new TableColumn("Actions");
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
                	Button btnEdit=new Button("Edit"); 
                	Button btnDelete=new Button("Delete"); 

                    {
                    	btnEdit.setOnAction((ActionEvent event) -> {
                            Product product = getTableView().getItems().get(getIndex());
                            txtProductName.setText(product.getName());
                            txtPrice.setText(String.valueOf(product.getPrice()));
                            txtDescription.setText(product.getDescription());
                            int cid = product.getCid();
                            Category cat = null;
							try {
								cat = CategoryDAO.getCategory(cid);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            cboCategory.setValue(cat);
                            updatedPid = product.getId();
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                        	Product product = getTableView().getItems().get(getIndex());
                            try {
								boolean status = ProductDAO.deleteProduct(product.getId());
								if (status) {
	                        		ArrayList<Product> products = ProductDAO.getAllProducts();
	                        		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
	                        		alert.showAndWait();
	                        		txtProductName.clear();
	                        		txtPrice.clear();
	                        		txtDescription.setText("");
	                        		tblProduct.refresh();
	                        		ObservableList<Product> data = FXCollections.observableArrayList(products);
	                    			tblProduct.setItems(data);
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

        tblProduct.getColumns().add(colActions);
        
    }

	
	
}



