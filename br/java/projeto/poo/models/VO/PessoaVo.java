package br.java.projeto.poo.models.VO;

public abstract class PessoaVo {
    private int id;
    private String nome, cpf;

    public PessoaVo(String nome, String cpf) {
        setCpf(cpf);
        setNome(nome);
    }

    public int getId() {
        return this.id;
    }

    protected void setId(int id) {
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
