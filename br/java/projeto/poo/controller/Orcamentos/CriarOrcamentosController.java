package br.java.projeto.poo.controller.Orcamentos;

import java.sql.SQLException;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.models.BO.PecaBO;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;
import br.java.projeto.poo.src.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class CriarOrcamentosController extends BaseController{
    PecaBO pecaBo = new PecaBO();
    
    @FXML private TableView<PecaVo> tbPecas;
    @FXML private TableColumn<PecaVo, String> acaoPeca;
    @FXML private TableColumn<PecaVo, String> nomePeca;
    @FXML private TableColumn<PecaVo, Double> valorPeca;
    @FXML private TableColumn<PecaVo, Integer> quantidade;
    @FXML private Button voltaTelaInicial;
    @FXML private Button salvarNovoOrcamento;
    @FXML private TextField buscarPlaca;
    @FXML private TextField campoBuscaPeca;
    @FXML private TextField campoBuscaServ;
    @FXML private ListView<PecaVo> pecasBuscadas;
    @FXML private Label dadosCliente;
    @FXML private TableView<ServicoVO> tbServicos;
    @FXML private TableColumn<?, ?> servicoAcao;
    @FXML private TableColumn<?, ?> servicoNome;
    @FXML private TableColumn<?, ?> servicoValor;
    @FXML private ListView<ServicoVO> servicosBuscados;

    ObservableList<PecaVo> itens = FXCollections.observableArrayList();
    ObservableList<PecaVo> pecasEscolhidas = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception {
        tbPecas.setItems(pecasEscolhidas);
        pecasBuscadas.setVisible(false);
        pecasBuscadas.setItems(itens);
        pecasBuscadas.setOnMouseClicked(event -> {
            this.atualizarValoresPecas();
        });

        inicializarTabela();
    }
    
    @FXML
    void buscarPecasPorNome(KeyEvent event) {
        try {
            if (campoBuscaPeca.getText().length() > 0) {
                PecaVo vo = new PecaVo();
                vo.setNome(campoBuscaPeca.getText());
                itens.setAll(pecaBo.buscarPorNome(vo));
                pecasBuscadas.setVisible(true);
            } else {
                pecasBuscadas.setVisible(false);
                itens.removeAll();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void atualizarValoresPecas() {
        try {
            PecaVo peca = pecasBuscadas.getSelectionModel().getSelectedItem();
            if (!pecasEscolhidas.contains(peca)) {
                peca.setQuantidade(1);
                pecasEscolhidas.add(peca);
            } else {
                int indice = pecasEscolhidas.indexOf(peca);
                PecaVo pecaAdicionada = pecasEscolhidas.get(indice);
                pecaAdicionada.setQuantidade(pecaAdicionada.getQuantidade() + 1);
                
                pecasEscolhidas.set(indice, pecaAdicionada);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void inicializarTabela() throws SQLException {
        nomePeca.setCellValueFactory(new PropertyValueFactory<PecaVo, String>("nome"));
        valorPeca.setCellValueFactory(new PropertyValueFactory<PecaVo, Double>("valor"));
        quantidade.setCellValueFactory(new PropertyValueFactory<PecaVo, Integer>("quantidade"));
        this.inicializarBotoesDeAcao(itens);
    }

    private void inicializarBotoesDeAcao (ObservableList<PecaVo> funcs) {
        acaoPeca.setCellFactory(param -> new TableCell<>() {
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnDelete);

            {
                btnDelete.getStyleClass().add("btn-delete");
                btnDelete.setOnAction(event -> {
                    try {
                        PecaVo peca = getTableView().getItems().get(getIndex());
                        if (peca.getQuantidade() > 1) {
                            peca.setQuantidade(peca.getQuantidade() - 1);
                            pecasEscolhidas.set(getIndex(), peca);
                        } else {
                            pecasEscolhidas.remove(getIndex());
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

    @FXML
    public void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("orcamentos");
    }
}
