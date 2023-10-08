package com.projetoavl.t2ed2.controller;

import com.projetoavl.t2ed2.Global;
import com.projetoavl.t2ed2.model.Aluno;
import com.projetoavl.t2ed2.model.ArvoreAVL;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;

import static com.projetoavl.t2ed2.Global.arvoreAVL;

public class TelaPrincipalController implements Initializable {

    private ArvoreAVL arvore = new ArvoreAVL();
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
    private TextField txtMatriculaBusca;

    @FXML
    private TextField txtMatriculaRemover;

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

    @FXML
    private TableView<Aluno> tableBuscarAluno;

    @FXML
    private TableView<Aluno> tableImprimirAluno;

    @FXML
    private TableView<Aluno> tableRemoverAluno;

    private final TableColumn cellAlunoMatricula= new TableColumn("Matrícula");
    private final TableColumn cellAlunoNome = new TableColumn("Nome");
    private final TableColumn cellAlunoFaltas = new TableColumn("Faltas");
    private final TableColumn cellAlunoNota1 = new TableColumn("Nota 1");
    private final TableColumn cellAlunoNota2 = new TableColumn("Nota 2");
    private final TableColumn cellAlunoNota3 = new TableColumn("Nota 3");
    private final TableColumn cellAlunoMedia = new TableColumn("Média");

    private final String arquivoEntrada = "src/main/java/com/projetoavl/t2ed2/entrada.txt";
    private final String arquivoSaida = "src/main/java/com/projetoavl/t2ed2/saida.txt";

    // Método para carregar os dados do arquivo de entrada e inserir na árvore
    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int matricula = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int faltas = Integer.parseInt(dados[2].trim());
                double nota1 = Double.parseDouble(dados[3].trim());
                double nota2 = Double.parseDouble(dados[4].trim());
                double nota3 = Double.parseDouble(dados[5].trim());

                Aluno aluno = new Aluno(matricula, nome, faltas, nota1, nota2, nota3);
                arvore.inserir(aluno);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
        }
    }
    
    private void formataTabela(){
        cellAlunoMatricula.setMinWidth(55);
        cellAlunoMatricula.setPrefWidth(60);
        cellAlunoMatricula.setResizable(false);
        cellAlunoMatricula.setCellValueFactory (new PropertyValueFactory<>( "matricula" ));
        cellAlunoMatricula.setStyle("-fx-alignment: center;");

        cellAlunoNome.setMinWidth(150);
        cellAlunoNome.setPrefWidth(180);
        cellAlunoNome.setResizable(false);
        cellAlunoNome.setCellValueFactory (new PropertyValueFactory <> ( "nome" ));
        cellAlunoNome.setStyle("-fx-alignment: center;");

        cellAlunoFaltas.setMinWidth(45);
        cellAlunoFaltas.setPrefWidth(50);
        cellAlunoFaltas.setResizable(false);
        cellAlunoFaltas.setCellValueFactory (new PropertyValueFactory <> ( "faltas" ));
        cellAlunoFaltas.setStyle("-fx-alignment: center;");

        cellAlunoNota1.setMinWidth(55);
        cellAlunoNota1.setPrefWidth(60);
        cellAlunoNota1.setResizable(false);
        cellAlunoNota1.setCellValueFactory (new PropertyValueFactory <> ( "nota1" ));
        cellAlunoNota1.setStyle("-fx-alignment: center;");

        cellAlunoNota2.setMinWidth(55);
        cellAlunoNota2.setPrefWidth(60);
        cellAlunoNota2.setResizable(false);
        cellAlunoNota2.setCellValueFactory (new PropertyValueFactory <> ( "nota2" ));
        cellAlunoNota2.setStyle("-fx-alignment: center;");

        cellAlunoNota3.setMinWidth(55);
        cellAlunoNota3.setPrefWidth(60);
        cellAlunoNota3.setResizable(false);
        cellAlunoNota3.setCellValueFactory (new PropertyValueFactory <> ( "nota3" ));
        cellAlunoNota3.setStyle("-fx-alignment: center;");

        cellAlunoMedia.setMinWidth(50);
        cellAlunoMedia.setPrefWidth(50);
        cellAlunoMedia.setResizable(false);
        cellAlunoMedia.setCellValueFactory (new PropertyValueFactory <> ( "media" ));
        cellAlunoMedia.setStyle("-fx-alignment: center;");
    }

    // Método pra carregar a tabela
    private void carregaTabela(TableView<Aluno> tabela, ObservableList<Aluno> list) {
        tabela.getColumns().clear();
        formataTabela();
        tabela.setItems(list);
        tabela.getColumns().addAll(cellAlunoMatricula, cellAlunoNome, cellAlunoFaltas,
                cellAlunoNota1, cellAlunoNota2, cellAlunoNota3, cellAlunoMedia);
    }

    // Método para inserir aluno
    @FXML
    void inserirAluno(ActionEvent event) {
        String matriculaStr = txtMatricula.getText();
        String nome = txtNome.getText();
        String faltasStr = txtFaltas.getText();
        String nota1Str = txtNota1.getText();
        String nota2Str = txtNota2.getText();
        String nota3Str = txtNota3.getText();

        // Verifica se os campos estão preenchidos
        if (matriculaStr.isEmpty() || nome.isEmpty() || faltasStr.isEmpty() || nota1Str.isEmpty()
                || nota2Str.isEmpty() || nota3Str.isEmpty()) {
            exibirErro("Preencha todos os campos!");
            return;
        }

        int matricula = Integer.parseInt(matriculaStr);
        int faltas = Integer.parseInt(faltasStr);
        double nota1 = Double.parseDouble(nota1Str);
        double nota2 = Double.parseDouble(nota2Str);
        double nota3 = Double.parseDouble(nota3Str);

        // Verifica se a matrícula já existe na árvore
        if (arvore.buscar(matricula) != null) {
            exibirErro("Matrícula já existe!");
            txtMatricula.clear();
            return;
        }

        Aluno novoAluno = new Aluno(matricula, nome, faltas, nota1, nota2, nota3);
        arvore.inserir(novoAluno);

        txtMatricula.clear();
        txtNome.clear();
        txtFaltas.clear();
        txtNota1.clear();
        txtNota2.clear();
        txtNota3.clear();

        exibirSucesso("Aluno inserido com sucesso!");
    }

    // Método para remover aluno
    @FXML
    void removerAluno(ActionEvent event) {
        String matriculaRemoverStr = txtMatriculaRemover.getText().trim();

        // Verifica se o campo de matrícula está vazio
        if (matriculaRemoverStr.isEmpty()) {
            exibirErro("Campo de matrícula vazio!");
            return;
        }

        int matriculaRemover = Integer.parseInt(txtMatriculaRemover.getText());
        arvore.remover(matriculaRemover);
        txtMatriculaRemover.clear();
        List<Aluno> alunoList = arvore.obterTodosAlunosEmOrdem();
        carregaTabela(tableRemoverAluno, FXCollections.observableArrayList(alunoList));
    }

    @FXML
    void buscarAluno(ActionEvent event) {
        String matriculaBuscaStr = txtMatriculaBusca.getText().trim();

        // Verifica se o campo de matrícula está vazio
        if (matriculaBuscaStr.isEmpty()) {
            exibirErro("Campo de matrícula vazio!");
            return;
        }

        int matriculaBusca = Integer.parseInt(txtMatriculaBusca.getText());
        Aluno alunoBuscado = arvore.buscar(matriculaBusca);
        if (alunoBuscado != null) {
            List<Aluno> alunoList = new ArrayList<>();
            alunoList.add(alunoBuscado);
            carregaTabela(tableBuscarAluno, FXCollections.observableArrayList(alunoList));
        } else {
            exibirErro("Aluno não encontrado.");
            txtMatriculaBusca.clear();
        }
    }

    // Método para impressão
    @FXML
    public void imprimirAlunos() {
        List<Aluno> alunoList = arvore.obterTodosAlunosEmOrdem();
        carregaTabela(tableImprimirAluno, FXCollections.observableArrayList(alunoList));
    }

    // Encerra o programa e salva as alterações na árvore
    @FXML
    void sair(MouseEvent event) {
        arvore.gravarEmArquivo(arquivoSaida);
        Stage currentStage = (Stage) txtMatricula.getScene().getWindow();
        currentStage.close();
    }

    // Método para o panel que for clicado deixar todos os outros panels invisiveis.
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

    // Método para exibir uma mensagem de erro
    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Método para exibir uma mensagem de sucesso
    private void exibirSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Carregue os dados dos alunos do arquivo de entrada
        carregarDados();

        inserirPanel.setVisible(false);
        removerPanel.setVisible(false);
        buscaPanel.setVisible(false);
        imprimirPanel.setVisible(false);

        // Animações
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(welcome.translateYProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(welcome.translateYProperty(), -50))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Animações
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