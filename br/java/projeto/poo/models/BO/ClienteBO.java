package br.java.projeto.poo.models.BO;

import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.ClienteDao;
import br.java.projeto.poo.models.VO.ClienteVO;

public class ClienteBO {
    ClienteDao clienteDao = new ClienteDao();

    public boolean inserir(ClienteVO cliente) throws Exception {
        try {
            return clienteDao.inserir(cliente);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new Exception("Esse CPF já pertence a outro Cliente");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return false;
    }
}
