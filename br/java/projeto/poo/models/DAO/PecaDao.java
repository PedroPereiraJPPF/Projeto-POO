package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.PecaVo;

public class PecaDao <VO extends PecaVo> extends BaseDao <VO>{
    Connection db;

    public PecaDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO peca) throws SQLException {
        String query = "INSERT INTO pecas (nome, valor) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, peca.getNome());
            ps.setDouble(2, peca.getValor());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(VO peca) throws SQLException {
        String query = "DELETE FROM pecas WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, peca.getId());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public VO atualizar(VO peca) throws SQLException {
        String query = "UPDATE pecas SET nome = ?, valor = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(3, peca.getId());
            ps.setString(1, peca.getNome());
            ps.setDouble(2, peca.getValor());
            ps.executeUpdate();
            return peca;

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO peca) throws SQLException {
        String query = "Select * from pecas where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, peca.getId());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet buscarPorFabricante(VO peca) throws SQLException {
        String query = "Select * from pecas where fabricante = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, peca.getFabricante());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from pecas";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }
}
