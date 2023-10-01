package br.java.projeto.poo.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import br.java.projeto.poo.models.BO.FuncionarioBO;
import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionariosController extends BaseController{
    private FuncionarioBO funcionarioBO = new FuncionarioBO(); 
   
    @FXML
    private TableView<FuncionarioVO> tabelaFuncionarios;
    
    @FXML
    private TableColumn<FuncionarioVO, Integer> FuncNivel;

    @FXML
    private TableColumn<FuncionarioVO, String> funcAcoes;

    @FXML
    private TableColumn<FuncionarioVO, String> funcAdmi;

    @FXML
    private TableColumn<FuncionarioVO, String> funcCPF;

    @FXML
    private TableColumn<FuncionarioVO, String> funcNome;

    @FXML
    private TableColumn<FuncionarioVO, Double> funcSalario;

    @FXML
    public void initialize() throws SQLException {
        funcNome.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("nome"));
        FuncNivel.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, Integer>("nivel"));
        funcAdmi.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("dataDeAdimissao"));
        funcCPF.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, String>("cpf"));
        funcSalario.setCellValueFactory(new PropertyValueFactory<FuncionarioVO, Double>("salario"));

        funcAcoes.setCellFactory(param -> new TableCell<>() {
        private final Button btnAcao = new Button("Ação");

        {
            btnAcao.setOnAction(event -> {
                FuncionarioVO funcionario = getTableView().getItems().get(getIndex());
                
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btnAcao);
            }
        }
    });

        ArrayList<FuncionarioVO> funcionarios = this.funcionarioBO.listar();
        ObservableList<FuncionarioVO> funcs = FXCollections.observableArrayList(funcionarios);
        tabelaFuncionarios.setItems(funcs);
    }
    
}
