package br.java.projeto.poo.models.VO;

public class FuncionarioVO extends PessoaVO {
    private double salario;
    private String dataDeAdimissao, senha;
    private int nivel;
    private EnderecoVO endereco;

    public FuncionarioVO () {
        super();
    }

    public FuncionarioVO(long id, String nome, String cpf, double salario, String dataDeAdimissao, EnderecoVO endereco, int nivel) throws Exception {
        super(id, nome, cpf);
        setSalario(salario);
        setDataDeAdimissao(dataDeAdimissao);
        setNivel(nivel);
        setEndereco(endereco);
    }

    public FuncionarioVO(long id, String nome, String cpf, double salario, String dataDeAdimissao, int nivel) throws Exception {
        super(id, nome, cpf);
        setSalario(salario);
        setDataDeAdimissao(dataDeAdimissao);
        setNivel(nivel);
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDataDeAdimissao() {
        return this.dataDeAdimissao;
    }

    public void setDataDeAdimissao(String dataDeAdimissao) {
        this.dataDeAdimissao = dataDeAdimissao;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnderecoVO getEndereco() {
        return this.endereco;
    }

    public void setEndereco(EnderecoVO endereco) {
        this.endereco = endereco;
    }
}
