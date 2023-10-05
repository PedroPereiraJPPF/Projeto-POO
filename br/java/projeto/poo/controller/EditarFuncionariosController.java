package br.java.projeto.poo.controller;

import br.java.projeto.poo.models.BO.FuncionarioBO;
import br.java.projeto.poo.models.VO.FuncionarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditarFuncionariosController {
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private int indice;

    @FXML
    private Pane EditarFuncionario;

    @FXML
    private Button cadastrar;

    @FXML
    private Button fechar;

    @FXML
    private TextField cpf;

    @FXML
    private TextField dataDeAdmissao;

    @FXML
    private TextField endereco;

    @FXML
    private Label mensagemDeErro;

    @FXML
    private TextField nivel;

    @FXML
    private TextField nome;

    @FXML
    private TextField salario;

    @FXML
    private Label id;

    @FXML
    void initialize () {
        this.mensagemDeErro.setVisible(false);
        this.dataDeAdmissao.setVisible(false);
    }

    @FXML
    void atualizarFuncionario(ActionEvent event) {
        try {
            FuncionarioVO funcionario = new FuncionarioVO(Long.valueOf(id.getText()), nome.getText(), cpf.getText(), Double.valueOf(salario.getText()), this.dataDeAdmissao.getText(), Integer.valueOf(nivel.getText()));
            funcionarioBO.atualizar(funcionario);
            FuncionariosController.funcionariosDisponiveis.set(this.indice, funcionario);
            this.fecharModal();
        } catch (Exception e) {
            this.mensagemDeErro.setText(e.getMessage());
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

    public void setDados(FuncionarioVO vo, int indice) {
        this.cpf.setText(vo.getCpf());
        this.nome.setText(vo.getNome());
        this.salario.setText(String.valueOf(vo.getSalario()));
        this.endereco.setText("teste");
        this.nivel.setText(String.valueOf(vo.getNivel()));
        this.dataDeAdmissao.setText(vo.getDataDeAdimissao());
        this.id.setText(String.valueOf(vo.getId()));
        this.indice = indice;
    }

}
