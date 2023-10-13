package br.java.projeto.poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModalsController {
    
    private boolean exclusaoValid = false;
    @FXML private Pane ModalSucess;
    @FXML private Pane ModalFail;
    @FXML private Pane ModalExcluir;
    @FXML private Label mensagemSucesso;
    @FXML private Label mensagemFalha;
    @FXML private Label mensagemExclusao;
    @FXML private Button cancelarExclusao;
    @FXML private Button confirmarExclusao;

    @FXML
    private void confirmarExclusao(){
        setExclusaoValid();
    }

    void ExibirMensagemExcluir(String mensagem){
        mensagemExclusao.setText(mensagem);
    }

    void ExibirMensagemFalha(String mensagem){
        mensagemFalha.setText(mensagem);
    }
    
    void ExibirMensagemSucesso(String mensagem){
        mensagemSucesso.setText(mensagem);
    }

    @FXML
    void CloseModalE(){
        Stage palco = (Stage)this.ModalExcluir.getScene().getWindow();
        palco.close();
    }

    @FXML
    void CloseModalS(){
        Stage palco = (Stage)this.ModalSucess.getScene().getWindow();
        palco.close();
    }
    @FXML
    void CloseModalF(){
        Stage palco = (Stage)this.ModalFail.getScene().getWindow();
        palco.close();
    }

    private void setExclusaoValid(){
        this.exclusaoValid = true;
    }

    public boolean getExclusaoValid(){
        return this.exclusaoValid;
    }
}
