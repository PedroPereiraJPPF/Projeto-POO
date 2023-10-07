package br.java.projeto.poo.src;

import br.java.projeto.poo.models.VO.FuncionarioVO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class App extends Application {
    private static Stage janela;
    private static GerenciadorDeTelas gr = new GerenciadorDeTelas();
    public static FuncionarioVO usuarioLogado;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        janela = primaryStage;
        //File f = new File("public/MarcãoIcon16x16.png");
        Image logo = new Image("file: public/MarcãoIcon32x32.png");
        janela.getIcons().add(logo);
        janela.setResizable(true);
        janela.setMinHeight(800);
        janela.setMinWidth(1024);
        Scene telaInicial = gr.carregarNovaTela("funcionarios"); // carrega a tela inicial
        janela.setScene(telaInicial);
        janela.show();
    }

    /**
     * Permite navegar entre telas usando o nome disponibilizado em TelasDisponiveis
     * @param nomeTela
     * @throws Exception
     */

    public static void navegarEntreTelas(String nomeTela) throws Exception {
        janela.setScene(gr.carregarNovaTela(nomeTela)); // carrega a tela com o nome recebido na funçãzo
    }
}