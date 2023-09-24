package br.java.projeto.poo.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button logar;

    @FXML
    private TextField nomeUsuario;

    @FXML
    private TextField senha;

    @FXML
    void logarUsuario(ActionEvent event) {
        if(nomeUsuario.getText().equals("teste")) {
            System.out.println("deu certo");
        }
    }

}
