package com.meybise.Accounts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class AccountsApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminPage.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Accounts");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
