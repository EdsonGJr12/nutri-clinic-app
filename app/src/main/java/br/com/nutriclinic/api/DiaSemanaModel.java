package br.com.nutriclinic.api;

public class DiaSemanaModel {
    private Integer codigo;
    private String diaSemana;

    private String diaSemanaResumida;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getDiaSemanaResumida() {
        return diaSemanaResumida;
    }

    public void setDiaSemanaResumida(String diaSemanaResumida) {
        this.diaSemanaResumida = diaSemanaResumida;
    }
}
