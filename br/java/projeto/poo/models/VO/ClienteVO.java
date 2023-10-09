package br.java.projeto.poo.models.VO;

public class ClienteVO extends PessoaVO{
    public ClienteVO(long id, String nome, String cpf, EnderecoVO endereco) throws Exception {
        super(id, nome, cpf, endereco);
    }

    public ClienteVO() {
        super();
    }
}
