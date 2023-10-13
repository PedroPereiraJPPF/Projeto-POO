package br.java.projeto.poo.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.java.projeto.poo.models.BO.ClienteBO;
import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
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

public class ClienteSubController {
    // ====== campos da tela de cadastro de clientes ======
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
    // ====================================================

    // ====== campos da tela de edição de clientes ======
    private ClienteVO clienteEditar;
    @FXML private TextField campoEditCPF;
    @FXML private TextField campoEditEnd;
    @FXML private TextField campoEditNome;
    @FXML private TextField idCliente;
    @FXML private Button cancelarEdicao;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;
    // ==================================================







    @FXML
    void initialize(ClienteVO cliente, int index){
        tipoVeic.getItems().addAll(tipoVeic_Array);
        clienteEditar = new ClienteVO();
        clienteEditar = cliente;
        this.preencherCampos(cliente, index);
        //System.out.println(clienteEditar.getNome() + "\n" + clienteEditar.getCpf() + "\n" + clienteEditar.getId());
    }







    @FXML
    void abrirModalFail(Label mensagem, Button b) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalFalha.fxml"));
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
    void abrirModalSucess(Label mensagem, Button b) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Modals/ModalSucesso.fxml"));
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
        
        String ano = null, cor = null, cpf = null, endereco = null, modelo = null, nome = null, placa = null, tipoVeic = null;
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
        
        if (this.campoModVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else modelo = campoModVeic.getText();

        if (this.campoNomeCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else nome = campoNomeCliente.getText();

        if (this.campoPlacCliente.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else placa = campoPlacCliente.getText();

        if (this.campoKMVeic.getText().isEmpty()) {this.mensagemErroCad.setVisible(true);} 
        else quilometragem = Double.parseDouble(campoKMVeic.getText());

        if (this.tipoVeic.getValue() == null){this.mensagemErroCad.setVisible(true);}
        else tipoVeic = this.tipoVeic.getValue();

        try{
            if(!this.mensagemErroCad.isVisible()){
                EnderecoVO nEnderecoVO = new EnderecoVO();
                nEnderecoVO.pegarValoresComoString(endereco);

                //EnderecoBO nEnderecoBO = new EnderecoBO();
                //nEnderecoBO.inserir(nEnderecoVO);
                ArrayList<VeiculoVO> listaveiculos = new ArrayList<VeiculoVO>();

                VeiculoVO veiculo = new VeiculoVO(id, placa, cor, modelo, cpf, tipoVeic, ano, quilometragem);
                listaveiculos.add(veiculo);
                ClienteVO nClienteVO = new ClienteVO(id, nome, cpf, nEnderecoVO, listaveiculos);

                ClienteBO clienteBO = new ClienteBO();
                clienteBO.inserir(nClienteVO);

                VeiculoBO nVeiculoBO = new VeiculoBO();
                nVeiculoBO.inserir(veiculo);

                Label labelSucesso = new Label("Cliente cadastrado com sucesso.");
                cancelarCadastro();
                abrirModalSucess(labelSucesso, cadastrarCliente);
            }
        }
        catch (Exception ex){
            Label labelFalha = new Label();
            labelFalha.setText(ex.getMessage());
            cancelarCadastro();
            abrirModalFail(labelFalha, cadastrarCliente);
            
        }
    }
    
    @FXML
    void cancelarCadastro() throws Exception{
        Stage palco = (Stage) this.cancelarCadastro.getScene().getWindow();
        palco.close();
    }
    




    @FXML
    void editarCliente() throws Exception{
        
        String nome = null, cpf = null, endereco = null;

        //System.out.println(clienteEditar.getNome() + "\n" + clienteEditar.getCpf());
        
        if (campoEditNome.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else nome = campoEditNome.getText();

        if (campoEditCPF.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else cpf = campoEditCPF.getText();
        
        try{
            
            
            clienteEditar.setNome(nome);
            clienteEditar.setCpf(cpf);
            clienteEditar.setEndereco(null);
            ClienteBO bo = new ClienteBO();
            bo.atualizar(clienteEditar);
            Label labelSucesso = new Label("Cliente editado com sucesso.");
            cancelarEdicao();
            abrirModalSucess(labelSucesso, salvarEdicao);

        }
        catch(Exception ex){
            Label labelFalha = new Label();
            labelFalha.setText(ex.getMessage());
            System.out.println(ex.getMessage());
            cancelarEdicao();
            abrirModalFail(labelFalha, salvarEdicao);
        }
    }

    @FXML
    void cancelarEdicao() throws Exception{
        Stage palco = (Stage) this.cancelarEdicao.getScene().getWindow();
        palco.close();
    }

    




    void preencherCampos(ClienteVO cliente, int index){
        try{

            campoEditNome.setText(cliente.getNome());
            campoEditCPF.setText(cliente.getCpf());
            //idCliente.setText(String.valueOf(cliente.getId()));
            //campoEditEnd.setText(cliente.getEndereco().toString());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }






    @FXML
    void setInvisibleCad(){
        this.mensagemErroCad.setVisible(false);
    }

    @FXML
    void setInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }

}
