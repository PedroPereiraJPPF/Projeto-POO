package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.models.DAO.ClienteDao;
import br.java.projeto.poo.models.VO.ClienteVO;
import br.java.projeto.poo.models.VO.EnderecoVO;

public class ClienteBO {
    ClienteDao clienteDao = new ClienteDao();
    EnderecoBO enderecoBO = new EnderecoBO();
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

    public ArrayList<ClienteVO> buscarPorCPF(String cpf) throws Exception {
        try {
            ClienteVO cl = new ClienteVO();
            cl.setCpf(cpf);
            ResultSet clientesBuscados = clienteDao.buscarPorCPF(cl);  
            ArrayList<ClienteVO> clientes = new ArrayList<>();
            EnderecoVO endereco;
            while (clientesBuscados.next()) {
                endereco = enderecoBO.buscarPorCliente(clientesBuscados.getString("cpf"));
                clientes.add(new ClienteVO(0, clientesBuscados.getString("nome"), clientesBuscados.getString("cpf"), endereco));
            }

            return clientes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }     
    }
}
