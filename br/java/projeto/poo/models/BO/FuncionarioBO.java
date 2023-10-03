package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.exceptions.ErroDeAuthenticacaoException;
import br.java.projeto.poo.models.DAO.FuncionarioDao;
import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionarioBO {
    private FuncionarioDao funcionarioDao = new FuncionarioDao();

    public ArrayList<FuncionarioVO> listar() throws SQLException {
        try {
            ArrayList<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();
            ResultSet selectFuncionarios = funcionarioDao.listar();
            while(selectFuncionarios.next()) {
                funcionarios.add(new FuncionarioVO(
                    selectFuncionarios.getInt("id"), 
                    selectFuncionarios.getString("nome"),
                    selectFuncionarios.getString("cpf"), 
                    selectFuncionarios.getDouble("salario"), 
                    selectFuncionarios.getString("dataDeAdmissao"), 
                    selectFuncionarios.getInt("funcao")));
            }
            
            return funcionarios;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Erro ao acessar o banco");
        }
    }

    public boolean inserir(FuncionarioVO ob) throws Exception {
        try {
            return funcionarioDao.inserir(ob);
        } catch (Exception e) {
            throw new Exception("Falha ao adicionar funcionario");
        }
    }

    public FuncionarioVO atualizar(FuncionarioVO vo) {
        
        return vo;
    }

    public Boolean deletar(long id) {
        try {
            FuncionarioVO vo = new FuncionarioVO();
            vo.setId(id);
            return funcionarioDao.deletar(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public FuncionarioVO authenticar(FuncionarioVO vo) throws ErroDeAuthenticacaoException {
        try {
            ResultSet funcionario = funcionarioDao.buscarPorCPF(vo);
            System.out.println("opa");
            if (funcionario == null) {
                throw new ErroDeAuthenticacaoException("Usuario não encontrado");
            }
            if (funcionario.next()) { // se existir retorna os dados do usuario
                System.out.println(funcionario.getString("senha").equals(vo.getSenha()));
                if (funcionario.getString("senha").equals(vo.getSenha())) {

                    return new FuncionarioVO(funcionario.getInt("id"), 
                        funcionario.getString("nome"), 
                        funcionario.getString("cpf"), 
                        funcionario.getDouble("salario"), 
                        funcionario.getString("dataDeAdmissao"), 
                        funcionario.getInt("funcao"));
                }
            }

            throw new ErroDeAuthenticacaoException("Usuario não encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErroDeAuthenticacaoException(e.getMessage());
        }
    }
}
