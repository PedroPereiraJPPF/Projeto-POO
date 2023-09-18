package br.java.projeto.poo.models.VO;

import br.java.projeto.poo.exceptions.InvalidCorException;
import br.java.projeto.poo.exceptions.InvalidCpfException;
import br.java.projeto.poo.exceptions.InvalidIdException;
import br.java.projeto.poo.exceptions.InvalidModeloException;
import br.java.projeto.poo.exceptions.InvalidPlacaException;
import br.java.projeto.poo.exceptions.InvalidVeiculoException;

public class VeiculoVO {
    long id;
    String placa, cor, modelo, cpfDono, tipo;

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
        if(id > 0) {
            this.id = id;
        } else {
            throw new InvalidIdException();
        }
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) throws InvalidPlacaException {
        if(placa.matches("[A-Z]{3}-\\d{4}")) {
            this.placa = placa;
        } else {
            throw new InvalidPlacaException();
        }
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) throws InvalidCorException {
        if (cor != null && !cor.isEmpty()) {
            this.cor = cor;
        }else {
            throw new InvalidCorException("Cor do veiculo inválida");
        }
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) throws InvalidModeloException {
        if(modelo != null && !modelo.isEmpty()) {   
            this.modelo = modelo;
        } else {
            throw new InvalidModeloException("modelo de veiculo inválido");
        }
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) throws InvalidVeiculoException {
        if(tipo != null && !tipo.isEmpty()) {   
            this.tipo = tipo;
        } else {
            throw new InvalidVeiculoException("modelo de veiculo inválido");
        }
    }

    public String getCpfDono() {
        return this.cpfDono;
    }

    public void setCpfDono(String cpfDono) throws InvalidCpfException {
        if(cpfDono.matches("\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b")) {
            this.cpfDono = cpfDono;
        } else {
            throw new InvalidCpfException("O CPF inserido é inválido");
        }
    }

}
