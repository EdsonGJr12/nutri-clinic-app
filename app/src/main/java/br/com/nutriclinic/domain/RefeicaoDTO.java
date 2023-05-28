package br.com.nutriclinic.domain;

public class RefeicaoDTO {
    private String nome;
    private String horario;

    public RefeicaoDTO(String nome, String horario) {
        this.nome = nome;
        this.horario = horario;
    }

    public String getNome() {
        return nome;
    }

    public String getHorario() {
        return horario;
    }
}
