package br.com.nutriclinic.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("auth")
    Call<TokenModel> logar(@Body LoginForm loginForm);
    @GET("pacientes/{idPaciente}/ultimo-plano/dias-semana")
    Call<List<Integer>> pesquisarDiasPlanoAlimentar(@Header("Authorization") String token, @Path("idPaciente") Long idPaciente);
    @GET("pacientes/{idPaciente}/ultimo-plano/dias-semana/{diaSemana}")
    Call<RefeicoesDiaModel> buscarRefeicoesDoDia(@Header("Authorization") String token, @Path("idPaciente") Long idPaciente, @Path("diaSemana") Integer diaSemana);
}

