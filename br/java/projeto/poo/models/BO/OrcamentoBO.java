package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.DAO.OrcamentoDao;
import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;

public class OrcamentoBO {
    OrcamentoDao orcamentoDao = new OrcamentoDao();

    public ArrayList<OrcamentoVO> listar() throws Exception {
        try {
            ResultSet orcamentos = orcamentoDao.listar();
            ResultSet pecasBuscadas;
            ResultSet servicosBuscados;
            OrcamentoVO orcamento = new OrcamentoVO();
            ArrayList<OrcamentoVO> listarOrcamentos = new ArrayList<OrcamentoVO>();
            ArrayList<PecaVo> pecas = new ArrayList<PecaVo>();
            ArrayList<ServicoVO> servicos = new ArrayList<ServicoVO>();

            while(orcamentos.next()) {
                orcamento.setId(orcamentos.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentos.getString("placaVeiculo"));
                orcamento.setValor(orcamentos.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentos.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentos.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentos.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentos.getString("cpfResponsavel"));
                pecasBuscadas = orcamentoDao.listarPecas(orcamento);
                servicosBuscados = orcamentoDao.listarServicos(orcamento);

                while(pecasBuscadas.next()) {
                    pecas.add(new PecaVo(pecasBuscadas.getLong("id"),
                    pecasBuscadas.getString("nome"),
                    pecasBuscadas.getString("fabricante"),
                    pecasBuscadas.getDouble("valor"),
                    pecasBuscadas.getInt("quantidade")));
                }

                while(servicosBuscados.next()) {
                    servicos.add(new ServicoVO(servicosBuscados.getLong("id"),
                    pecasBuscadas.getString("nome"),
                    pecasBuscadas.getDouble("valor")));
                }

                orcamento.setPecas(pecas);
                orcamento.setServicos(servicos);
                listarOrcamentos.add(orcamento);
            }

            return listarOrcamentos;

        } catch (Exception e) {
            throw e;
        }
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
