package br.java.projeto.poo.src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application {
    private static Stage stage;
    private static GerenciadorDeTelas gr = new GerenciadorDeTelas();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Scene telaInicial = gr.carregarNovaTela("orcamentos"); // carrega a tela inicial
        primaryStage.setScene(telaInicial);
        primaryStage.show();
    }

    /**
     * Permite navegar entre telas usando o nome disponibilizado em TelasDisponiveis
     * @param nomeTela
     * @throws Exception
     */

    public static void navegarEntreTelas(String nomeTela) throws Exception {
        stage.setScene(gr.carregarNovaTela(nomeTela)); // carrega a tela com o nome recebido na função
    }
}