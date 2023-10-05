package com.projetoavl.t2ed2.view;

import com.projetoavl.t2ed2.Global;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

public class TelaInicial extends Application {
    private static Stage stage;
    public static Scene scene;

    public void start(Stage t) throws Exception  {
        stage = new Stage();

        stage.setResizable(false);

        Parent painel = FXMLLoader.load(Objects.requireNonNull(Global.class.getResource("TelaInicial.fxml")));
        scene = new Scene(painel);

        stage.setTitle("Tela Inicial");
        // stage.getIcons().add(new Image(Objects.requireNonNull(TelaInicial.class.getResourceAsStream("img/logo.png"))));
        stage.show();

        stage.setScene(scene);

        stage.setOnCloseRequest((WindowEvent t1) -> {
            t1.consume();
            stage.close();
        });

    }


    /**
     * O método principal que inicia a aplicação.
     *
     * @param args os argumentos da linha de comando
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Obtém o estágio da janela da tela principal.
     *
     * @return o estágio da janela
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Obtém a cena da tela principal.
     *
     * @return a cena da tela
     */
    public static Scene getScene(){
        return scene;
    }

}
