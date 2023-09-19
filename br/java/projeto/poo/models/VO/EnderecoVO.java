package br.java.projeto.poo.models.VO;

public class EnderecoVO {
    private long id;
    private String cpfCliente, cpfFuncionario, rua, bairro, cidade, estado, cep;

    public EnderecoVO(int id, String cpfCliente, String cpfFuncionario, String rua, String bairro, String cidade, String estado, String Cep) {
        setId(id);
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
        setRua(rua);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(Cep);
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

}
