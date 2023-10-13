package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.java.projeto.poo.models.DAO.PecaDao;
import br.java.projeto.poo.models.VO.PecaVo;

public class PecaBO implements BaseInterfaceBO<PecaVo>{

    PecaDao pecaDao = new PecaDao<PecaVo>();

    @Override
    public ArrayList listar() throws Exception {
        ArrayList<PecaVo> listaDePecas = new ArrayList<PecaVo>();
        ResultSet pecasDoBanco = pecaDao.listar();
        String nomePecaBanco = pecasDoBanco.getString("nome");
        String nomeFabrBanco = pecasDoBanco.getString("fabricante");
        int quantPecaBanco = pecasDoBanco.getInt("quantidade"); 
        double valorPecaBanco = pecasDoBanco.getDouble("preco");

        while(pecasDoBanco.next()){
            listaDePecas.add(new PecaVo(0,nomePecaBanco,nomeFabrBanco,valorPecaBanco,quantPecaBanco));
        }

        return listaDePecas;
    }

    @Override
    public Object buscarPorId(int id) throws Exception{
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public ArrayList<PecaVo> buscarPorFabricante(PecaVo vo) throws Exception {
        PecaDao pecaDao = new PecaDao<PecaVo>();
        try{
            ResultSet resultBusca = pecaDao.buscarPorFabricante(vo);
            ArrayList<PecaVo> resultado = new ArrayList<PecaVo>();
            String nomeResult = resultBusca.getString("nome");
            String fabResult = resultBusca.getString("fabricante");
            double valorResult = resultBusca.getDouble("preco");
            int quantResult = resultBusca.getInt("quantidade");

            while (resultBusca.next()) {
                resultado.add(new PecaVo(0, nomeResult, fabResult, valorResult, quantResult));
            }

            return resultado;

        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar peça.");
        }
    }

    public ArrayList<PecaVo> buscarPorNome(PecaVo vo) throws Exception {
        PecaDao pecaDao = new PecaDao<PecaVo>();
        try{
            ResultSet resultBusca = pecaDao.buscarPorNome(vo);
            ArrayList<PecaVo> resultado = new ArrayList<PecaVo>();
            String nomeResult = resultBusca.getString("nome");
            String fabResult = resultBusca.getString("fabricante");
            double valorResult = resultBusca.getDouble("preco");
            int quantResult = resultBusca.getInt("quantidade");

            while (resultBusca.next()) {
                resultado.add(new PecaVo(0, nomeResult, fabResult, valorResult, quantResult));
            }

            return resultado;

        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar peça.");
        }
    }

    @Override
    public boolean inserir(PecaVo vo) throws Exception{
        try {pecaDao.inserir(vo);} 
        catch (Exception e) {e.printStackTrace();}
        
        return true;
    }

    @Override
    public PecaVo atualizar(PecaVo vo) throws Exception{
        
        PecaDao pecaDao = new PecaDao<PecaVo>();
        return pecaDao.atualizar(vo);
    }

    @Override
    public Boolean deletar(PecaVo vo) throws Exception{
        PecaDao pecaDao = new PecaDao<PecaVo>();
        try {return pecaDao.deletar(vo);}
        catch(Exception e) {e.printStackTrace();}
        return false;
    }
    
}
