package br.java.projeto.poo.controller;

import java.io.IOException;

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
    void carregarAutomoveis(MouseEvent event) throws Exception {
        App.navegarEntreTelas("automoveis");
    }

    @FXML
    void carregarClientes(MouseEvent event) throws Exception {
        App.navegarEntreTelas("clientes");
    }   

    @FXML
    void carregarFuncionarios(MouseEvent event) throws Exception {
        App.navegarEntreTelas("funcionarios");
    }

    @FXML
    void carregarOrcamentos(MouseEvent event) throws Exception {
        App.navegarEntreTelas("orcamentos");
    }

    @FXML
    void carregarPecas(MouseEvent event) throws Exception {
        App.navegarEntreTelas("pecas");
    }

    @FXML
    void carregarServicos(MouseEvent event) throws Exception {
        App.navegarEntreTelas("servicos");
    }

    @FXML
    void logout(MouseEvent event) {

    }
}
