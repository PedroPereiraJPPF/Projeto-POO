package br.java.projeto.poo.controller;

import java.util.ArrayList;

import br.java.projeto.poo.models.BO.ClienteBO;
import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastrarAutomoveisController {
    VeiculoBO veiculoBO = new VeiculoBO();
    ClienteBO clienteBO = new ClienteBO();
    private boolean clienteExisteFlag = false;

    @FXML private TextField ano;
    @FXML private Button cadastrar;
    @FXML private Button cancelar;
    @FXML private TextField cor;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField km;
    @FXML private TextField modelo;
    @FXML private Label msgErro;
    @FXML private TextField nome;
    @FXML private TextField placa;
    @FXML private ChoiceBox<String> tipo;
    
    @FXML
    void initialize() {
        this.msgErro.setVisible(false);
        tipo.getItems().addAll("Carro", "Moto");
        tipo.setValue("Carro");
    }

    @FXML
    void buscarCliente(KeyEvent event) {
        try {
            if(cpf.getText().length() == 14) {
                ArrayList<ClienteVO> clientes = clienteBO.buscarPorCPF(cpf.getText());
                if(!clientes.isEmpty()) {
                    ClienteVO cliente = clientes.get(0);
                    nome.setText(cliente.getNome());
                    endereco.setText(cliente.getEndereco().toString());
                    clienteExisteFlag = true;
                } else {
                    clienteExisteFlag = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cadastrarVeiculo(ActionEvent event) {
        try {
            this.validarCamposVazios();
            EnderecoVO enderecoCliente = new EnderecoVO().pegarValoresComoString(endereco.getText());
            
            VeiculoVO veiculoVO = new VeiculoVO(0, placa.getText(), cor.getText(), modelo.getText(), cpf.getText(), tipo.getValue(), ano.getText(), Double.valueOf(km.getText()));
            ArrayList<VeiculoVO> listaVeiculos = new ArrayList<VeiculoVO>();
            listaVeiculos.add(veiculoVO);

            ClienteVO ClienteVO = new ClienteVO(0, nome.getText(), cpf.getText(), enderecoCliente, listaVeiculos);

            if (!clienteExisteFlag) {
                clienteBO.inserir(ClienteVO);
            }

            veiculoBO.inserir(veiculoVO);

            AutomoveisController.listaAutomoveis.add(0, veiculoVO);
            AutomoveisController.automoveisDisponiveis.add(0, veiculoVO);

            this.fecharModal();

        } catch (Exception e) {
            this.msgErro.setText(e.getMessage());
            this.msgErro.setVisible(true);
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.fecharModal();
    }

    private void fecharModal() {
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }

    private void validarCamposVazios() throws Exception {
        if (cpf.getText().isEmpty()) {
            throw new Exception("CPF não pode ser vazio");
        }
        if (endereco.getText().isEmpty()) {
            throw new Exception("Endereco não pode ser vazio");
        }
        if (ano.getText().isEmpty()) {
            throw new Exception("Ano não pode ser vazio");
        }
        if (nome.getText().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        if (km.getText().isEmpty()) {
            throw new Exception("Quilometragem não pode ser vazio");
        }
        if (modelo.getText().isEmpty()) {
            throw new Exception("Modelo não pode ser vazio");
        }
        if (placa.getText().isEmpty()) {
            throw new Exception("Placa não pode ser vazia");
        }
        if (cor.getText().isEmpty()) {
            throw new Exception("Cor não pode ser vazio");
        }
        if (tipo.getValue().isEmpty()) {
            throw new Exception("Escolha um tipo");
        }
    }
}
