package br.java.projeto.poo.controller.Orcamentos;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.src.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CriarOrcamentosController extends BaseController{
    @FXML private Button voltaTelaInicial;
    @FXML private Button salvarNovoOrcamento;
    @FXML private TextField buscarPlaca;
    @FXML private TextField campoBuscaPeca;
    @FXML private TextField campoBuscaServ;
    
    @FXML
    public void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("orcamentos");
    }
}
