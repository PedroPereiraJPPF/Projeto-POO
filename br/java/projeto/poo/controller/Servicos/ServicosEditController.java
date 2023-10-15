package br.java.projeto.poo.controller.Servicos;

import java.io.IOException;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.ServicoBO;
import br.java.projeto.poo.models.VO.ServicoVO;
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

public class ServicosEditController {
    
    private ServicoBO servicoBO = new ServicoBO();
    private ServicoVO servicoEditar;
    @FXML private TextField campoEditNome;
    @FXML private TextField campoEditValor;
    @FXML private Button cancelarEdicao;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;

    
    
    void initialize(ServicoVO servico, int index) throws Exception {
        
        servicoEditar = new ServicoVO(servico.getId(), servico.getNome(), servico.getValor());
        servicoEditar = servico;
        this.preencherCampos(servico, index);
        
        
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
        Window wCS = b.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }

    @FXML
    void abrirModalSucess(Label mensagem, Button b) throws IOException{
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
        Window wCS = b.getScene().getWindow();
        double centralizarEixoX = (wCS.getX() + wCS.getWidth()/2) - 200;
        double centralizarEixoY = (wCS.getY() + wCS.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }
    
    
    
    @FXML
    void editarServico() throws Exception {
        String nome = null; 
        double valor = 0;
        
        if(campoEditNome.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else nome = campoEditNome.getText();

        if(campoEditValor.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else valor = Double.parseDouble(campoEditValor.getText());


        try {
            if(!mensagemErroEdit.isVisible()){
                servicoEditar.setNome(nome);
                servicoEditar.setValor(valor);
                Label labelSucesso = new Label("Peça editada com sucesso.");
                servicoBO.atualizar(servicoEditar);
                cancelarEdicao();
                abrirModalSucess(labelSucesso, salvarEdicao);
            }
        } catch (Exception ex) {
            Label labelFalha = new Label(ex.getMessage());
            cancelarEdicao();
            abrirModalFail(labelFalha, cancelarEdicao);
        }
    }

    @FXML
    void cancelarEdicao() {
        Stage palco = (Stage)this.cancelarEdicao.getScene().getWindow();
        palco.close();
    }


    void preencherCampos(ServicoVO servico, int index){
        
        try{

            campoEditNome.setText(servico.getNome());
            campoEditValor.setText(String.valueOf(servico.getValor()));
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    void setInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }
}
