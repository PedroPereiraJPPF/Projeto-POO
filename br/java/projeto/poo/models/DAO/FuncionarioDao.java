package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionarioDao <VO extends FuncionarioVO> extends BaseDao <VO> {

    Connection db;

    public FuncionarioDao() {
        db = this.getConnection();
    }

    public void inserir(VO funcionario) {
        String query = "INSERT INTO funcionarios (nome, cpf, salario, dataDeAdmissao, funcao) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDouble(3, funcionario.getSalario());
            ps.setString(4, funcionario.getDataDeAdimissao());
            ps.setInt(5, funcionario.getFuncao());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deletar(int id) {
        String query = "DELETE FROM funcionarios WHERE id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setInt(1, id);
            return ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void atualizar(int id, VO funcionario) {
        String query = "UPDATE funcionarios SET nome = ?, cpf = ?, salario = ?, dataDeAdmissao = ?, funcao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setInt(6, id);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDouble(3, funcionario.getSalario());
            ps.setString(4, funcionario.getDataDeAdimissao());
            ps.setInt(5, funcionario.getFuncao());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet buscarPorId(int id) {
        String query = "Select * from funcionarios where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public ResultSet listar() {
        String query = "Select * from funcionarios";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
