package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.java.projeto.poo.models.DAO.TelefoneDao;
import br.java.projeto.poo.models.VO.TelefoneVO;

public class TelefoneBO {
    private TelefoneDao telefoneDao = new TelefoneDao();

    public boolean inserir(TelefoneVO vo) throws Exception {
        try {
            return telefoneDao.inserir(vo);
        } catch (Exception e) {
            throw e;
        }
    }   
    
    public TelefoneVO buscarPorFuncionario(String cpf) throws Exception {
        try {
            ResultSet selectTelefone = telefoneDao.buscarPorCpfFuncionario(new TelefoneVO(0,null, cpf, null));
            if(selectTelefone.next()) {
                return new TelefoneVO(0, 
                    selectTelefone.getString("cpfCliente"),
                    selectTelefone.getString("cpfFuncionario"),
                    selectTelefone.getString("numero")
                );
            }

            return null;
        } catch (Exception e) {
            throw new Exception("Erro ao acessar o banco");
        }
    }

    public TelefoneVO buscarPorCliente(String cpf) throws Exception {
        try {
            ResultSet selectTelefone = telefoneDao.buscarPorCpfFuncionario(new TelefoneVO(0,cpf, null, null));
            if(selectTelefone.next()) {
                return new TelefoneVO(0, 
                    selectTelefone.getString("cpfCliente"),
                    selectTelefone.getString("cpfFuncionario"),
                    selectTelefone.getString("numero")
                );
            }

            return null;
        } catch (Exception e) {
            throw new Exception("Erro ao acessar o banco");
        }
    }


    public boolean deletarPorCPFFuncionario(String cpf) throws SQLException {
        try {
           return telefoneDao.deletar(new TelefoneVO(0, null, cpf, null));
        } catch (Exception e) {
            throw e;
        }
    }
}
