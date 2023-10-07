package br.java.projeto.poo.controller;

import java.io.IOException;

import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class ClienteController extends BaseController{

    // ====== Área da tela principal de clientes ======

    @FXML protected Button novoCliente;
    @FXML private TextField campoBusca;
    protected Popup popUpCadCliente = new Popup();
    

    @FXML
    void AbrirCadastro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Clientes/CadastrarCliente.fxml"));
        Parent root = loader.load();
        Scene janelaCad = new Scene(root);
        Stage palco = new Stage();
        palco.setResizable(false);
        palco.setScene(janelaCad);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wNC = novoCliente.getScene().getWindow();
        palco.setX((wNC.getX() + wNC.getWidth()/2) - 250);
        palco.setY((wNC.getY() + wNC.getHeight()/2) - 325);
        palco.show();
        
    }

    void AbrirEdicao(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Clientes/EditarCliente.fxml"));
        Parent root = loader.load();
        Scene janelaEdit = new Scene(root);
        Stage palco = new Stage();
        palco.setResizable(false);
        palco.setScene(janelaEdit);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wNC = novoCliente.getScene().getWindow();
        palco.setX((wNC.getX() + wNC.getWidth()/2) - 250);
        palco.setY((wNC.getY() + wNC.getHeight()/2) - 225);
        palco.show();
    }

    @FXML
    void buscarCliente(ActionEvent event) {

    }




    // ====== Área da dela de cadastro de clientes ======


    @FXML private Button cadastrarCliente;
    @FXML private Button cancelarCadastro;
    @FXML private ChoiceBox<String> tipoVeic = new ChoiceBox<>();
    @FXML private Label mensagemErroCad;
    @FXML private Pane cadastroCliente;
    private String[] tipoVeic_Array = {"Carro","Moto"};
    @FXML private TextField campoAnoVeic;
    @FXML private TextField campoCPFCliente;
    @FXML private TextField campoCorVeic;
    @FXML private TextField campoEndCliente;
    @FXML private TextField campoKMVeic;
    @FXML private TextField campoModVeic;
    @FXML private TextField campoNomeCliente;
    @FXML private TextField campoPlacCliente;
    


    @FXML
    void initialize(){
        tipoVeic.getItems().addAll(tipoVeic_Array);
        
    }


    @FXML
    void CadastrarCliente() throws Exception{
        
        String ano = null, cor = null, cpf = null, endereco = null, modelo = null, nome = null, placa = null, quilometragem = null, tipoVeic = null;
        long id = 0;
        DropShadow ErrorStyle = new DropShadow();
        ErrorStyle.setBlurType(BlurType.THREE_PASS_BOX);
        ErrorStyle.setColor(Color.RED);
        ErrorStyle.setRadius(10);
        this.mensagemErroCad.setVisible(false);

        if(campoAnoVeic.getText() == null) {this.mensagemErroCad.setVisible(true); campoAnoVeic.setEffect(ErrorStyle);}
        else ano = campoAnoVeic.getText();

        if (this.campoCorVeic.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else cor = campoCorVeic.getText();

        if (this.campoCPFCliente.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else cpf = campoCPFCliente.getText();
        
        if (this.campoEndCliente.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else endereco = campoEndCliente.getText();
        
        if (this.campoModVeic.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else modelo = campoModVeic.getText();

        if (this.campoNomeCliente.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else nome = campoNomeCliente.getText();

        if (this.campoPlacCliente.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else placa = campoPlacCliente.getText();

        if (this.campoKMVeic.getText() == null) {this.mensagemErroCad.setVisible(true);} 
        else quilometragem = campoKMVeic.getText();

        if (this.tipoVeic.getValue() == null){this.mensagemErroCad.setVisible(true);}
        else tipoVeic = this.tipoVeic.getValue();

        try{
            if(!this.mensagemErroCad.isVisible()){
            ClienteVO novoClienteVO = new ClienteVO(id, nome, cpf);
            VeiculoVO nVeiculoVO = new VeiculoVO(id, placa, cor, modelo, cpf, tipoVeic);
            Label labelSucesso = new Label("Cliente cadastrado com sucesso.");
            CancelarCadastro();
            AbrirModalSucess(labelSucesso);
            
        };}
        catch (Exception ex){
            Label labelFalha = new Label();
            labelFalha.setText(ex.getMessage());
            CancelarCadastro();
            AbrirModalFail(labelFalha);
            
        }
    }
    
    @FXML
    void CancelarCadastro() throws Exception{
        Stage palco = (Stage) this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }

    @FXML
    void SetInvisibleCad(){
        this.mensagemErroCad.setVisible(false);
    }




    // ====== Área da tela de edição de clientes ======

    @FXML private TextField campoCPF;
    @FXML private TextField campoEndereco;
    @FXML private TextField campoNome;
    @FXML private Button cancelarEdicao;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;



    @FXML
    void EditarCliente() throws Exception{
        
        String nome = null, cpf = null, endereco = null;
        
        if (this.campoNome.getText() == null) {this.mensagemErroEdit.setVisible(true);} 
        else nome = this.campoNome.getText();

        if (this.campoCPF.getText() == null) {this.mensagemErroEdit.setVisible(true);} 
        else cpf = this.campoCPF.getText();

        if(this.campoEndereco.getText() == null){this.mensagemErroEdit.setVisible(true);}
        else endereco = this.campoEndereco.getText();

        try{
            if (!mensagemErroEdit.isVisible()) {
                ClienteVO clienteVO = new ClienteVO(0, nome, cpf);
                Label labelSucesso = new Label("Cliente editado com sucesso.");
                cancelarEdicao();
                AbrirModalSucess(labelSucesso);
            }
        }
        catch(Exception ex){
            Label labelFalha = new Label();
            labelFalha.setText(ex.getMessage());
            cancelarEdicao();
            AbrirModalFail(labelFalha);
        }
    }

    @FXML
    void cancelarEdicao() throws Exception{
        Stage palco = (Stage) this.cancelarEdicao.getScene().getWindow();
        palco.close();
    }


    @FXML
    void SetInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }


    //  Área dos pop-ups do sucesso ou falha 

    


    @FXML
    void AbrirModalSucess(Label mensagem) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalSucesso.fxml"));
        Parent root = loader.load();
        /*
        Popup popup = new Popup();
        popup.getContent().add(root);
        popup.setHideOnEscape(true);
        popup.setAutoHide(true);
        popup.show(novoCliente.getScene().getWindow(), 0, 0);
        */
        
        ModalsController controller = loader.getController();
        controller.ExibirMensagemSucesso(mensagem.getText());
        
        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCC = cadastrarCliente.getScene().getWindow();
        palco.setX((wCC.getX() + wCC.getWidth()/2) - 200);
        palco.setY((wCC.getY() + wCC.getHeight()/2) - 100);
        palco.showAndWait();
        
        
    }

    @FXML
    void AbrirModalFail(Label mensagem) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalFalha.fxml"));
        Parent root = loader.load();
        /*
        Popup popup = new Popup();
        popup.getContent().add(root);
        popup.setHideOnEscape(true);
        popup.setAutoHide(true);
        popup.show(novoCliente.getScene().getWindow(), 0, 0);
        */

        ModalsController controller = loader.getController();
        controller.ExibirMensagemFalha(mensagem.getText());

        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCC = cadastrarCliente.getScene().getWindow();
        palco.setX((wCC.getX() + wCC.getWidth()/2) - 200);
        palco.setY((wCC.getY() + wCC.getHeight()/2) - 100);
        palco.showAndWait();

    }
}
