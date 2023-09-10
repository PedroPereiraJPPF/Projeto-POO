package br.java.projeto.poo.models.DAO;

import java.sql.ResultSet;

public interface BasicInterfaceDao <VOData> {
    public void inserir(VOData data);
    public boolean deletar(int id);
    public ResultSet buscarPorId(int id);
    public ResultSet listar();
    public void atualizar(int id, VOData data);
    
}
