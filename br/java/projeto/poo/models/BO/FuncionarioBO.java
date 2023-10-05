package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.exceptions.ErroDeAuthenticacaoException;
import br.java.projeto.poo.exceptions.InvalidCpfException;
import br.java.projeto.poo.exceptions.UsuarioNaoEncontradoException;
import br.java.projeto.poo.models.DAO.FuncionarioDao;
import br.java.projeto.poo.models.VO.FuncionarioVO;

public class FuncionarioBO {
    private FuncionarioDao funcionarioDao = new FuncionarioDao();

    public ArrayList<FuncionarioVO> listar() throws Exception {
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

    public boolean inserir(FuncionarioVO vo) throws Exception {
        try {
            return funcionarioDao.inserir(vo);
        } catch (Exception e) {
            throw new Exception("Falha ao adicionar funcionario");
        }
    }

    public FuncionarioVO atualizar(FuncionarioVO vo) throws Exception {
        try {
            ResultSet verificarFuncionario = funcionarioDao.buscarPorId(vo);

            if (!verificarFuncionario.next() || vo.getId() == 0) {
                throw new UsuarioNaoEncontradoException("Usuario não encontrado");
            }

            if (!this.validarCpf(vo.getCpf())) {
                throw new InvalidCpfException("CPF inválido o formato deve ser ***.***.***-**");
            }

            return funcionarioDao.atualizar(vo);
        } 
        catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new Exception("Esse CPF já pertence a outro usuario");
            }
            throw new Exception("falha ao atualizar funcionario");
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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

    private boolean validarCpf(String cpf) {
        return cpf.matches("\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b");
    }
}
