package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidEnderecoException;

public class EnderecoVO {
    private long id;
    private String cpfCliente, cpfFuncionario, rua, bairro, cidade, numero;

    public EnderecoVO() {

    }

    public EnderecoVO(String cpfCliente, String cpfFuncionario) {
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
    }

    public EnderecoVO(int id, String cpfCliente, String cpfFuncionario, String rua, String numero, String bairro, String cidade) {
        setId(id);
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
        setRua(rua);
        setBairro(bairro);
        setCidade(cidade);
        setNumero(numero);
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return this.cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfFuncionario() {
        return this.cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String toString() {
        return this.rua + ", " + this.numero + ", " + this.bairro + ", " + this.cidade; 
    }

    public EnderecoVO pegarValoresComoString(String enderecoCompleto) throws InvalidEnderecoException {
        String partes[] = enderecoCompleto.split(",");
        int contador = partes.length;
        if (contador < 4) {
            throw new InvalidEnderecoException("formato de endereço invalido");
        }
        this.rua = partes[0];
        this.numero = partes[1];
        this.bairro = partes[2];
        this.cidade = partes[3];

        return this;
    }

}
