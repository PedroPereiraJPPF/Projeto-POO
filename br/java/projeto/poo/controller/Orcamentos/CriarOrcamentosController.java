package br.java.projeto.poo.controller.Orcamentos;

import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.exceptions.InvalidQuantidadeException;
import br.java.projeto.poo.models.BO.PecaBO;
import br.java.projeto.poo.models.BO.VeiculoBO;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;
import br.java.projeto.poo.models.VO.VeiculoVO;
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
    VeiculoBO veiculoBO = new VeiculoBO();
    @FXML private TableView<PecaVo> tbPecas;
    @FXML private TableColumn<PecaVo, String> acaoPeca;
    @FXML private TableColumn<PecaVo, String> nomePeca;
    @FXML private TableColumn<PecaVo, Double> valorPeca;
    @FXML private TableColumn<PecaVo, Integer> quantidade;
    @FXML private Button voltaTelaInicial;
    @FXML private Button salvarNovoOrcamento;
    @FXML private TextField buscarVeiculo;
    @FXML private TextField campoBuscaPeca;
    @FXML private TextField campoBuscaServ;
    @FXML private ListView<PecaVo> pecasBuscadas;
    @FXML private Label dadosCliente;
    @FXML private TableView<ServicoVO> tbServicos;
    @FXML private TableColumn<?, ?> servicoAcao;
    @FXML private TableColumn<?, ?> servicoNome;
    @FXML private TableColumn<?, ?> servicoValor;
    @FXML private ListView<ServicoVO> servicosBuscados;
    @FXML private Label msgErro;

    ObservableList<PecaVo> itens = FXCollections.observableArrayList();
    ObservableList<PecaVo> pecasEscolhidas = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception {
        tbPecas.setItems(pecasEscolhidas);
        pecasBuscadas.setVisible(false);
        servicosBuscados.setVisible(false);
        salvarNovoOrcamento.setDisable(true);
        pecasBuscadas.setItems(itens);
        pecasBuscadas.setOnMouseClicked(event -> {
            this.atualizarValoresPecas();
        });

        inicializarTabelas();
    }

    @FXML
    void buscarDadosDoVeiculo(KeyEvent event) {
        try {
            if(buscarVeiculo.getText().length() > 7) {
                ArrayList<VeiculoVO> veiculos = veiculoBO.buscarPorPlaca(buscarVeiculo.getText());
                if(veiculos.get(0).getId() > 0) {
                    dadosCliente.setText("Modelo: " + veiculos.get(0).getModelo() + " - CPF dono: " + veiculos.get(0).getCpfDono());
                    salvarNovoOrcamento.setDisable(false);
                }
            } else {
                salvarNovoOrcamento.setDisable(true);
                dadosCliente.setText("");
            }
        } catch (Exception e) {
            dadosCliente.setText("Veiculo não encontrado");
        } 
    }

    // funçoes para configurar a tabela de peças
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
            msgErro.setVisible(false);
            PecaVo peca = pecasBuscadas.getSelectionModel().getSelectedItem();
            int indice = pecasEscolhidas.indexOf(peca);
            
            if(peca.getQuantidade() == 0) {
                throw new InvalidQuantidadeException("Sem estoque para essa peça");
            }

            if (!pecasEscolhidas.contains(peca)) {
                pecasEscolhidas.add(new PecaVo(peca.getId(), peca.getNome(), peca.getFabricante(), peca.getValor(), 1));
            } else {
                PecaVo pecaAdicionada = pecasEscolhidas.get(indice);
                if (peca.getQuantidade() == pecaAdicionada.getQuantidade()) {
                    throw new InvalidQuantidadeException("Sem estoque para essa peça");
                }
                pecaAdicionada.setQuantidade(pecaAdicionada.getQuantidade() + 1);
                pecasEscolhidas.set(indice, pecaAdicionada);
            }
        } catch (Exception e) {
            msgErro.setText(e.getMessage());
            msgErro.setVisible(true);
        }
    }

    private void inicializarBotoesDeAcaoPeca (ObservableList<PecaVo> funcs) {
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

    private void inicializarTabelas() throws SQLException {
        nomePeca.setCellValueFactory(new PropertyValueFactory<PecaVo, String>("nome"));
        valorPeca.setCellValueFactory(new PropertyValueFactory<PecaVo, Double>("valor"));
        quantidade.setCellValueFactory(new PropertyValueFactory<PecaVo, Integer>("quantidade"));
        this.inicializarBotoesDeAcaoPeca(itens);
    }

    @FXML
    public void voltaTelaInicial() throws Exception {
        App.navegarEntreTelas("orcamentos");
    }
}
