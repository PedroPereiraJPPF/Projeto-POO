package br.java.projeto.poo.models.BO;

import java.util.ArrayList;

public interface BaseInterfaceBO<VO> {
    public ArrayList<VO> listar(); 
    public Object buscarPorId(int id);
    public boolean inserir(VO ob);
    public Object atualizar(VO ob);
    public Boolean deletar();
}
