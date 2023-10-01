package br.java.projeto.poo.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ClienteController extends BaseController {

    @FXML

    Parent root;
    Window stage;
    Popup CadastrarCliente = new Popup();
    Button NovoCliente = new Button();

    public void AbrirPopUp(ActionEvent e) throws IOException{
        
    };
}
