package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.exceptions.InvalidPlacaException;
import br.java.projeto.poo.models.DAO.VeiculoDao;
import br.java.projeto.poo.models.VO.VeiculoVO;

public class VeiculoBO {
    VeiculoDao veiculoDao = new VeiculoDao();

    public ArrayList<VeiculoVO> listar() throws Exception {
        try {
            ResultSet veiculosBuscados = veiculoDao.listar();
            ArrayList<VeiculoVO> veiculos = new ArrayList<>();
            while(veiculosBuscados.next()) {
                veiculos.add(new VeiculoVO(veiculosBuscados.getLong("id"),
                veiculosBuscados.getString("placa"),
                veiculosBuscados.getString("cor"),
                veiculosBuscados.getString("modelo"),
                veiculosBuscados.getString("cpfDono"),
                veiculosBuscados.getString("tipo")));
            }

            return veiculos;
        } catch (Exception e) {
            throw new Exception("erro ao buscar veiculos");
        }
    }

    public boolean inserir(VeiculoVO veiculo) throws Exception {
        try {
            if(!validarPlaca(veiculo.getPlaca())) {
                throw new InvalidPlacaException("O fomato da placa deve ser ABC-1234");
            }

            return veiculoDao.inserir(veiculo);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new Exception("Essa placa já pertence a outro veiculo");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return false;
    }

    public boolean deletar(long id) throws Exception {
        try {
            VeiculoVO vo = new VeiculoVO(id, null, null, null, null, null);
            return veiculoDao.deletar(vo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Erro ao deletar usuario");
        }
    }


    private boolean validarPlaca(String placa) {
        return placa.matches("[A-Za-z]{3}-\\d{4}");
    }
}
