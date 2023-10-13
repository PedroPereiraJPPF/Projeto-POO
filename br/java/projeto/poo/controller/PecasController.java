package br.java.projeto.poo.controller;

import java.io.IOException;

import br.java.projeto.poo.models.BO.PecaBO;
import br.java.projeto.poo.models.VO.PecaVo;
import javafx.event.ActionEvent;
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

public class PecasController extends BaseController {
    
    @FXML private TextField buscarPeca;
    @FXML private Button novaPeca;

    @FXML private TextField campoCadFabricante;
    @FXML private TextField campoCadNome;
    @FXML private TextField campoCadValor;
    @FXML private TextField campoCadQuantidade;
    @FXML private Label mensagemErroCad;
    @FXML private Button cadastrarPeca;
    @FXML private Button cancelarCadastro;

    @FXML private TextField campoEditFabricante;
    @FXML private TextField campoEditNome;
    @FXML private TextField campoEditValor;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;
    @FXML private Button cancelarEdicao;


    @FXML
    void abrirCadastro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Pecas/CadastrarPeca.fxml"));
        Parent root = loader.load();
        Scene janelaCad = new Scene(root);
        Stage palco = new Stage(StageStyle.UNDECORATED);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.setScene(janelaCad);
        Window wNP = novaPeca.getScene().getWindow();
        double centralizarEixoX = (wNP.getX() + wNP.getWidth()/2) - 200;
        double centralizarEixoY = (wNP.getY() + wNP.getHeight()/2) - 200;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.show();
    }

    @FXML
    void abrirEdicao() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Pecas/EditarPeca.fxml"));
        Parent root = loader.load();
        Scene janelaCad = new Scene(root);
        Stage palco = new Stage(StageStyle.UNDECORATED);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.setScene(janelaCad);
        palco.show();
    }

    @FXML
    void abrirModalFail(Label mensagem) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalFalha.fxml"));
        Parent root = loader.load();

        ModalsController controller = loader.getController();
        controller.ExibirMensagemFalha(mensagem.getText());

        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCP = cadastrarPeca.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }

    @FXML
    void abrirModalSucess(Label mensagem) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalSucesso.fxml"));
        Parent root = loader.load();
        
        ModalsController controller = loader.getController();
        controller.ExibirMensagemSucesso(mensagem.getText());
        
        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCP = cadastrarPeca.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }

    @FXML
    void buscarPeca() {

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
                pecaBO.inserir(novaPeca);
            }
        } catch (Exception ex) {
            Label labelFalha = new Label(ex.getMessage());
            cancelarCadastro();
        }
    }

    @FXML
    void cancelarCadastro(){
        Stage palco = (Stage)this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }

    @FXML
    void editarPeca() throws Exception{
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
                
            }
        } catch (Exception ex) {
            Label labelFalha = new Label(ex.getMessage());
            cancelarCadastro();
        }
    }

    @FXML
    void cancelarEdicao(){
        Stage palco = (Stage)this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }

    @FXML
    void setInvisibleCad(){
        this.mensagemErroCad.setVisible(false);
    }

    @FXML
    void setInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }
  
}
