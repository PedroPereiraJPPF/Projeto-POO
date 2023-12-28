package br.java.projeto.poo.controller.Funcionarios;

import br.java.projeto.poo.models.BO.FuncionarioBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeletarFuncionarioController {
    private long id;
    private int indice;
    private FuncionarioBO funcionarioBO = new FuncionarioBO();

    @FXML
    private Button deletar;

    @FXML
    private Button fechar;

    @FXML
    private Label texto;

    @FXML
    void deletarFuncionario(ActionEvent event) {
        System.out.println(this.indice);
        funcionarioBO.deletar(id);
        FuncionariosController.funcionariosDisponiveis.remove(this.indice);
        FuncionariosController.listaFuncionarios.remove(this.indice);
        fecharModal();
    }

    @FXML
    void fecharModal(ActionEvent event) {
        fecharModal();
    }

    private void fecharModal() {
        Stage stage = (Stage) this.fechar.getScene().getWindow();
        stage.close();
    }

    public void setDados(long id, int indice) {
        this.id = id;
        this.indice = indice;
    }
}
