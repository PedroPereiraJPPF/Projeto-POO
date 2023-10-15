package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.DAO.OrcamentoDao;
import br.java.projeto.poo.models.VO.OrcamentoVO;

public class OrcamentoBO {
    OrcamentoDao orcamentoDao = new OrcamentoDao();

    public ArrayList<OrcamentoVO> listar() throws Exception {
        try {
            ResultSet orcamentos = orcamentoDao.listar();
            OrcamentoVO orcamento = new OrcamentoVO();
            ArrayList<OrcamentoVO> listarOrcamentos = new ArrayList<OrcamentoVO>();
            while(orcamentos.next()) {
                orcamento.setId(orcamentos.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentos.getString("placaVeiculo"));
                orcamento.setValor(orcamentos.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentos.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentos.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentos.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentos.getString("cpfResponsavel"));
                listarOrcamentos.add(orcamento);
            }

            return listarOrcamentos;

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean inserir(OrcamentoVO vo) throws Exception {
        try {
            return this.orcamentoDao.inserir(vo); 
        } catch (Exception e) {
            throw e;
        }
    }

    public OrcamentoVO atualizar(OrcamentoVO VO) {
        return null;
    }

    public boolean deletar() {
        return false;
    }

    public ArrayList<OrcamentoVO> buscarPorId(long id) throws SQLException {
        return null;
    }

    public ArrayList<OrcamentoVO> buscarPorVeiculo(String placa) throws SQLException {
        return null;
    }

    public ArrayList<OrcamentoVO> buscarPorCPFCliente(String cpf) throws SQLException {
        return null;
    }
}
