package br.java.projeto.poo.controller.Servicos;

import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.ServicoBO;
import br.java.projeto.poo.models.VO.ServicoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ServicosController extends BaseController{

    private ServicoBO servicoBO = new ServicoBO();
    private ArrayList<ServicoVO> listaServicos;
    private ObservableList<ServicoVO> servicosDisponiveis;
    @FXML private Button novoServico;

    @FXML private TableView<ServicoVO> tabelaServicos;
    @FXML private TableColumn<ServicoVO, String> columnBut;
    @FXML private TableColumn<ServicoVO, String> columnServ;
    @FXML private TableColumn<ServicoVO, Double> columnVal;
    


    public void initialize() throws Exception{
        listaServicos = this.servicoBO.listar();
        servicosDisponiveis = FXCollections.observableArrayList(listaServicos);
        this.inicializarTabela();
    }



    @FXML
    void abrirCadastro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Servicos/CadastrarServico.fxml"));
        Parent root = loader.load();
        Scene janelaCad = new Scene(root);
        Stage palco = new Stage(StageStyle.UNDECORATED);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.setScene(janelaCad);
        Window wNS = novoServico.getScene().getWindow();
        double centralizarEixoX = (wNS.getX() + wNS.getWidth()/2) - 200;
        double centralizarEixoY = (wNS.getY() + wNS.getHeight()/2) - 200;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.show();


    }

    @FXML
    void abrirEdicao(ServicoVO servico, int index) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Servicos/EditarServico.fxml"));
        Parent root = loader.load();

        ServicosEditController controller = loader.getController();
        controller.initialize(servico, index);

        Scene janelaCad = new Scene(root);
        Stage palco = new Stage(StageStyle.UNDECORATED);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.setScene(janelaCad);
        Window wNS = novoServico.getScene().getWindow();
        double centralizarEixoX = (wNS.getX() + wNS.getWidth()/2) - 200;
        double centralizarEixoY = (wNS.getY() + wNS.getHeight()/2) - 200;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.show();
    }



    void abrirExclusao(ServicoVO servico, int index) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Modals/ModalExcluir.fxml"));
        Parent root = loader.load();
        ModalsController modalExc = loader.getController();

        String mensagem = "Tem certeza que deseja excluir essa serviço?";

        modalExc.ExibirMensagemExcluir(mensagem);

        Scene janelaEdit = new Scene(root);
        Stage palco = new Stage();
        palco.setResizable(false);
        palco.setScene(janelaEdit);
        palco.initModality(Modality.APPLICATION_MODAL);
        palco.initStyle(StageStyle.UNDECORATED);
        Window wNC = novoServico.getScene().getWindow();
        double centralizarEixoX, centralizarEixoY;
        centralizarEixoX = (wNC.getX() + wNC.getWidth()/2) - 225;
        centralizarEixoY = (wNC.getY() + wNC.getHeight()/2) - 150;
        palco.setX(centralizarEixoX);
        palco.setY(centralizarEixoY);
        palco.showAndWait();

        if(modalExc.getExclusaoValid()){
            realizarExclusao(servico);
        }
    }



    private void inicializarTabela() throws SQLException {
        columnServ.setCellValueFactory(new PropertyValueFactory<ServicoVO, String>("nome"));
        columnVal.setCellValueFactory(new PropertyValueFactory<ServicoVO, Double>("valor"));
        tabelaServicos.setItems(servicosDisponiveis);
        this.inicializarBotoesDeAcao(servicosDisponiveis);
    }

    private void inicializarBotoesDeAcao (ObservableList<ServicoVO> funcs) {
        columnBut.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnEdit, btnDelete);

            {
                btnEdit.getStyleClass().add("btn-edit");
                btnDelete.getStyleClass().add("btn-delete");
                btnEdit.setOnAction(event -> {
                    try {
                        ServicoVO servico = getTableView().getItems().get(getIndex());
                        abrirEdicao(servico, getIndex());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnDelete.setOnAction(event -> {
                    try{
                        ServicoVO servico = getTableView().getItems().get(getIndex());
                        //funcs.remove(cliente);
                        abrirExclusao(servico, getIndex());
                        
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                });
            }

            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    //btnContainer.setStyle("-fx-padding: 0 20 0 20;");
                    btnContainer.setSpacing(10);
                    btnContainer.setAlignment(Pos.CENTER);
                    setGraphic(btnContainer);
                }
            }
        });
    }


    private void realizarExclusao(ServicoVO servico) throws Exception {
        ServicoBO servicoExcluido = new ServicoBO();
            if(servicoExcluido.deletar(servico)){
                servicosDisponiveis.remove(servico);
                //tabelaClientes.refresh();
            }
    }

}
