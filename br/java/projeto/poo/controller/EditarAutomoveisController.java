package br.java.projeto.poo.controller;

import br.java.projeto.poo.models.BO.EnderecoBO;
import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarAutomoveisController {
    VeiculoBO veiculoBO = new VeiculoBO();
    int indice = 0;
    String cpfDono;
    
    @FXML
    private Label id;

    @FXML
    private TextField ano;

    @FXML
    private Button cadastrar;

    @FXML
    private Button cancelar;

    @FXML
    private TextField cor;

    @FXML
    private TextField km;

    @FXML
    private TextField modelo;

    @FXML
    private Label msgErro;

    @FXML
    private TextField placa;

    @FXML 
    private ChoiceBox<String> tipo;

    @FXML
    void initialize() {
        this.id.setVisible(false);
        this.msgErro.setVisible(false);
        tipo.getItems().addAll("Carro", "Moto");
        tipo.setValue("Carro");
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.fecharModal();
    }

    private void fecharModal() {
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editarVeiculo(ActionEvent event) {
        try {
            VeiculoVO veiculo = new VeiculoVO(Integer.valueOf(id.getText()),
            placa.getText(),
            cor.getText(),
            modelo.getText(),
            cpfDono, tipo.getValue(), ano.getText(), Double.valueOf(km.getText()));

            veiculoBO.atualizar(veiculo);
            AutomoveisController.listaAutomoveis.set(indice, veiculo);
            AutomoveisController.automoveisDisponiveis.set(indice, veiculo);

            this.fecharModal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.msgErro.setText(e.getMessage());
            this.msgErro.setVisible(true);
        }
    }

    public void setDados(VeiculoVO vo, int indice) {
        try {
            if (Long.valueOf(vo.getId()) <= 0) { // verifica se o funcionario possui um id válido
                this.id.setText(String.valueOf(veiculoBO.buscarPorPlaca(vo.getPlaca()).get(0).getId()));
            } else {
                this.id.setText(String.valueOf(vo.getId()));   
            } 

            ano.setText(vo.getAno());
            placa.setText(vo.getPlaca());
            modelo.setText(vo.getModelo());
            tipo.setValue(vo.getTipo());
            cor.setText(vo.getCor());
            km.setText(String.valueOf(vo.getKm()));
            cpfDono = vo.getCpfDono();
            this.indice = indice;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
