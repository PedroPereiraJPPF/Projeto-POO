package br.java.projeto.poo.src;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @implNote serve para carregar as telas do sistema usando as telas disponiveis no arquivo de TelasDisponiveis 
 */

public class GerenciadorDeTelas {
        final private String pastaPadrao = "../views/"; 
        final private TelasDisponiveis telas = new TelasDisponiveis();

        public Scene carregarNovaTela(String nomeTela) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pastaPadrao + telas.pegarTelaPorNome(nomeTela)));
            Parent root = loader.load();
            Scene tela = new Scene(root);
            
            return tela;
        }

        
        
}
