package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;

public class OrcamentoDao <VO extends OrcamentoVO> extends BaseDao <VO>{
    Connection db;

    public OrcamentoDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO orcamento) throws SQLException {
        String query = "INSERT INTO orcamentos (placaVeiculo, valor) VALUES (?, ?)";
        String queryPecas = "INSERT INTO pecas_orcamentos (idOrcamento, idPeca) VALUES (?, ?)";
        String queryServicos = "INSERT INTO servicos_orcamentos (idOrcamento, idServico) values (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, orcamento.getPlacaVeiculo());
            ps.setDouble(2, orcamento.getValor());
            ps.execute();
            ps.close();

            List<PecaVo> pecas = orcamento.getPecas();
            List<ServicoVO> servicos = orcamento.getServicos();

            if(pecas != null) {
                ps = this.db.prepareStatement(queryPecas);
                for (PecaVo peca : pecas) {
                    try {
                        ps.setLong(1, orcamento.getId());
                        ps.setLong(2, peca.getId());
                        ps.execute();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
                ps.close();
            }

            if(servicos != null) {
                ps = this.db.prepareStatement(queryServicos);
                for (ServicoVO servico : servicos) {
                    try {
                        ps.setLong(1, orcamento.getId());
                        ps.setLong(2, servico.getId());
                        ps.execute();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
                ps.close();
            }

            return true;

        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean deletar(VO orcamento) throws SQLException {
        String query = "DELETE FROM orcamentos WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, orcamento.getId());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public long atualizar(VO orcamento) throws SQLException {
        String query = "UPDATE orcamentos SET placaAutomovel = ?, valor = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(3, orcamento.getId());
            ps.setString(1, orcamento.getPlacaVeiculo());
            ps.setDouble(2, orcamento.getValor());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO orcamento) throws SQLException {
        String query = "Select * from orcamentos where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, orcamento.getId());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from orcamentos";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }
}
