package br.java.projeto.poo.controller.Orcamentos;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.java.projeto.poo.controller.ModalsController;
import br.java.projeto.poo.models.BO.OrcamentoBO;
import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.src.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GerarRelatorioController {
    OrcamentoBO orcamentoBO = new OrcamentoBO();
    ModalsController modalsController = new ModalsController();

    @FXML
    private Button cadastrar;

    @FXML
    private Button cancelar;

    @FXML
    private TextField dataCriacao;

    @FXML
    private TextField dataEncerramento;

    @FXML
    private Label msgErro;

    @FXML
    private TextField reponsavel;

    @FXML
    private ChoiceBox<String> status;

    @FXML
    public void initialize() {
        this.msgErro.setVisible(false);
        status.getItems().addAll("Finalizado", "Em aberto", "Todos");
        status.setValue("Todos");
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharModal();
    }

    @FXML
    void gerar(ActionEvent event) {
        try {
            Document documento = new Document();
            validarCampos();
            java.sql.Date dataInicio = gerarData(dataCriacao.getText());
            java.sql.Date dataEncerramento = gerarData(this.dataEncerramento.getText());
            if (dataEncerramento.before(dataInicio)) {
                throw new Exception("A data de encerramento não pode ser menor que a de inicio");
            }
            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);

            int numeroStatus = this.retornarStatus(status.getValue());
            OrcamentoVO orcamentoVO = new OrcamentoVO();
            orcamentoVO.setCpfFuncionario(reponsavel.getText());
            orcamentoVO.setDataDeCriação(dataInicio);
            orcamentoVO.setDataDeEncerramento(dataEncerramento);
            orcamentoVO.setStatus(numeroStatus);
            ArrayList<OrcamentoVO> orcamentos = orcamentoBO.buscarPorStatusData(orcamentoVO);

            PdfWriter.getInstance(documento, new FileOutputStream("br/java/projeto/poo/relatorios/Relatorio-" + dataFormatada + ".pdf"));
            documento.open();
            documento.setPageSize(PageSize.A4);
            documento.add(new Paragraph("Relatorio Ganhos - " + dataFormatada));
            documento.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);

            float[] columnWidths = {450f, 450f, 350f, 350f, 450f, 450f};
            table.setWidths(columnWidths);

            table.addCell("Responsavel");
            table.addCell("Cliente");
            table.addCell("Automovel");
            table.addCell("valor");
            table.addCell("inicio");
            table.addCell("fim");
            
            for (OrcamentoVO orcamentoAtual : orcamentos) {
                table.addCell(orcamentoAtual.getCpfFuncionario());
                table.addCell(orcamentoAtual.getCpfCliente());
                table.addCell(orcamentoAtual.getPlacaVeiculo());
                table.addCell(String.valueOf(orcamentoAtual.getValor()));
                table.addCell(String.valueOf(orcamentoAtual.getDataDeCriação()));
                table.addCell(String.valueOf(orcamentoAtual.getDataDeEncerramento()));
            }

            documento.add(table);
            documento.close();
            fecharModal();

            modalsController.abrirModalSucesso("Relatorio criado com sucesso!");
            App.navegarEntreTelas("orcamentos");
        } catch (Exception e) {
            msgErro.setText(e.getMessage());
            msgErro.setVisible(true);
        }
    }

    private void fecharModal() {
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }

    private void validarCampos() throws Exception {
        if (!dataCriacao.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new Exception("data de criação inválida");
        }

        if (!dataEncerramento.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new Exception("data de encerramento inválida");
        }

        if (status.getValue().isEmpty()) {
            throw new Exception("O status não pode ser vazio");
        }
    }

    private java.sql.Date gerarData(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataGerada = dateFormat.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(dataGerada.getTime());
        return sqlDate;
    }

    private int retornarStatus (String status) {
        if (status.equals("Finalizado")) {
            return 1;
        }

        if (status.equals("Em aberto")) {
            return 0;
        }

        return 3;
    }
}
