package br.java.projeto.poo.models.VO;

public class TelefoneVO {
    private long id;
    private String numero, cpfCliente, cpfFuncionario;

    public TelefoneVO(long id, String cpfCliente, String cpfFuncionario, String numero) {
        setId(id);
        setCpfCliente(cpfCliente);
        setCpfFuncionario(cpfFuncionario);
        setNumero(numero);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String toString() {
        return this.numero; 
    }

}
