package br.java.projeto.poo.controller.Orcamentos;

import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.models.BO.OrcamentoBO;
import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.src.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class OrcamentosController extends BaseController {
    private OrcamentoBO orcamentoBO = new OrcamentoBO();
    static ArrayList<OrcamentoVO> listaOrcamentos;
    static ObservableList<OrcamentoVO> orcamentosDisponiveis;

    @FXML private Button novoOrcamento;
    @FXML private Button gerarRelatorio;
    @FXML private TableView<OrcamentoVO> tbOrcamentos;
    @FXML private TableColumn<OrcamentoVO, Double> valor;
    @FXML private TableColumn<OrcamentoVO, String> proprietario;
    @FXML private TableColumn<OrcamentoVO, String> funcionario;
    @FXML private TableColumn<OrcamentoVO, String> placa;
    @FXML private TableColumn<OrcamentoVO, String> acoes;

    @FXML
    public void initialize() throws Exception {
        super.initialize();
        listaOrcamentos = this.orcamentoBO.listar();
        orcamentosDisponiveis = FXCollections.observableArrayList(listaOrcamentos); // pega os funcionarios disponiveis no banco de dados
        this.inicializarTabela(); // inicializa os valores da tabela
    }

    @FXML
    public void gerarRelatorio() throws Exception {
        
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
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnEdit, btnDelete);

            {
                btnEdit.getStyleClass().add("btn-edit");
                btnDelete.getStyleClass().add("btn-delete");
                btnEdit.setOnAction(event -> {
                    try {
                        // FuncionarioVO funcionario = getTableView().getItems().get(getIndex());
                        // abrirModalEditar(funcionario, getIndex());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnDelete.setOnAction(event -> {
                    try {
                        // FuncionarioVO funcionario = getTableView().getItems().get(getIndex());
                        // System.out.println(getIndex());
                        // abrirModalDeletar(funcionario.getId(), getIndex());
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
