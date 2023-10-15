package br.java.projeto.poo.controller.Pecas;

import java.io.IOException;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.PecaBO;
import br.java.projeto.poo.models.VO.PecaVo;
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

public class PecasCadController {
    
    @FXML private TextField campoCadFabricante;
    @FXML private TextField campoCadNome;
    @FXML private TextField campoCadValor;
    @FXML private TextField campoCadQuantidade;
    @FXML private Label mensagemErroCad;
    @FXML private Button cadastrarPeca;
    @FXML private Button cancelarCadastro;
    




    @FXML
    void abrirModalFail(String mensagem, Button b) throws Exception{
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
        Window wCP = b.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }

    @FXML
    void abrirModalSucess(String mensagem, Button b) throws IOException{
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
        Window wCP = b.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }



    
    @FXML
    void cadastrarPeca() throws Exception{
        String nome = null, fabricante = null;
        int quantidade = 0; 
        double valor = 0;

        if(campoCadNome.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else nome = campoCadNome.getText();

        if(campoCadFabricante.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else fabricante = campoCadFabricante.getText();

        if(campoCadQuantidade.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else quantidade = Integer.parseInt(campoCadQuantidade.getText());

        if(campoCadValor.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else valor = Double.parseDouble(campoCadValor.getText());

        try {
            if(!mensagemErroCad.isVisible()){
                PecaVo novaPeca = new PecaVo(0, nome, fabricante, valor, quantidade);
                PecaBO pecaBO = new PecaBO();
                if(pecaBO.inserir(novaPeca)){
                    String labelSucesso = "Peça cadastrada com sucesso.";
                    cancelarCadastro();
                    abrirModalSucess(labelSucesso, cadastrarPeca);
                }
            }
        } catch (Exception ex) {
            String labelFalha = ex.getMessage();
            cancelarCadastro();
            abrirModalFail(labelFalha, cancelarCadastro);
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
