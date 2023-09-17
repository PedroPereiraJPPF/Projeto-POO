package br.java.projeto.poo.models.VO;

public class TelefoneVO {
    long idCliente;
    String numero;

    public TelefoneVO(long idCliente, String numero) {
        setIdCliente(idCliente);
        setNumero(numero);
    }

    public long getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
