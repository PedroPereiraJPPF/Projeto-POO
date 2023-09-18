package br.java.projeto.poo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.VO.VeiculoVO;

public class VeiculoDao <VO extends VeiculoVO> extends BaseDao <VO>{
    Connection db;

    public VeiculoDao() {
        db = this.getConnection();
    }

    public boolean inserir(VO veiculo) throws SQLException {
        String query = "INSERT INTO veiculos (cor, modelo, placa, cpfDono, tipo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, veiculo.getCor());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            ps.setString(4, veiculo.getCpfDono());
            ps.setString(5, veiculo.getTipo());
            return ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public boolean deletar(VO vo) throws SQLException {
        String query = "DELETE FROM veiculos WHERE id = (?)";
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

    public long atualizar(VO veiculo) throws SQLException {
        String query = "UPDATE veiculos SET cor = ?, modelo = ?, placa = ?, cpfDono = ?, tipo = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(6, veiculo.getId());
            ps.setString(1, veiculo.getCor());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            ps.setString(4, veiculo.getCpfDono());
            ps.setString(5, veiculo.getTipo());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ResultSet buscarPorId(VO veiculo) throws SQLException {
        String query = "Select * from veiculos where id = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setLong(1, veiculo.getId());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet buscarPorCPFDono(VO veiculo) throws SQLException {
        String query = "Select * from veiculos where cpfDono = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, veiculo.getCpfDono());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet buscarPorPlaca(VO veiculo) throws SQLException {
        String query = "Select * from veiculos where placa = (?)";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            ps.setString(1, veiculo.getPlaca());
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }
    
    public ResultSet listar() throws SQLException {
        String query = "Select * from veiculos";
        PreparedStatement ps = null;
        try {
            ps = this.db.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            throw e;
        }
    }
}
