package br.java.projeto.poo.models.VO;

import javax.naming.InvalidNameException;

import br.java.projeto.poo.exceptions.InvalidIdException;
import br.java.projeto.poo.exceptions.InvalidValorException;

public class ServicoVO {
    private long id;
    private String nome;
    private double valor;

    public ServicoVO(long id, String nome, double valor) throws Exception {
        setId(id);
        setNome(nome);
        setValor(valor);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException();
        }

        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws InvalidNameException {
        if(nome == null || nome.isEmpty()) {
            throw new InvalidNameException("Nome inválido");
        }

        this.nome = nome;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) throws InvalidValorException {
        if(valor <= 0) {
            throw new InvalidValorException("O valor inserido é inválido");
        }
        this.valor = valor;
    }

}
