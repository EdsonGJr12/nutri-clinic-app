package br.com.nutriclinic.api;

import java.util.List;

public class RefeicoesDiaModel {
    private String diaSemana;
    private List<String> refeicoes;

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public List<String> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<String> refeicoes) {
        this.refeicoes = refeicoes;
    }
}
