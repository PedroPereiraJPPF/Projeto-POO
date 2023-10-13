package br.java.projeto.poo.models.VO;

import java.util.ArrayList;

public class ClienteVO extends PessoaVO{
    ArrayList<VeiculoVO> veiculo;

    public ClienteVO(long id, String nome, String cpf, EnderecoVO endereco, ArrayList<VeiculoVO> veiculo) throws Exception {
        super(id, nome, cpf, endereco);
        setVeiculo(veiculo);
    }

    public ClienteVO() {
        super();
    }

    public ArrayList<VeiculoVO> getVeiculo(){
        return this.veiculo;
    }

    public void setVeiculo(ArrayList<VeiculoVO> veiculo){
        this.veiculo = veiculo;
    }
}
