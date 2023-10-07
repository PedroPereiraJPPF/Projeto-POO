package br.java.projeto.poo.controller;

import br.java.projeto.poo.src.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * Carrega funções que todos os controllers possuem
 */

public class BaseController {
    protected Stage stage;

    @FXML
    final void carregarAutomoveis(MouseEvent event) throws Exception {
        App.navegarEntreTelas("automoveis");
    }

    @FXML
    final void carregarClientes(MouseEvent event) throws Exception {
        App.navegarEntreTelas("clientes");
    }   

    @FXML
    final void carregarFuncionarios(MouseEvent event) throws Exception {
        App.navegarEntreTelas("funcionarios");
    }

    @FXML
    final void carregarOrcamentos(MouseEvent event) throws Exception {
        App.navegarEntreTelas("orcamentos");
    }

    @FXML
    final void carregarPecas(MouseEvent event) throws Exception {
        App.navegarEntreTelas("pecas");
    }

    @FXML
    final void carregarServicos(MouseEvent event) throws Exception {
        App.navegarEntreTelas("servicos");
    }

    @FXML
    final void logout(MouseEvent event) throws Exception {
        App.navegarEntreTelas("login");
    }
}
