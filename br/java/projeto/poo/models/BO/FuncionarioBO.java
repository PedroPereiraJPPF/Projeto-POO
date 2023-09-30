package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.java.projeto.poo.exceptions.ErroDeAuthenticacaoException;
import br.java.projeto.poo.models.DAO.FuncionarioDao;
import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionarioBO<VO extends FuncionarioVO> implements BaseInterfaceBO<VO>{
    private FuncionarioDao funcionarioDao = new FuncionarioDao();

    @Override
    public ArrayList<VO> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public FuncionarioVO buscarPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public boolean inserir(VO ob) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public Object atualizar(VO ob) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Boolean deletar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
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
