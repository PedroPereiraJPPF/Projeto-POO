package br.java.projeto.poo.controller.Clientes;

import java.io.IOException;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.ClienteBO;
import br.java.projeto.poo.models.BO.EnderecoBO;
import br.java.projeto.poo.models.BO.TelefoneBO;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.TelefoneVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ClienteEditController {
    
    private ClienteVO clienteEditar = new ClienteVO();
    @FXML private TextField campoEditCPF;
    @FXML private TextField campoEditEnd;
    @FXML private TextField campoEditNome;
    @FXML private TextField campoEditTel;
    @FXML private Button cancelarEdicao;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;
    
    
    void initialize(ClienteVO cliente, int index){
        
        clienteEditar = new ClienteVO();
        clienteEditar = cliente;
        this.preencherCampos(cliente, index);
            
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
    void abrirModalSucess(Label mensagem, Button b, ClienteVO cliente) throws IOException{
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

        ClienteController.clientesDisponiveis.add(cliente);
        
    }



    
    
    
    @FXML
    void editarCliente() throws Exception{
        
        String nome = null, cpf = null, endereco = null, telefone = null;
        
        if (campoEditNome.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else nome = campoEditNome.getText();

        if (campoEditCPF.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else cpf = campoEditCPF.getText();
        
        if (campoEditEnd.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else endereco = campoEditEnd.getText();
        
        if (campoEditTel.getText().isEmpty()) {mensagemErroEdit.setVisible(true);} 
        else telefone = campoEditTel.getText();



        
        try{
            
            EnderecoVO enderecoVO = new EnderecoVO();
            enderecoVO.pegarValoresComoString(endereco);
            EnderecoBO enderecoBO = new EnderecoBO();
            enderecoBO.atualizar(enderecoVO);

            
            clienteEditar.setNome(nome);
            clienteEditar.setCpf(cpf);
            clienteEditar.setEndereco(enderecoVO);
            ClienteBO clienteBO = new ClienteBO();
            clienteBO.atualizar(clienteEditar);

            String cpfNull = null;
            TelefoneBO telefoneBO = new TelefoneBO();
            TelefoneVO telefoneVO = new TelefoneVO(0, cpf, cpfNull, telefone);
            telefoneBO.atualizar(telefoneVO);

            Label labelSucesso = new Label("Cliente editado com sucesso.");
            cancelarEdicao();
            abrirModalSucess(labelSucesso, salvarEdicao, clienteEditar);

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
            campoEditEnd.setText(cliente.getEndereco().toString());
            campoEditTel.setText(cliente.getTelefone().toString());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }



    
    @FXML
    void setInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }

}
