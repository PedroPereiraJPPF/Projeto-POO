package br.java.projeto.poo.models.BO;

import java.sql.SQLException;
import java.util.ArrayList;
import br.java.projeto.poo.DAO.OrcamentoDao;
import br.java.projeto.poo.models.VO.OrcamentoVO;

public class OrcamentoBO {
    OrcamentoDao orcamentoDao = new OrcamentoDao();

    public ArrayList<OrcamentoVO> listar() {
        return null;
    }

    public boolean inserir(OrcamentoVO VO) {
        return false;
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
