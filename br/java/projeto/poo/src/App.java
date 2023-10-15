package br.java.projeto.poo.src;

import br.java.projeto.poo.models.VO.FuncionarioVO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class App extends Application {
    private static Stage janela;
    private static GerenciadorDeTelas gr = new GerenciadorDeTelas();
    public static FuncionarioVO usuarioLogado = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            janela = primaryStage;
            //File f = new File("public/MarcãoIcon16x16.png");
            Image logo = new Image("file: public/MarcãoIcon32x32.png");
            janela.getIcons().add(logo);
            janela.setResizable(true);
            janela.setMinHeight(800);
            janela.setMinWidth(1024);
            // Scene telaInicial = usuarioLogado != null ? gr.carregarNovaTela("funcionarios") : gr.carregarNovaTela("login"); // carrega a tela inicial logica comentada para testes
            Scene telaInicial = gr.carregarNovaTela("login"); // usar essa por enquanto
            janela.setScene(telaInicial);
            janela.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite navegar entre telas usando o nome disponibilizado em TelasDisponiveis
     * @param nomeTela
     * @throws Exception
     */

    public static void navegarEntreTelas(String nomeTela) {
        janela.setScene(gr.carregarNovaTela(nomeTela)); // carrega a tela com o nome recebido na funçãzo
    }
}