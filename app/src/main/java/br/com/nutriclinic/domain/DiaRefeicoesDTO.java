package br.com.nutriclinic.domain;

import java.util.List;

public class DiaRefeicoesDTO {
    private String diaSemana;
    private List<RefeicaoDTO> refeicoes;

    public DiaRefeicoesDTO(String diaSemana, List<RefeicaoDTO> refeicoes) {
        this.diaSemana = diaSemana;
        this.refeicoes = refeicoes;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public List<RefeicaoDTO> getRefeicoes() {
        return refeicoes;
    }
}
