package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidIdException;

public abstract class PessoaVO {
    private long id;
    private String nome, cpf;
    private EnderecoVO endereco;
    private TelefoneVO telefone;

    public PessoaVO() {

    }

    public PessoaVO(long id, String nome, String cpf, EnderecoVO endereco) throws Exception {
        setId(id);
        setCpf(cpf);
        setNome(nome);
        setEndereco(endereco);
    }

    public PessoaVO(long id, String nome, String cpf) throws Exception {
        setId(id);
        setCpf(cpf);
        setNome(nome);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) throws InvalidIdException {
        if (id < 0) {
            throw new InvalidIdException("O id inserido é invalido");
        }

        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoVO getEndereco() {
        return this.endereco;
    }

    public void setEndereco(EnderecoVO endereco) {
        this.endereco = endereco;
    }

    public TelefoneVO getTelefone() {
        return this.telefone;
    }

    public void setTelefone(TelefoneVO telefone) {
        this.telefone = telefone;
    }
}
