package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidEnderecoException;

public class EnderecoVO {
    private long id;
    private String cpfCliente, cpfFuncionario, rua, bairro, cidade, estado, cep;

    public EnderecoVO() {

    }

    public EnderecoVO(String cpfCliente, String cpfFuncionario) {
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
    }

    public EnderecoVO(int id, String cpfCliente, String cpfFuncionario, String rua, String bairro, String cidade, String estado) {
        setId(id);
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
        setRua(rua);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
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

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String toString() {
        return this.rua + ", " + this.bairro + ", " + this.cidade + ", " + this.estado; 
    }

    public EnderecoVO pegarValoresComoString(String enderecoCompleto) throws InvalidEnderecoException {
        String partes[] = enderecoCompleto.split(",");
        int contador = partes.length;
        if (contador < 4) {
            throw new InvalidEnderecoException("formato de endereço invalido");
        }
        this.rua = partes[0];
        this.bairro = partes[1];
        this.cidade = partes[2];
        this.estado = partes[3];

        return this;
    }

}
