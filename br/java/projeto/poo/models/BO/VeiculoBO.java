package br.java.projeto.poo.models.BO;

import java.sql.SQLException;

import br.java.projeto.poo.exceptions.InvalidPlacaException;
import br.java.projeto.poo.models.DAO.VeiculoDao;
import br.java.projeto.poo.models.VO.VeiculoVO;

public class VeiculoBO {
    VeiculoDao veiculoDao = new VeiculoDao();

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


    private boolean validarPlaca(String placa) {
        return placa.matches("[A-Za-z]{3}-\\d{4}");
    }
}
