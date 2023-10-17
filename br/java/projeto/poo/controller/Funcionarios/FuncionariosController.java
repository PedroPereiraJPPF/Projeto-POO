package br.java.projeto.poo.controller.Funcionarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.controller.BaseController;
import br.java.projeto.poo.models.BO.FuncionarioBO;
import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionariosController extends BaseController{
    private FuncionarioBO funcionarioBO = new FuncionarioBO(); 
    static ObservableList<FuncionarioVO> funcionariosDisponiveis;
    static ArrayList<FuncionarioVO> listaFuncionarios;

    @FXML private Button cadastrarFuncionario;
    @FXML private TableView<FuncionarioVO> tabelaFuncionarios;
    @FXML private TableColumn<FuncionarioVO, Integer> FuncNivel;
    @FXML private TableColumn<FuncionarioVO, String> funcAcoes;
    @FXML private TableColumn<FuncionarioVO, String> funcAdmi;
    @FXML private TableColumn<FuncionarioVO, String> funcCPF;
    @FXML private TableColumn<FuncionarioVO, String> funcNome;
    @FXML private TableColumn<FuncionarioVO, Double> funcSalario;
    @FXML private TableColumn<FuncionarioVO, Integer> funcId;
    @FXML private TableColumn<FuncionarioVO, String> funcEndereco;
    @FXML private TableColumn<FuncionarioVO, String> funcTelefone;
    @FXML private TextField buscar;

    @FXML
    public void initialize() throws Exception {
        super.initialize();
        listaFuncionarios = this.funcionarioBO.listar();
        funcionariosDisponiveis = FXCollections.observableArrayList(listaFuncionarios); // pega os funcionarios disponiveis no banco de dados
        this.inicializarTabela(); // inicializa os valores da tabela
    }

    @FXML
    void buscarFuncionario(KeyEvent event) {
        try {
            ArrayList<FuncionarioVO> funcionarioVOs;
            if (this.buscar.getText().length() > 2) {
                if (this.buscar.getText().matches("^\\d{3}.*")) {
                    funcionarioVOs = funcionarioBO.buscarPorCPF(this.buscar.getText());
                    funcionariosDisponiveis.setAll(funcionarioVOs);
                } else {
                    funcionarioVOs = funcionarioBO.buscarPorNome(this.buscar.getText());
                    funcionariosDisponiveis.setAll(funcionarioVOs);
                }
            } else {
                funcionariosDisponiveis.setAll(listaFuncionarios);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void abirModalCadastro(ActionEvent event) throws IOException {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UNDECORATED);
        modalStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Funcionarios/CadastrarFuncionario.fxml"));
        Parent root = loader.load();
        Scene modalScene = new Scene(root);
        modalStage.setScene(modalScene);
        Window wNF = cadastrarFuncionario.getScene().getWindow();
        modalStage.setX((wNF.getX() + wNF.getWidth()/2) - 251);
        modalStage.setY((wNF.getY() + wNF.getHeight()/2) - 325);
        modalStage.showAndWait();
    }

    void abrirModalEditar(FuncionarioVO vo, int indice) throws Exception {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Funcionarios/EditarFuncionario.fxml"));
            Parent root = loader.load();
            EditarFuncionariosController editarController = loader.getController();
            editarController.setDados(vo, indice);
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            Window wNF = cadastrarFuncionario.getScene().getWindow();
            modalStage.setX((wNF.getX() + wNF.getWidth()/2) - 250);
            modalStage.setY((wNF.getY() + wNF.getHeight()/2) - 300);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void abrirModalDeletar(long id, int indice) throws Exception {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Funcionarios/ConfirmarDelete.fxml"));
            Parent root = loader.load();
            DeletarFuncionarioController deletar = loader.getController();
            deletar.setDados(id, indice);
            Scene modalScene = new Scene(root);
            modalStage.setScene(modalScene);
            modalStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // inicializa a tabela
    private void inicializarTabela() throws SQLException {
        funcNome.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("nome"));
        FuncNivel.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, Integer>("nivel"));
        funcAdmi.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("dataDeAdimissao"));
        funcCPF.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("cpf"));
        funcSalario.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, Double>("salario"));
        funcId.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, Integer>("id"));
        funcEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("endereco"));
        funcTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("telefone"));
        tabelaFuncionarios.setItems(funcionariosDisponiveis);
        this.inicializarBotoesDeAcao(funcionariosDisponiveis);
    }

    // inicializa os botões de ação
    private void inicializarBotoesDeAcao (ObservableList<FuncionarioVO> funcs) {
        funcAcoes.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();
            private final HBox btnContainer = new HBox(btnEdit, btnDelete);

            {
                btnEdit.getStyleClass().add("btn-edit");
                btnDelete.getStyleClass().add("btn-delete");
                btnEdit.setOnAction(event -> {
                    try {
                        FuncionarioVO funcionario = getTableView().getItems().get(getIndex());
                        System.out.println(funcionario.getId());
                        abrirModalEditar(funcionario, getIndex());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                btnDelete.setOnAction(event -> {
                    try {
                        FuncionarioVO funcionario = getTableView().getItems().get(getIndex());
                        System.out.println(getIndex());
                        abrirModalDeletar(funcionario.getId(), getIndex());
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
                    btnContainer.setAlignment(Pos.CENTER);
                    btnContainer.setSpacing(10);
                    setGraphic(btnContainer);
                }
            }
        });
    }  
}
