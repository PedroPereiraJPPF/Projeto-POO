package br.java.projeto.poo.models.VO;

import java.util.Date;

public class FuncionarioVO extends PessoaVO {
    private double salario;
    private String dataDeAdimissao;
    private int funcao;

    public double getSalario() {
        return this.salario;
    }

    public FuncionarioVO(long id, String nome, String cpf, double salario, String dataDeAdimissao, int funcao) {
        super(id, nome, cpf);
        setSalario(salario);
        setDataDeAdimissao(dataDeAdimissao);
        setFuncao(funcao);
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

    public int getFuncao() {
        return this.funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

}
