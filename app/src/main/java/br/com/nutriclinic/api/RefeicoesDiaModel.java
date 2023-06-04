package br.com.nutriclinic.api;

import java.util.List;

public class RefeicoesDiaModel {
    private String diaSemana;
    private List<RefeicaoDiaResumoModel> refeicoes;

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public List<RefeicaoDiaResumoModel> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<RefeicaoDiaResumoModel> refeicoes) {
        this.refeicoes = refeicoes;
    }
}
