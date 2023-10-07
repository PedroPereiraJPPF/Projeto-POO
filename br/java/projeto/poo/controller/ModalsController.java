package br.java.projeto.poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModalsController {
    
    @FXML private Pane ModalSucess;
    @FXML private Pane ModalFail;
    @FXML private Label mensagemSucesso;
    @FXML private Label mensagemFalha;

    void ExibirMensagemFalha(String mensagem){
        mensagemFalha.setText(mensagem);
    }
    
    void ExibirMensagemSucesso(String mensagem){
        mensagemSucesso.setText(mensagem);
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
}
