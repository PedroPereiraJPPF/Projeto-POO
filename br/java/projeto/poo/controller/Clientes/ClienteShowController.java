package br.java.projeto.poo.controller.Clientes;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.src.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ClienteShowController extends BaseController{
    
    @FXML protected Label exibirCPF;
    @FXML protected Label exibirCorV;
    @FXML protected Label exibirDataCad;
    @FXML protected Label exibirEndereco;
    @FXML protected Label exibirKmV;
    @FXML protected Label exibirModV;
    @FXML protected Label exibirNome;
    @FXML protected Label exibirPlacaV;
    @FXML protected Button novoVeic;
    @FXML protected Button telaInicial;
    @FXML protected Label nomeClienteMenu;






    @FXML
    void novoVeiculo(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Automoveis/CadastrarAutomoveis.fxml"));
        Parent root = loader.load();

        //ClienteEditController controller = loader.getController();
        //controller.initialize(cliente, i);

        Scene janelaEdit = new Scene(root);
        Stage palco = new Stage();
        palco.setResizable(false);
        palco.setScene(janelaEdit);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wNC = telaInicial.getScene().getWindow();
        double centralizarEixoX, centralizarEixoY;
        centralizarEixoX = (wNC.getX() + wNC.getWidth()/2) - 260;
        centralizarEixoY = (wNC.getY() + wNC.getHeight()/2) - 332;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.show();
    }

    @FXML
    void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("clientes");
    }

}
