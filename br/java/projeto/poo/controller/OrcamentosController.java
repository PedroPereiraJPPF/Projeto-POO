package br.java.projeto.poo.controller;

import br.java.projeto.poo.src.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OrcamentosController extends BaseController {

    @FXML private Button novoOrcamento;
    @FXML private Button gerarRelatorio;
    @FXML private Button voltaTelaInicial;
    @FXML private Button salvarNovoOrcamento;
    @FXML private TextField buscarPlaca;
    @FXML private TextField campoBuscaPeca;
    @FXML private TextField campoBuscaServ;


    @FXML
    public void gerarRelatorio() throws Exception {
        
    }

    @FXML
    public void novoOrcamento() throws Exception {
        App.navegarEntreTelas("novoOrcamento");
    }

    @FXML
    public void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("orcamentos");
    }
}
