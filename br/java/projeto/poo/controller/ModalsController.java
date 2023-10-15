package br.java.projeto.poo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
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
        CloseModalE();
    }

    public void ExibirMensagemExcluir(String mensagem){
        mensagemExclusao.setText(mensagem);
    }

    public void ExibirMensagemFalha(String mensagem){
        mensagemFalha.setText(mensagem);
    }
    
    public void ExibirMensagemSucesso(String mensagem){
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

    public void abrirModalSucesso(String mensagem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalSucesso.fxml"));
            Parent root = loader.load();
            
            ModalsController controller = loader.getController();
            controller.ExibirMensagemSucesso(mensagem);
            
            Scene popup = new Scene(root);
            Stage palco = new Stage();
            palco.setScene(popup);
            palco.setResizable(false);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.initStyle(StageStyle.UNDECORATED);
            palco.showAndWait();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void abrirModalFalha(String mensagem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalFalha.fxml"));
            Parent root = loader.load();
            
            ModalsController controller = loader.getController();
            controller.ExibirMensagemFalha(mensagem);
            
            Scene popup = new Scene(root);
            Stage palco = new Stage();
            palco.setScene(popup);
            palco.setResizable(false);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.initStyle(StageStyle.UNDECORATED);
            palco.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean abrirModalExcluir(String mensagem, long index) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalExcluir.fxml"));
            Parent root = loader.load();
            ModalsController modalExc = loader.getController();

            modalExc.ExibirMensagemExcluir(mensagem);

            Scene janelaEdit = new Scene(root);
            Stage palco = new Stage();
            palco.setResizable(false);
            palco.setScene(janelaEdit);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.initStyle(StageStyle.UNDECORATED);
            palco.showAndWait();

            return modalExc.getExclusaoValid();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
