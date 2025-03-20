package hmi.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import hmi.model.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class LoginController {
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnLogin;

	
	@FXML
	private void loginClick() throws SQLException, IOException {
		String username = null;
		String password = null;
		
		username = txtUsername.getText();
		password = txtPassword.getText();
		boolean status = LoginDAO.checkCredential(username, password);
		if (!status) {
			Alert alert = new Alert(AlertType.ERROR, "Wrong Username and Password", ButtonType.OK);
			alert.show();
		}
		else {
			Stage stage = (Stage)btnLogin.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/application/registration.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}
	
//	FXMLLoader loader = new FXMLLoader();
//	URL xmlUrl = getClass().getResource("login.fxml");
//	loader.setLocation(xmlUrl);
//	Parent root = loader.load();
	
}
