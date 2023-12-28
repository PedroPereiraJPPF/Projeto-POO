package br.java.projeto.poo.controller.Orcamentos;

import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.OrcamentoBO;
import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.src.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class OrcamentosController extends BaseController {
    private OrcamentoBO orcamentoBO = new OrcamentoBO();
    static ArrayList<OrcamentoVO> listaOrcamentos;
    static ObservableList<OrcamentoVO> orcamentosDisponiveis;
    private ModalsController modalsController = new ModalsController();

    @FXML private Button novoOrcamento;
    @FXML private Button gerarRelatorio;
    @FXML private TableView<OrcamentoVO> tbOrcamentos;
    @FXML private TableColumn<OrcamentoVO, Double> valor;
    @FXML private TableColumn<OrcamentoVO, String> proprietario;
    @FXML private TableColumn<OrcamentoVO, String> funcionario;
    @FXML private TableColumn<OrcamentoVO, String> placa;
    @FXML private TableColumn<OrcamentoVO, String> acoes;
    @FXML private TextField buscar;

    @FXML
    public void initialize() throws Exception {
        super.initialize();
        listaOrcamentos = this.orcamentoBO.listar();
        orcamentosDisponiveis = FXCollections.observableArrayList(listaOrcamentos); // pega os funcionarios disponiveis no banco de dados
        this.inicializarTabela(); // inicializa os valores da tabela
    }

    @FXML
    public void gerarRelatorio() throws Exception {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Orcamentos/ModalGerarRelatorio.fxml"));
            Parent root = loader.load();
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            Window wNO = novoOrcamento.getScene().getWindow();
            modalStage.setX((wNO.getX() + wNO.getWidth()/2) - 260);
            modalStage.setY((wNO.getY() + wNO.getHeight()/2) - 230);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void buscarOrcamentos(KeyEvent event) {
        try {
            if (buscar.getText().length() > 2) {
                if(buscar.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    orcamentosDisponiveis.setAll(orcamentoBO.buscarPorData(buscar.getText()));
                } else if (buscar.getText().matches("^\\d{3}.*") ){
                    orcamentosDisponiveis.setAll(orcamentoBO.buscarPorCPFCliente(buscar.getText()));
                } else {
                    orcamentosDisponiveis.setAll(orcamentoBO.buscarPorVeiculo(buscar.getText()));
                }
            } else {
                listaOrcamentos = this.orcamentoBO.listar();
                orcamentosDisponiveis.setAll(listaOrcamentos);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void novoOrcamento() throws Exception {
        App.navegarEntreTelas("novoOrcamento");
    }

    // inicializa a tabela
    private void inicializarTabela() throws SQLException {
        proprietario.setCellValueFactory(new PropertyValueFactory<OrcamentoVO, String>("cpfCliente"));
        funcionario.setCellValueFactory(new PropertyValueFactory<OrcamentoVO, String>("cpfFuncionario"));
        valor.setCellValueFactory(new PropertyValueFactory<OrcamentoVO, Double>("valor"));
        placa.setCellValueFactory(new PropertyValueFactory<OrcamentoVO, String>("placaVeiculo"));
        tbOrcamentos.setItems(orcamentosDisponiveis);
        this.inicializarBotoesDeAcao(orcamentosDisponiveis);
    }

    // inicializa os botões de ação
    private void inicializarBotoesDeAcao (ObservableList<OrcamentoVO> funcs) {
        acoes.setCellFactory(param -> new TableCell<>() {
            private final Button btnFinalizar = new Button();
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnFinalizar, btnEdit, btnDelete);

            {
                btnEdit.getStyleClass().add("btn-edit");
                btnDelete.getStyleClass().add("btn-delete");
                btnFinalizar.getStyleClass().add("btn-encerrar");

                btnFinalizar.setOnAction(event -> {
                    try {
                        OrcamentoVO orcamentoVO = getTableView().getItems().get(getIndex());
                        if(modalsController.abrirModalExcluir("Realmente deseja fechar esse orçamento?", getIndex())) {
                            orcamentoBO.encerrarRelatorio(orcamentoVO);
                            orcamentosDisponiveis.remove(getIndex());
                        };
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnEdit.setOnAction(event -> {
                    try {
                        OrcamentoVO orcamentoVO = getTableView().getItems().get(getIndex());
                        abrirTelaEditar(orcamentoVO, getIndex());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnDelete.setOnAction(event -> {
                    try {
                        OrcamentoVO orcamentoVO = getTableView().getItems().get(getIndex());
                        if(modalsController.abrirModalExcluir("Realmente deseja excluir esse orçamento?", getIndex())) {
                            orcamentoBO.deletar(orcamentoVO.getId());
                            orcamentosDisponiveis.remove(getIndex());
                        };
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
                    btnContainer.setStyle("-fx-padding: 0 20 0 5;");
                    btnContainer.setSpacing(10);
                    setGraphic(btnContainer);
                }
            }
        });
    }

    void abrirTelaEditar(OrcamentoVO vo, int indice) throws Exception {
        try {
            Stage stage = (Stage) this.novoOrcamento.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Orcamentos/EditarOrcamento.fxml"));
            Parent root = loader.load();
            EditarOrcamentosController editarController = loader.getController();
            editarController.setDados(vo.getId());
            Scene modalScene = new Scene(root);
            stage.setScene(modalScene);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
