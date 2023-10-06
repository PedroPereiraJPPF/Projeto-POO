package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.EnderecoDao;
import br.java.projeto.poo.models.VO.EnderecoVO;

public class EnderecoBO {
    private EnderecoDao enderecoDao = new EnderecoDao();

    public boolean inserir(EnderecoVO vo) throws Exception {
        try {
            return enderecoDao.inserir(vo);
        } catch (Exception e) {
            throw e;
        }
    }   
    
    public EnderecoVO buscarPorFuncionario(String cpf) throws Exception {
        try {
            ResultSet selectEndereco = enderecoDao.buscarPorFuncionario(new EnderecoVO(null, cpf));
            if(selectEndereco.next()) {
                return new EnderecoVO(0, 
                    selectEndereco.getString("cpfCliente"),
                    selectEndereco.getString("cpfFuncionario"),
                    selectEndereco.getString("rua"), 
                    selectEndereco.getString("bairro"), 
                    selectEndereco.getString("cidade"), 
                    selectEndereco.getString("estado"));
            }

            return null;
        } catch (Exception e) {
            throw new Exception("Erro ao acessar o banco");
        }
    }


    public boolean deletarPorCPFFuncionario(String cpf) throws SQLException {
        try {
           return enderecoDao.deletar(new EnderecoVO(null, cpf));
        } catch (Exception e) {
            throw e;
        }
    }
}
