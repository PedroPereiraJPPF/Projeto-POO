package br.java.projeto.poo.controller;

import java.io.IOException;

import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;
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

public class ServicosController extends BaseController{

    @FXML private Button novoServico;
    
    @FXML private Button cadastrarServico;
    @FXML private TextField campoCadNome;
    @FXML private TextField campoCadValor;
    @FXML private Button cancelarCadastro;
    @FXML private Label mensagemErroCad;

    @FXML private TextField campoEditNome;
    @FXML private TextField campoEditValor;
    @FXML private Button cancelarEdicao;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;


    @FXML
    void abrirCadastro() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Servicos/CadastrarServico.fxml"));
        Parent root = loader.load();
        Scene janelaCad = new Scene(root);
        Stage palco = new Stage(StageStyle.UNDECORATED);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.setScene(janelaCad);
        Window wNS = novoServico.getScene().getWindow();
        double centralizarEixoX = (wNS.getX() + wNS.getWidth()/2) - 200;
        double centralizarEixoY = (wNS.getY() + wNS.getHeight()/2) - 150;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
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
        Window wCS = cadastrarServico.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
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
        Window wCS = cadastrarServico.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }

    @FXML
    void cadastrarServico() {
        String nome = null;
        double valor = 0;

        if(campoCadNome.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else nome = campoCadNome.getText();

        if(campoCadValor.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else valor = Double.parseDouble(campoCadValor.getText());

        try {
            if(!mensagemErroCad.isVisible()){
                ServicoVO novoServico = new ServicoVO(0, nome, valor);
                
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
    void editarServico() {

    }

    @FXML
    void cancelarEdicao() {
        Stage palco = (Stage)this.cancelarEdicao.getScene().getWindow();
        palco.close();
    }

}
