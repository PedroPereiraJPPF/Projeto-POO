package br.java.projeto.poo.src;

import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.ClienteDao;
import br.java.projeto.poo.models.VO.ClienteVO;

class App {
    public static void main(String[] args) {
        ClienteVO cv = new ClienteVO(3, "joao pedros", "12345"); 
        ClienteDao<ClienteVO> cd = new ClienteDao<ClienteVO>();

        try {
            cd.buscarPorCPF(cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}