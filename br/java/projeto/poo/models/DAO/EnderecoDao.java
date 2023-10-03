package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.EnderecoVO;

public class EnderecoDao <VO extends EnderecoVO> extends BaseDao <VO>{
    
    Connection db;

    public EnderecoDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO endereco) throws SQLException {
        String query = "INSERT INTO enderecos (cpfCliente, cpfFuncionario, rua, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, endereco.getCpfCliente());
            ps.setString(2, endereco.getCpfFuncionario());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setString(7, endereco.getCep());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(VO vo) throws SQLException {
        String query = "DELETE FROM enderecos WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, vo.getId());
            return ps.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public VO atualizar(VO endereco) throws SQLException {
        String query = "UPDATE enderecos SET cpfCliente = ?, cpfFuncionario = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(8, endereco.getId());
            ps.setString(1, endereco.getCpfCliente());
            ps.setString(2, endereco.getCpfFuncionario());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setString(7, endereco.getCep());
            ps.executeUpdate();
            return endereco; 

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO endereco) throws SQLException {
        String query = "Select * from enderecos where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, endereco.getId());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet buscarPorCEP(VO endereco) throws SQLException {
        String query = "Select * from enderecos where cep = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, endereco.getCep());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from enderecos";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }
}
