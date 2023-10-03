package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.ClienteVO;

public class ClienteDao <VO extends ClienteVO> extends BaseDao <VO>{
    Connection db;

    public ClienteDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO cliente) throws SQLException {
        String query = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(VO cliente) throws SQLException {
        String query = "DELETE FROM clientes WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, cliente.getId());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletarPorCPF(VO cliente) throws SQLException {
        String query = "DELETE FROM clientes WHERE cpf = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, cliente.getCpf());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        }
    }

    public VO atualizar(VO cliente) throws SQLException {
        String query = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(3, cliente.getId());
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.executeUpdate();
            return cliente;

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO cliente) throws SQLException {
        String query = "Select * from clientes where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, cliente.getId());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet buscarPorCPF(VO cliente) throws SQLException {
        String query = "Select * from clientes where cpf = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, cliente.getCpf());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet buscarPorNome(VO cliente) throws SQLException {
        String query = "Select * from clientes where nome = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, cliente.getNome());
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from clientes";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw e;
        }
    }
}
