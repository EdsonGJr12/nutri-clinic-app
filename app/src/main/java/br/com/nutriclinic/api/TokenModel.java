package br.com.nutriclinic.api;

public class TokenModel {
    private String token;
    private String nomeUsuario;
    private String perfilUsuario;

    private Long idPaciente;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
