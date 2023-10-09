package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidCorException;
import br.java.projeto.poo.exceptions.InvalidIdException;
import br.java.projeto.poo.exceptions.InvalidModeloException;
import br.java.projeto.poo.exceptions.InvalidVeiculoException;

public class VeiculoVO {
    private long id;
    private String placa, cor, modelo, cpfDono, tipo;

    public VeiculoVO(long id, String placa, String cor, String modelo, String cpfDono, String tipo) throws Exception{
        setId(id);
        setPlaca(placa);
        setCor(cor);
        setModelo(modelo);
        setCpfDono(cpfDono);
        setTipo(tipo);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) throws InvalidIdException {
        if(id < 0) {
            throw new InvalidIdException();
        }

        this.id = id;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        // if(!placa.matches("[A-Z]{3}-\\d{4}")) {
        //     throw new InvalidPlacaException();
        // }

        this.placa = placa;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) throws InvalidCorException {
        if (cor == null || cor.isEmpty()) {
            throw new InvalidCorException("A cor do veiculo inválida");
        }

        this.cor = cor;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) throws InvalidModeloException {
        if(modelo == null || modelo.isEmpty()) {   
            throw new InvalidModeloException("modelo de veiculo inválido");
        }

        this.modelo = modelo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) throws InvalidVeiculoException {
        if(tipo == null || tipo.isEmpty()) {   
            throw new InvalidVeiculoException("modelo de veiculo inválido");
        }

        this.tipo = tipo;
    }

    public String getCpfDono() {
        return this.cpfDono;
    }

    public void setCpfDono(String cpfDono) {
        // if(!cpfDono.matches("\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b")) {
        //     throw new InvalidCpfException("O CPF inserido é inválido");
        // }

        this.cpfDono = cpfDono;
    }

}
