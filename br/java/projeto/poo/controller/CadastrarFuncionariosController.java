package br.java.projeto.poo.controller;

import java.util.Date;
import java.text.SimpleDateFormat;

import br.java.projeto.poo.models.BO.FuncionarioBO;
import br.java.projeto.poo.models.VO.FuncionarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarFuncionariosController {
    private FuncionarioBO funcionarioBO = new FuncionarioBO();

    @FXML
    private Pane CadastrarFuncionario;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField cpf;

    @FXML
    private TextField endereco;

    @FXML
    private Button fechar;

    @FXML
    private TextField nivel;

    @FXML
    private TextField nome;

    @FXML
    private TextField salario;

    @FXML
    private Label mensagemDeErro;

    @FXML
    void initialize () {
        this.mensagemDeErro.setVisible(false);
    }

    @FXML
    void cadastrarFuncionario(ActionEvent event) throws Exception {
        try {
            Date dataAtual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String dataAtualString = formato.format(dataAtual);
            FuncionarioVO funcionario = new FuncionarioVO(0, nome.getText(), cpf.getText(), Double.valueOf(salario.getText()), dataAtualString, Integer.valueOf(nivel.getText()));
            if(!funcionarioBO.inserir(funcionario)) {
                FuncionariosController.funcionariosDisponiveis.add(0, funcionario);
                this.fecharModal();
            } else {
                this.mensagemDeErro.setVisible(true);
            }
        } catch (Exception e) {
            this.mensagemDeErro.setVisible(true);
        }
    }

    @FXML
    void fecharModal(ActionEvent event) {
        this.fecharModal();
    }

    private void fecharModal() {
        Stage stage = (Stage) this.fechar.getScene().getWindow();
        stage.close();
    }

}
