package br.java.projeto.poo.controller.Pecas;

import java.io.IOException;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.PecaBO;
import br.java.projeto.poo.models.VO.PecaVo;
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

public class PecasEditController {
    
    private PecaVo pecaEditar;
    private PecaBO pecaBO = new PecaBO();
    @FXML private TextField campoEditFabricante;
    @FXML private TextField campoEditNome;
    @FXML private TextField campoEditValor;
    @FXML private TextField campoEditQuantidade;
    @FXML private Label mensagemErroEdit;
    @FXML private Button salvarEdicao;
    @FXML private Button cancelarEdicao;



    




    void initialize(PecaVo peca, int index) throws Exception {
        pecaEditar = new PecaVo();
        pecaEditar = peca;
        this.preencherCampos(peca, index);
    }





    @FXML
    void abrirModalFail(String mensagem, Button b) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalFalha.fxml"));
        Parent root = loader.load();

        ModalsController controller = loader.getController();
        controller.ExibirMensagemFalha(mensagem);

        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCP = b.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

    }

    @FXML
    void abrirModalSucess(String mensagem, Button b) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalSucesso.fxml"));
        Parent root = loader.load();
        
        ModalsController controller = loader.getController();
        controller.ExibirMensagemSucesso(mensagem);
        
        Scene popup = new Scene(root);
        Stage palco = new Stage();
        palco.setScene(popup);
        palco.setResizable(false);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wCP = b.getScene().getWindow();
        double centralizarEixoX = (wCP.getX() + wCP.getWidth()/2) - 200;
        double centralizarEixoY = (wCP.getY() + wCP.getHeight()/2) - 100;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();
        
    }








    @FXML
    void editarPeca() throws Exception{
        String nome = null, fabricante = null;
        int quantidade = 0; 
        double valor = 0;
        
        if(campoEditNome.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else nome = campoEditNome.getText();

        if(campoEditFabricante.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else fabricante = campoEditFabricante.getText();

        if(campoEditQuantidade.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else quantidade = Integer.parseInt(campoEditQuantidade.getText());

        if(campoEditValor.getText().isEmpty()) {this.mensagemErroEdit.setVisible(true);}
        else valor = Double.parseDouble(campoEditValor.getText());

        try {
            if(!mensagemErroEdit.isVisible()){
                pecaEditar.setNome(nome);
                pecaEditar.setFabricante(fabricante);
                pecaEditar.setQuantidade(quantidade);
                pecaEditar.setValor(valor);
                String labelSucesso = "Peça editada com sucesso.";
                pecaBO.atualizar(pecaEditar);
                cancelarEdicao();
                abrirModalSucess(labelSucesso, salvarEdicao);
            }
        } catch (Exception ex) {
            String labelFalha = ex.getMessage();
            cancelarEdicao();
            abrirModalFail(labelFalha, cancelarEdicao);
        }
    }

    @FXML
    void cancelarEdicao(){
        Stage palco = (Stage)this.cancelarEdicao.getScene().getWindow();
        palco.close();
    }





    @FXML
    void setInvisibleEdit(){
        this.mensagemErroEdit.setVisible(false);
    }




    void preencherCampos(PecaVo peca, int index){
        
        try{

            campoEditNome.setText(peca.getNome());
            campoEditFabricante.setText(peca.getFabricante());
            campoEditQuantidade.setText(String.valueOf(peca.getQuantidade()));
            campoEditValor.setText(String.valueOf(peca.getValor()));
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
