package br.java.projeto.poo.controller;

import br.java.projeto.poo.src.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/*
 * Carrega funções que todos os controllers possuem
 */

public class BaseController {
    @FXML
    private Label nAuto;
    @FXML
    private Label nClie;
    @FXML
    private Label nFunc;
    @FXML
    private Label nOrcamentos;
    @FXML
    private Label nPecas;
    @FXML
    private Label nSair;
    @FXML
    private Label nServ;
    @FXML
    private HBox menu;


    @FXML
    public void initialize() throws Exception {
        this.verificarPermissoes();
    }

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
        App.usuarioLogado = null;
        App.navegarEntreTelas("login");
    }

    /**
     * @throws Exception
     */
    protected void verificarPermissoes() throws Exception {
        try {
            ObservableList<Node> itensMenu = menu.getChildren();
            if (App.usuarioLogado.getNivel() == 1) {
                itensMenu.remove(nFunc);
                itensMenu.remove(nServ);
                itensMenu.remove(nPecas);
            } else if (App.usuarioLogado.getNivel() == 2) {
                itensMenu.remove(nFunc);
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
