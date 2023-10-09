package br.java.projeto.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AutomoveisController extends BaseController{
    @FXML
    private Button cadastrar;

    @FXML
    void abrirModalCadastro(ActionEvent event) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Automoveis/CadastrarAutomoveis.fxml"));
            Parent root = loader.load();
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
