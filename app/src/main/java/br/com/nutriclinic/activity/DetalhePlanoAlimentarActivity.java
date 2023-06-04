package br.com.nutriclinic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import br.com.nutriclinic.fragment.DetalhePlanoAlimentarAdapter;
import br.com.nutriclinic.R;
import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.DiaSemanaModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhePlanoAlimentarActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_plano_alimentar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary));
        }


        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SharedPreferencesConfig.TOKEN_KEY, "");
        Long idPaciente = sharedPreferences.getLong(SharedPreferencesConfig.ID_PACIENTE_KEY, 0);

        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        Call<List<DiaSemanaModel>> diasSemanaCall = apiClient.pesquisarDiasPlanoAlimentar(token, idPaciente);

        AppCompatActivity currentActivity = this;
        diasSemanaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<DiaSemanaModel>> call, Response<List<DiaSemanaModel>> response) {

                if (response.isSuccessful()) {

                    List<DiaSemanaModel> diaSemanaModel = response.body();

                    viewPager = findViewById(R.id.detalhePlanoAlimentarViewPager);

                    TabLayout tabLayout = findViewById(R.id.tabLayout);

                    DetalhePlanoAlimentarAdapter adapter = new DetalhePlanoAlimentarAdapter(currentActivity, diaSemanaModel);
                    viewPager.setAdapter(adapter);

                    new TabLayoutMediator(tabLayout, viewPager,
                            (tab, position) -> tab.setText(diaSemanaModel.get(position).getDiaSemanaResumida())
                    ).attach();

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


}