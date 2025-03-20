/*package hmi.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Employee;
import models.EmployeeDAO;

public class EmployeeController {
	

	@FXML
	private TableView tblCustomer;
	@FXML
	private TableColumn<Employee,Integer> id;
	@FXML
	private TableColumn<Employee,String> name;
	@FXML
	private TableColumn<Employee,String> email;
	@FXML
	private TableColumn<Employee,String> phone;
	@FXML
	private TableColumn<Employee,String> city;	
	
	
	@FXML
    private void initialize () {
		
		try {
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
	        name.setCellValueFactory(new PropertyValueFactory<>("name"));
	        email.setCellValueFactory(new PropertyValueFactory<>("email"));
	        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
	       city.setCellValueFactory(new PropertyValueFactory<>("city"));
	        addButtonToTable();
			ObservableList<Employee> emps=EmployeeDAO.getAllEmployees();
			tblCustomer.setItems(emps);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addButtonToTable() {        
		TableColumn<Employee, Void> actionBtn = new TableColumn("Actions");
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                	Button btnUpdate=new Button("Update"); 
                	Button btnDelete=new Button("Delete"); 

                    {
                        btnUpdate.setOnAction((ActionEvent event) -> {
                            Employee Employee = getTableView().getItems().get(getIndex());
                            System.out.println("selectedEmployee: " + Employee.getId());
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Employee Employee = getTableView().getItems().get(getIndex());
                            System.out.println("selectedEmployee: " + Employee.getId());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	 HBox managebtn = new HBox(btnUpdate, btnDelete);
                             managebtn.setStyle("-fx-alignment:center");
                             HBox.setMargin(btnUpdate, new Insets(2, 2, 0, 3));
                             HBox.setMargin(btnDelete, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                           
                        }
                    }
                };
                return cell;
            }
        };

        actionBtn.setCellFactory(cellFactory);

        tblCustomer.getColumns().add(actionBtn);

    }

	
	
}	*/
