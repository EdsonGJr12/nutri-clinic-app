package br.com.nutriclinic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.fragment.DiaRefeicoesAdapter;
import br.com.nutriclinic.R;
import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.DiaSemanaModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary));
        }

        this.nomeUsuarioTextView = findViewById(R.id.nomeUsuario);

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SharedPreferencesConfig.TOKEN_KEY, "");
        Long idPaciente = sharedPreferences.getLong(SharedPreferencesConfig.ID_PACIENTE_KEY, 0);

        String nomeUsuario = sharedPreferences.getString(SharedPreferencesConfig.NOME_USUARIO_KEY, "");
        this.nomeUsuarioTextView.setText(nomeUsuario);

        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        Call<List<DiaSemanaModel>> diasSemanaCall = apiClient.pesquisarDiasPlanoAlimentar(token, idPaciente);

        AppCompatActivity currentActivity = this;
        diasSemanaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<DiaSemanaModel>> call, Response<List<DiaSemanaModel>> response) {

                if (response.isSuccessful()) {
                    List<Integer> diasSemana = response.body().stream()
                            .map(dia -> dia.getCodigo())
                            .collect(Collectors.toList());

                    viewPager = findViewById(R.id.viewPager);

                    DiaRefeicoesAdapter adapter = new DiaRefeicoesAdapter(currentActivity, diasSemana);
                    viewPager.setAdapter(adapter);

                } else {
                    Toast.makeText(getApplicationContext(),"Falha ao carregar dados. Tente novamente em instantes", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<DiaSemanaModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void detalhesPlanoAlimentar(View view) {
        Intent intent = new Intent(this, DetalhePlanoAlimentarActivity.class);
        startActivity(intent);
    }
}