package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionarioDao extends BaseDao <FuncionarioVO> {

    Connection db;

    public FuncionarioDao() {
        db = this.getConnection();
    }

    public boolean inserir(FuncionarioVO funcionario) throws SQLException {
        String query = "INSERT INTO funcionarios (nome, cpf, salario, dataDeAdmissao, funcao, senha) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDouble(3, funcionario.getSalario());
            ps.setString(4, funcionario.getDataDeAdimissao());
            ps.setInt(5, funcionario.getNivel());
            ps.setString(6, funcionario.getSenha());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(FuncionarioVO vo) throws SQLException {
        String query = "DELETE FROM funcionarios WHERE id = (?)";
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

    public long atualizar(FuncionarioVO funcionario) throws SQLException {
        String query = "UPDATE funcionarios SET nome = ?, cpf = ?, salario = ?, dataDeAdmissao = ?, funcao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(6, funcionario.getId());
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDouble(3, funcionario.getSalario());
            ps.setString(4, funcionario.getDataDeAdimissao());
            ps.setInt(5, funcionario.getNivel());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(FuncionarioVO funcionario) throws SQLException {
        String query = "Select * from funcionarios where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, funcionario.getId());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet buscarPorCPF(FuncionarioVO funcionario) throws SQLException {
        String query = "Select * from funcionarios where cpf = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, funcionario.getCpf());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet listar() throws SQLException {
        String query = "Select * from funcionarios";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }
    
}
