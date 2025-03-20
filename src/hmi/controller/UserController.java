package hmi.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import hmi.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class UserController {

	@FXML
    private Button btnRegister;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radioMale;
    
    @FXML
    private RadioButton radioFemale;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNrc;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtUsername;
	
	@FXML
	private void registerClick() throws SQLException {
		
		String username = txtUsername.getText();
		String email = txtEmail.getText();
		String nrc = txtNrc.getText();
		String phone = txtPhoneNumber.getText();
		String address = txtAddress.getText();
		RadioButton selectRadio = (RadioButton)gender.getSelectedToggle();
		String gender = " ";
		LocalDate dob_value = txtDateOfBirth.getValue();
		
		System.out.println(username);
		if (selectRadio == radioMale) {
			gender = "male";
		}
		else {
			gender = "female";
		}
		
		boolean status = UserDAO.saveUser(username, nrc, email, phone, dob_value.toString(), address, gender);
		if (status) {
			Alert alert = new Alert (AlertType.INFORMATION, "Added Successfully");
			alert.show();
		}
		else {
			Alert alert = new Alert (AlertType.ERROR, "Error when adding new students");
			alert.show();
		}
		
	}
	
	
	
}
