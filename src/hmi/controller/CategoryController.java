package hmi.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hmi.model.Category;
import hmi.model.CategoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class CategoryController implements Initializable{

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colActions;

    @FXML
    private TableColumn<Category, String> colDescription;

    @FXML
    private TableColumn<Category, String> colName;

    @FXML
    private TableColumn<Category, Integer> colNo;

    @FXML
    private TableView tblCategory;

    @FXML
    private TextField txtCategoryDescription;

    @FXML
    private TextField txtCategoryName;
    
    private int updatedCid;
    
    @FXML
    void cancel(ActionEvent event) {
    	
    }

    @FXML
    void saveUser(ActionEvent event) throws SQLException {
    	String name = txtCategoryName.getText();
    	String description = txtCategoryDescription.getText();
    	Category newCategory = new Category(name, description);
    	boolean status = CategoryDAO.addCategory(newCategory);
    	if (status) {
    		ArrayList<Category> categories = CategoryDAO.getAllCategories();
    		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
    		alert.showAndWait();
    		txtCategoryName.clear();
    		txtCategoryDescription.setText("");
    		tblCategory.refresh();
    		ObservableList<Category> data = FXCollections.observableArrayList(categories);
			tblCategory.setItems(data);
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR, "Failed");
    		alert.showAndWait();
    	}
    }

    @FXML
    void updateUser(ActionEvent event) throws SQLException {
    	String name = txtCategoryName.getText();
    	String description = txtCategoryDescription.getText();
    	Category uCategory = new Category(updatedCid, name, description);
    	boolean status = CategoryDAO.updateCategory(uCategory);
    	if (status) {
    		ArrayList<Category> categories = CategoryDAO.getAllCategories();
    		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
    		alert.showAndWait();
    		txtCategoryName.clear();
    		txtCategoryDescription.setText("");
    		tblCategory.refresh();
    		ObservableList<Category> data = FXCollections.observableArrayList(categories);
			tblCategory.setItems(data);
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
			colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
			colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
			addButtonToTable();
			ObservableList<Category> data = FXCollections.observableArrayList(categories);
			tblCategory.setItems(data);		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addButtonToTable() {
		TableColumn<Category, Void> colActions = new TableColumn("Actions");
        Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
            @Override
            public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
                final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
                	Button btnEdit=new Button("Edit"); 
                	Button btnDelete=new Button("Delete"); 

                    {
                    	btnEdit.setOnAction((ActionEvent event) -> {
                            Category category = getTableView().getItems().get(getIndex());
                            txtCategoryName.setText(category.getName());
                            txtCategoryDescription.setText(category.getDescription());
                            updatedCid = category.getId();
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                        	Category category = getTableView().getItems().get(getIndex());
                            try {
								boolean status = CategoryDAO.deleteCategory(category.getId());
								if (status) {
	                        		ArrayList<Category> categories = CategoryDAO.getAllCategories();
	                        		Alert alert = new Alert(AlertType.INFORMATION, "Successful");
	                        		alert.showAndWait();
	                        		txtCategoryName.clear();
	                        		txtCategoryDescription.setText("");
	                        		tblCategory.refresh();
	                        		ObservableList<Category> data = FXCollections.observableArrayList(categories);
	                    			tblCategory.setItems(data);
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

        tblCategory.getColumns().add(colActions);
        
    }

	
	
}



