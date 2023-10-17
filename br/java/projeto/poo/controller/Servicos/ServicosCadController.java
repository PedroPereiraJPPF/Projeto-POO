package br.java.projeto.poo.controller.Servicos;

import java.io.IOException;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.ServicoBO;
import br.java.projeto.poo.models.VO.ServicoVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ServicosCadController {
    
    @FXML private Button cadastrarServico;
    @FXML private TextField campoCadNome;
    @FXML private TextField campoCadValor;
    @FXML private Button cancelarCadastro;
    @FXML private Label mensagemErroCad;

    
    
    @FXML
    void abrirModalFail(String mensagem) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalFalha.fxml"));
        Parent root = loader.load();

        ModalsController controller = loader.getController();
        controller.ExibirMensagemFalha(mensagem);

        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCS = cadastrarServico.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }

    @FXML
    void abrirModalSucess(String mensagem) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalSucesso.fxml"));
        Parent root = loader.load();
        
        ModalsController controller = loader.getController();
        controller.ExibirMensagemSucesso(mensagem);
        
        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCS = cadastrarServico.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }

    @FXML
    void cadastrarServico() throws Exception {
        String nome = null;
        double valor = 0;

        if(campoCadNome.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else nome = campoCadNome.getText();

        if(campoCadValor.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else valor = Double.parseDouble(campoCadValor.getText());

        try {
            if(!mensagemErroCad.isVisible()){
                ServicoVO novoServico = new ServicoVO(1, nome, valor);
                ServicoBO servicoBO = new ServicoBO();

                if(servicoBO.inserir(novoServico)){
                    String labelSucesso = "Serviço cadastrado com sucesso.";
                    ServicosController.listaServicos = servicoBO.listar();
                    ServicosController.servicosDisponiveis.setAll(ServicosController.listaServicos);
                    cancelarCadastro();
                    abrirModalSucess(labelSucesso);
                    //cancelarCadastro();
                }
            }
        } catch (Exception ex) {
            String labelFalha = ex.getMessage();
            cancelarCadastro();
            abrirModalFail(labelFalha);
            //cancelarCadastro();
        }
    }

    @FXML
    void cancelarCadastro(){
        Stage palco = (Stage)this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }

    

    @FXML
    void setInvisibleCad(){
        this.mensagemErroCad.setVisible(false);
    }
}
