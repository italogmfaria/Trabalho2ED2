package com.projetoavl.t2ed2.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class TelaInicialController {

    @FXML
    private Pane telaInicial;

    @FXML
    private Pane mainTela;

    Stage stage;

    @FXML
    public void abreTela(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projetoavl/t2ed2/TelaPrincipal.fxml"));
        Parent telaPrincipal = loader.load();

        Scene scene = new Scene(telaPrincipal);

        Stage primaryStage = (Stage) telaInicial.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void fechaTela(ActionEvent event) throws IOException{
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Saindo");
        alerta.setHeaderText("Você está prestes a fechar a aplicação!");
        alerta.setContentText("Quer mesmo sair?");
        if (alerta.showAndWait().get() == ButtonType.OK){
            stage = (Stage) telaInicial.getScene().getWindow();
            System.out.println("Aplicativo fechado com sucesso.");
            Platform.exit();
        }
    }
}

