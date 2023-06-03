package br.com.nutriclinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.TokenModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import br.com.nutriclinic.domain.DatabaseMock;
import br.com.nutriclinic.domain.DiaRefeicoesDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView nomeUsuarioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.nomeUsuarioTextView = findViewById(R.id.nomeUsuario);

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SharedPreferencesConfig.TOKEN_KEY, "");
        Long idPaciente = sharedPreferences.getLong(SharedPreferencesConfig.ID_PACIENTE_KEY, 0);

        String nomeUsuario = sharedPreferences.getString(SharedPreferencesConfig.NOME_USUARIO_KEY, "");
        this.nomeUsuarioTextView.setText(nomeUsuario);

        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        Call<List<Integer>> diasSemanaCall = apiClient.pesquisarDiasPlanoAlimentar(token, idPaciente);

        AppCompatActivity currentActivity = this;
        diasSemanaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                if (response.isSuccessful()) {
                    List<Integer> diasSemana = response.body();

                    viewPager = findViewById(R.id.viewPager);

                    DiaRefeicoesAdapter adapter = new DiaRefeicoesAdapter(currentActivity, diasSemana);
                    viewPager.setAdapter(adapter);

                } else {
                    Toast.makeText(getApplicationContext(),"Falha ao carregar dados. Tente novamente em instantes", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}