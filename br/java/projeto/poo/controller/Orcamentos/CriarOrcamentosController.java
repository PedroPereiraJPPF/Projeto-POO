package br.java.projeto.poo.controller.Orcamentos;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.src.App;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CriarOrcamentosController extends BaseController{
    @FXML private Button voltaTelaInicial;
    @FXML private Button salvarNovoOrcamento;
    @FXML private TextField buscarPlaca;
    @FXML private TextField campoBuscaPeca;
    @FXML private TextField campoBuscaServ;
    @FXML private ListView<PecaVo> pecasBuscadas;
    ObservableList<PecaVo> itens = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception {
        pecasBuscadas.setVisible(false);
        pecasBuscadas.setItems(itens);
        pecasBuscadas.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            
        });
    }
    
    @FXML
    void buscarPecasPorNome(KeyEvent event) {

    }

    @FXML
    public void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("orcamentos");
    }
}
