package br.java.projeto.poo.src;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.ClienteDao;
import br.java.projeto.poo.models.DAO.EnderecoDao;
import br.java.projeto.poo.models.DAO.TelefoneDao;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.TelefoneVO;

class App {
    public static void main(String[] args) {
        ClienteVO cv = new ClienteVO(3, "joao pedros", "12345"); 
        ClienteDao<ClienteVO> cd = new ClienteDao<ClienteVO>();

        EnderecoVO ev = new EnderecoVO(1, "444555", null, "ruas", "B", "assu", "rn", "59650000");
        EnderecoDao<EnderecoVO> Ed = new EnderecoDao<EnderecoVO>();

        TelefoneVO tv = new TelefoneVO(5, "12345", null, "11111");
        TelefoneDao<TelefoneVO> td = new TelefoneDao<TelefoneVO>();

        try {
            cd.buscarPorCPF(cv);
            cd.inserir(cv);
            ResultSet telefones = td.buscarPorCpfCliente(tv);
            while(telefones.next()) {
                System.out.println(telefones.getString("numero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}