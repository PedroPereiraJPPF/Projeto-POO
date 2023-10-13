package br.java.projeto.poo.models.VO;

import java.util.ArrayList;

public class OrcamentoVO {
    long id;
    String placaVeiculo, cpfCliente;
    float valor;
    ArrayList<PecaVo> pecas;
    ArrayList<ServicoVO> Servicos;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlacaVeiculo() {
        return this.placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public ArrayList<PecaVo> getPecas() {
        return this.pecas;
    }

    public void setPecas(ArrayList<PecaVo> pecas) {
        this.pecas = pecas;
    }

    public ArrayList<ServicoVO> getServicos() {
        return this.Servicos;
    }

    public void setServicos(ArrayList<ServicoVO> Servicos) {
        this.Servicos = Servicos;
    }

    public String getCpfCliente() {
        return this.cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

}
