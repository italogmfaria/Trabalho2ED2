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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private TableView tableRemover;

    @FXML
    private TableView tableBuscar;

    @FXML
    private TableView tableImprimir;

    @FXML
    private ImageView welcome;

    //Metodo pra salvar os dados no txt
    @FXML
    void inserirDados(ActionEvent event) {

        String matricula = txtMatricula.getText();
        String nome = txtNome.getText();
        String nota1 = txtNota1.getText();
        String nota2 = txtNota2.getText();
        String nota3 = txtNota3.getText();
        String faltas = txtFaltas.getText();

        String dados = matricula + ", " + nome + ", " + faltas + ", " + nota1 +
                ", " + nota2 + ", " + nota3;

        // Eu tive problema em settar o caminho do arvivo, testa aí no seu.
        String arquivoSaida = "src\\main\\java\\com\\projetoavl\\t2ed2\\saida.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida, true))) {
            writer.write(dados);
            writer.newLine(); // Esse aqui faz ir pra proxima linha, mas ele pula linha vazia se algum dado for apagado.
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Aqui é só pra limpar os campos de textos depois, se não quiser pode tirar.
        txtMatricula.clear();
        txtNome.clear();
        txtNota1.clear();
        txtNota2.clear();
        txtNota3.clear();
        txtFaltas.clear();
    }

    // Sair do programa e salvar as alterações
    @FXML
    void sair(MouseEvent event) {
        Stage currentStage = (Stage) lblSair.getScene().getWindow();
        currentStage.close();
    }


    // Problema: necessita fazer um button para fechar cada panel
    // Fiz o panel que for clicado deixar todos os outros panels invisiveis.
    // Adicionei esse "Map" pra conseguir definir qual é o panel e o label.
    @FXML
    void telaClicada(MouseEvent event) {
        Label labelClicado = (Label) event.getSource();
        Pane panelClicado = null;

        Map<Label, Pane> labelToPanelMap = new HashMap<>();
        labelToPanelMap.put(lblInserir, inserirPanel);
        labelToPanelMap.put(lblRemover, removerPanel);
        labelToPanelMap.put(lblBuscar, buscaPanel);
        labelToPanelMap.put(lblImprimir, imprimirPanel);

        for (Label label : labelToPanelMap.keySet()) {
            Pane panel = labelToPanelMap.get(label);
            if (label == labelClicado) {
                panelClicado = panel;
                panel.setVisible(true);
            } else {
                panel.setVisible(false);
            }
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