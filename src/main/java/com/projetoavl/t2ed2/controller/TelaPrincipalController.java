package com.projetoavl.t2ed2.controller;

import com.projetoavl.t2ed2.Global;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    private Pane painelAberto = null;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnImprimir;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnRemover;

    @FXML
    private Pane buscaPanel;

    @FXML
    private Pane imprimirPanel;

    @FXML
    private Pane inserirPanel;

    @FXML
    private Label lblBuscar;

    @FXML
    private Label lblImprimir;

    @FXML
    private Label lblInserir;

    @FXML
    private Label lblRemover;

    @FXML
    private Label lblSair;

    @FXML
    private Pane removerPanel;

    @FXML
    private TextField txtFaltas;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    private ImageView welcome;

    // Sair do programa e salvar as alterações
    @FXML
    void sair(MouseEvent event) {
        Stage currentStage = (Stage) lblSair.getScene().getWindow();
        currentStage.close();
    }


    // Problema: necessita fazer um button para fechar cada panel
    @FXML
    void telaClicada(MouseEvent event) {
        Label labelClicado = (Label) event.getSource();
        Pane panelClicado = null;

        if (labelClicado == lblInserir) {
            panelClicado = inserirPanel;
        } else if (labelClicado == lblRemover) {
            panelClicado = removerPanel;
        } else if (labelClicado == lblBuscar) {
            panelClicado = buscaPanel;
        } else if (labelClicado == lblImprimir) {
            panelClicado = imprimirPanel;
        }

        if (panelClicado != null) {
            panelClicado.setVisible(!panelClicado.isVisible());
        }
    }


    // Animações
    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        inserirPanel.setVisible(false);
        removerPanel.setVisible(false);
        buscaPanel.setVisible(false);
        imprimirPanel.setVisible(false);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(welcome.translateYProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(welcome.translateYProperty(), -50))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        }
    @FXML
    private void labelMousePressed(MouseEvent event) {
        Label label = (Label) event.getSource();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
        transition.setToY(5); // Define o deslocamento vertical
        transition.play();
    }

    @FXML
    private void labelMouseReleased(MouseEvent event) {
        Label label = (Label) event.getSource();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
        transition.setToY(0); // Retorna à posição original
        transition.play();
    }

    }