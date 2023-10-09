package br.java.projeto.poo.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.FuncionarioVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class AutomoveisController extends BaseController{
    private VeiculoBO veiculoB0 = new VeiculoBO();
    public static ArrayList<VeiculoVO> listaAutomoveis;
    static ObservableList<VeiculoVO> automoveisDisponiveis;

    @FXML
    private Button cadastrar;

    @FXML
    private TableView<VeiculoVO> tbAutomoveis;

    @FXML
    private TableColumn<VeiculoVO, String> placa;

    @FXML
    private TableColumn<VeiculoVO, String> proprietario;

    @FXML
    private TableColumn<VeiculoVO, String> acoes;

    @FXML
    private TableColumn<VeiculoVO, String> modelo;

    @FXML
    private TableColumn<VeiculoVO, Long> id;

    @FXML
    public void initialize () {
        try {
            listaAutomoveis = this.veiculoB0.listar();
            automoveisDisponiveis = FXCollections.observableArrayList(listaAutomoveis);
            inicializarTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void abrirModalCadastro(ActionEvent event) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Automoveis/CadastrarAutomoveis.fxml"));
            Parent root = loader.load();
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void abrirModalEditar(VeiculoVO vo, int indice) throws Exception {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Automoveis/EditarAutomoveis.fxml"));
            Parent root = loader.load();
            EditarAutomoveisController editarController = loader.getController();
            editarController.setDados(vo, indice);
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // inicializa a tabela
    private void inicializarTabela() throws SQLException {
        placa.setCellValueFactory(new PropertyValueFactory<VeiculoVO, String>("placa"));
        proprietario.setCellValueFactory(new PropertyValueFactory<VeiculoVO, String>("cpfDono"));
        modelo.setCellValueFactory(new PropertyValueFactory<VeiculoVO, String>("modelo"));
        id.setCellValueFactory(new PropertyValueFactory<VeiculoVO, Long>("id"));
        tbAutomoveis.setItems(automoveisDisponiveis);
        this.inicializarBotoesDeAcao(automoveisDisponiveis);
    }

    // inicializa os botões de ação
    private void inicializarBotoesDeAcao (ObservableList<VeiculoVO> funcs) {
        acoes.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnEdit, btnDelete);

            {
                btnEdit.getStyleClass().add("btn-edit");
                btnDelete.getStyleClass().add("btn-delete");
                btnEdit.setOnAction(event -> {
                    try {
                        VeiculoVO veiculo = getTableView().getItems().get(getIndex());
                        abrirModalEditar(veiculo, getIndex());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnDelete.setOnAction(event -> {
                    try {
                        VeiculoVO veiculo = getTableView().getItems().get(getIndex());
                        if (!veiculoB0.deletar(veiculo.getId())) {
                            funcs.remove(veiculo);
                        }
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btnContainer.setStyle("-fx-padding: 0 20 0 20;");
                    btnContainer.setSpacing(10);
                    setGraphic(btnContainer);
                }
            }
        });
    }  

}
