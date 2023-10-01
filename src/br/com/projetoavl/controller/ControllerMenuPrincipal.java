package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * 
 *  Controller da Interface Menu Inicial
 * 
 */

public class ControllerMenuPrincipal {

	@FXML
	private Button cadastro;

	@FXML
	private Button login;

	@FXML
	void button_cadastro(ActionEvent event) {
		Stage currentStage = (Stage) cadastro.getScene().getWindow();
        currentStage.close();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Cadastro.fxml"));
            Parent root = loader.load();
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de menu");
        }
	}

	@FXML
	void button_login(ActionEvent event) {
		Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.close();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            Parent root = loader.load();
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de menu");
        }
	}

	
}
