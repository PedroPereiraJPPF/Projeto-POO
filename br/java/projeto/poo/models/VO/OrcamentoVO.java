package br.java.projeto.poo.models.VO;

import java.util.List;

public class OrcamentoVO {
    long id;
    String placaVeiculo;
    float valor;
    List<PecaVo> pecas;
    List<ServicoVO> Servicos;

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

    public List<PecaVo> getPecas() {
        return this.pecas;
    }

    public void setPecas(List<PecaVo> pecas) {
        this.pecas = pecas;
    }

    public List<ServicoVO> getServicos() {
        return this.Servicos;
    }

    public void setServicos(List<ServicoVO> Servicos) {
        this.Servicos = Servicos;
    }
}
