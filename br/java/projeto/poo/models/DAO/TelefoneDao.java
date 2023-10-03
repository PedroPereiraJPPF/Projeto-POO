package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.java.projeto.poo.models.VO.TelefoneVO;

public class TelefoneDao <VO extends TelefoneVO> extends BaseDao <VO>{
    Connection db;

    public TelefoneDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO telefone) throws SQLException {
        String query = "INSERT INTO telefones (cpfCliente, cpfFuncionario, numero) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, telefone.getCpfCliente());
            ps.setString(2, telefone.getCpfFuncionario());
            ps.setString(3, telefone.getNumero());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(VO vo) throws SQLException {
        String query = "DELETE FROM telefones WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, vo.getId());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public VO atualizar(VO telefone) throws SQLException {
        String query = "UPDATE telefones SET cpfCliente = ?, cpfFuncionario = ?, numero = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(4, telefone.getId());
            ps.setString(1, telefone.getCpfCliente());
            ps.setString(2, telefone.getCpfFuncionario());
            ps.setString(3, telefone.getNumero());
            ps.executeUpdate();
            return telefone; 

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO telefone) throws SQLException {
        String query = "Select * from telefones where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, telefone.getId());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet buscarPorCpfCliente(VO telefone) throws SQLException {
        String query = "Select * from telefones where cpfCliente = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1,  telefone.getCpfCliente());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet buscarPorCpfFuncionario(VO telefone) throws SQLException {
        String query = "Select * from telefones where cpfFuncionario = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1,  telefone.getCpfFuncionario());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from telefones";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }
    
}
