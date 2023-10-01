package br.java.projeto.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Popup;

public class ClienteController extends BaseController {

    @FXML

    Popup CadastrarCliente = new Popup();
    Button NovoCliente = new Button();

    public void AbrirPopUp(ActionEvent e){
        CadastrarCliente.show();
    };
}
