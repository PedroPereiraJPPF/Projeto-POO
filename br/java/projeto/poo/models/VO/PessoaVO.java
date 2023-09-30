package br.java.projeto.poo.models.VO;

public abstract class PessoaVO {
    private long id;
    private String nome, cpf;

    public PessoaVO() {

    }

    public PessoaVO(long id, String nome, String cpf) {
        setId(id);
        setCpf(cpf);
        setNome(nome);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
}
