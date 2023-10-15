package br.java.projeto.poo.controller.Clientes;

import java.io.IOException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.ClienteBO;
import br.java.projeto.poo.models.BO.TelefoneBO;
import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.TelefoneVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ClienteCadController {
    
    
    @FXML private Button cadastrarCliente;
    @FXML private Button cancelarCadastro;
    @FXML private ChoiceBox<String> tipoVeic;
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
    @FXML private TextField campoTelefone;
    


    @FXML
    void initialize(){

        tipoVeic.getItems().addAll(tipoVeic_Array);
    
    }






    @FXML
    void abrirModalFail(Label mensagem, Button b) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalFalha.fxml"));
        Parent root = loader.load();

        ModalsController controller = loader.getController();
        controller.ExibirMensagemFalha(mensagem.getText());

        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCC = b.getScene().getWindow();
        double centralizarEixoX = (wCC.getX() + wCC.getWidth()/2) - 200;
        double centralizarEixoY = (wCC.getY() + wCC.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }





    @FXML
    void abrirModalSucess(Label mensagem, Button b, ClienteVO cliente) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalSucesso.fxml"));
        Parent root = loader.load();
        
        ModalsController controller = loader.getController();
        controller.ExibirMensagemSucesso(mensagem.getText());
        
        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCC = b.getScene().getWindow();
        double centralizarEixoX = (wCC.getX() + wCC.getWidth()/2) - 200;
        double centralizarEixoY = (wCC.getY() + wCC.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }




    @FXML
    void cadastrarCliente() throws Exception{
        
        String ano = null, cor = null, cpf = null, endereco = null, modelo = null, nome = null, placa = null, tipoVeic = null, telefone = null;
        double quilometragem = 0;
        long id = 0;
        DropShadow ErrorStyle = new DropShadow();
        ErrorStyle.setBlurType(BlurType.THREE_PASS_BOX);
        ErrorStyle.setColor(Color.RED);
        ErrorStyle.setRadius(10);
        this.mensagemErroCad.setVisible(false);

        if(campoAnoVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);}
        else ano = campoAnoVeic.getText();

        if (this.campoCorVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else cor = campoCorVeic.getText();

        if (this.campoCPFCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else cpf = campoCPFCliente.getText();
        
        if (this.campoEndCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else endereco = campoEndCliente.getText();

        if (this.campoNomeCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else nome = campoNomeCliente.getText();

        if (this.campoModVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else modelo = campoModVeic.getText();

        if (this.campoPlacCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else placa = campoPlacCliente.getText();

        if (this.campoKMVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else quilometragem = Double.parseDouble(campoKMVeic.getText());

        if (this.campoTelefone.getText().isEmpty()){this.mensagemErroCad.setVisible(true);}
        else telefone = this.campoTelefone.getText();
        
        if (this.tipoVeic.getValue() == null){this.mensagemErroCad.setVisible(true);}
        else tipoVeic = this.tipoVeic.getValue();
        

        try{
            if(!this.mensagemErroCad.isVisible()){
                EnderecoVO nEnderecoVO = new EnderecoVO();
                TelefoneBO telefoneBO = new TelefoneBO();
                nEnderecoVO.pegarValoresComoString(endereco);

                //EnderecoBO nEnderecoBO = new EnderecoBO();
                //nEnderecoBO.inserir(nEnderecoVO);
                ArrayList<VeiculoVO> listaveiculos = new ArrayList<VeiculoVO>();

                VeiculoVO veiculo = new VeiculoVO(id, placa, cor, modelo, cpf, tipoVeic, ano, quilometragem);
                listaveiculos.add(veiculo);
                ClienteVO nClienteVO = new ClienteVO(id, nome, cpf, nEnderecoVO, listaveiculos);

                String cpfNull = null;

                ClienteBO clienteBO = new ClienteBO();
                clienteBO.inserir(nClienteVO);

                TelefoneVO telefoneVO = new TelefoneVO(id, cpf, cpfNull, telefone);
                telefoneBO.inserir(telefoneVO);

                VeiculoBO nVeiculoBO = new VeiculoBO();
                nVeiculoBO.inserir(veiculo);

                Label labelSucesso = new Label("Cliente cadastrado com sucesso.");
                cancelarCadastro();
                abrirModalSucess(labelSucesso, cadastrarCliente, nClienteVO);
            }
        }
        catch (Exception ex){
            Label labelFalha = new Label();
            labelFalha.setText(ex.getMessage());
            cancelarCadastro();
            abrirModalFail(labelFalha, cadastrarCliente);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../../controller/Clientes/ClienteController.java"));
            ClienteController controller2 = loader2.load();
            controller2.tabelaClientes.refresh();
            
        }
    }
    
    @FXML
    void cancelarCadastro() throws Exception{
        Stage palco = (Stage) this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }
    




    @FXML
    void setInvisibleCad(){
        this.mensagemErroCad.setVisible(false);
    }

    

}
