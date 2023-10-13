package br.java.projeto.poo.controller.Funcionarios;

import br.java.projeto.poo.models.BO.EnderecoBO;
import br.java.projeto.poo.models.BO.FuncionarioBO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.FuncionarioVO;
import br.java.projeto.poo.models.VO.TelefoneVO;
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
    private TextField id;

    @FXML
    private TextField telefone;

    @FXML
    void initialize () {
        this.mensagemDeErro.setVisible(false);
        this.dataDeAdmissao.setVisible(false);
    }

    @FXML
    void atualizarFuncionario(ActionEvent event) {
        try {
            this.validarCamposVazios();

            EnderecoVO enderecoFuncionario = new EnderecoVO().pegarValoresComoString(endereco.getText());
            TelefoneVO telefoneFuncionario = new TelefoneVO(0, null, null, telefone.getText());
            FuncionarioVO funcionario = new FuncionarioVO(Long.valueOf(id.getText()), 
            nome.getText(), 
            cpf.getText(), 
            Double.valueOf(salario.getText()), 
            this.dataDeAdmissao.getText(), 
            enderecoFuncionario, 
            Integer.valueOf(nivel.getText()), 
            telefoneFuncionario,
            null);

            funcionarioBO.atualizar(funcionario);
            FuncionariosController.funcionariosDisponiveis.set(this.indice, funcionario);
            FuncionariosController.listaFuncionarios.set(this.indice, funcionario);
            this.fecharModal();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setDados(FuncionarioVO vo, int indice) throws Exception {
       try {
            EnderecoVO enderecoFuncionario = new EnderecoBO().buscarPorFuncionario(vo.getCpf());
            if (Long.valueOf(vo.getId()) <= 0) { // verifica se o funcionario possui um id válido
                this.id.setText(String.valueOf(funcionarioBO.buscarPorCPF(vo.getCpf()).get(0).getId()));
            } else {
                this.id.setText(String.valueOf(vo.getId()));   
            }
            this.cpf.setText(vo.getCpf());
            this.nome.setText(vo.getNome());
            this.salario.setText(String.valueOf(vo.getSalario()));
            this.nivel.setText(String.valueOf(vo.getNivel()));
            this.dataDeAdmissao.setText(vo.getDataDeAdimissao());
            this.indice = indice;
            this.endereco.setText(enderecoFuncionario.toString());
            this.telefone.setText(vo.getTelefone().getNumero());
       } catch (Exception e) {
            System.out.println(e.getMessage());
       }
    }

    private void validarCamposVazios() throws Exception {
        if (cpf.getText().isEmpty()) {
            throw new Exception("CPF não pode ser vazio");
        }
        if (endereco.getText().isEmpty()) {
            throw new Exception("Endereco não pode ser vazio");
        }
        if (nivel.getText().isEmpty()) {
            throw new Exception("Nivel não pode ser vazio");
        }
        if (nome.getText().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        if (salario.getText().isEmpty()) {
            throw new Exception("Salario não pode ser vazio");
        }
        if (telefone.getText().isEmpty()) {
            throw new Exception("O funcionario precisa ter um telefone");
        }
    }

}
