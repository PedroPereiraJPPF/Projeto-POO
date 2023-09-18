package br.java.projeto.poo.src;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.ClienteDao;
import br.java.projeto.poo.models.DAO.EnderecoDao;
import br.java.projeto.poo.models.DAO.TelefoneDao;
import br.java.projeto.poo.models.DAO.VeiculoDao;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;
import br.java.projeto.poo.models.VO.TelefoneVO;
import br.java.projeto.poo.models.VO.VeiculoVO;

class App {
    public static void main(String[] args) {
        try {
            ClienteVO cv = new ClienteVO(6, "joao pedros", "123.456.789-53"); 
            ClienteDao<ClienteVO> cd = new ClienteDao<ClienteVO>();

            EnderecoVO ev = new EnderecoVO(1, "444555", null, "ruas", "B", "assu", "rn", "59650000");
            EnderecoDao<EnderecoVO> Ed = new EnderecoDao<EnderecoVO>();

            TelefoneVO tv = new TelefoneVO(5, "12345", null, "11111");
            TelefoneDao<TelefoneVO> td = new TelefoneDao<TelefoneVO>();

            VeiculoVO vv = new VeiculoVO(4, "ABC-4457", "azul", "modelo massa2", "123.456.789-53", "avião");
            VeiculoDao<VeiculoVO> vd = new VeiculoDao<VeiculoVO>();
            
            cd.buscarPorCPF(cv);
            cd.inserir(cv);
            ResultSet telefones = td.listar();
            while(telefones.next()) {
                System.out.println(telefones.getString("numero"));
            }
            vd.inserir(vv);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}