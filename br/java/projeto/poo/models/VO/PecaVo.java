package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidIdException;
import br.java.projeto.poo.exceptions.InvalidNomeException;
import br.java.projeto.poo.exceptions.InvalidQuantidadeException;
import br.java.projeto.poo.exceptions.InvalidValorException;

public class PecaVo {
    private long id;
    private String nome, fabricante;
    private double valor;
    private int quantidade;

    public PecaVo() {
        
    }

    public PecaVo(long id, String nome, String fabricante, double valor, int quantidade) throws Exception {
        setId(id);
        setNome(nome);
        setFabricante(fabricante);
        setValor(valor);
        setQuantidade(quantidade);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) throws InvalidIdException {
        if(id < 0) {
            throw new InvalidIdException();
        }
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws InvalidNomeException {
        this.nome = nome;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) throws InvalidNomeException {
        this.fabricante = fabricante;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) throws InvalidValorException {
        if(valor <= 0) {
            throw new InvalidValorException("O valor deve ser maior que 0");
        }

        this.valor = valor;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(int quantidade)throws InvalidQuantidadeException{
        if(quantidade < 0){
            throw new InvalidQuantidadeException("O valor dever maior que 0");
        }

        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }

}
