package br.com.nutriclinic.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import br.com.nutriclinic.R;
import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.RefeicaoDiaResumoModel;
import br.com.nutriclinic.api.RefeicoesDiaModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefeicaoItemAdapter extends BaseAdapter {

    private final List<RefeicaoDiaResumoModel> refeicoes;
    private final Activity activity;
    private final Integer diaSemana;

    public RefeicaoItemAdapter(Activity activity, List<RefeicaoDiaResumoModel> refeicoes, Integer diaSemana) {
        this.activity = activity;
        this.refeicoes = refeicoes;
        this.diaSemana = diaSemana;
    }

    @Override
    public int getCount() {
        return refeicoes.size();
    }

    @Override
    public Object getItem(int i) {
        return refeicoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return refeicoes.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.refeicao_list_item, parent, false);

        RefeicaoDiaResumoModel refeicao = refeicoes.get(i);

        TextView refeicaoDescricaoTextView = (TextView) view.findViewById(R.id.refeicaoDescricaoTextView);
        TextView refeicaoHorarioTextView = (TextView) view.findViewById(R.id.refeicaoHorarioTextView);

        refeicaoDescricaoTextView.setText(refeicao.getDescricao());
        refeicaoHorarioTextView.setText(refeicao.getHorario());

        view.setClickable(true);
        view.setOnClickListener(view1 -> carregarAlimentos(view1, refeicao.getId()));

        return view;
    }

    private void carregarAlimentos(View view, Long idRefeicao) {

        ListView alimentosList = view.findViewWithTag("alimentosList");

        if (alimentosList.getVisibility() == View.VISIBLE) {
            alimentosList.setVisibility(View.GONE);
            return;
        }

        SharedPreferences sharedPreferences = activity.getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SharedPreferencesConfig.TOKEN_KEY, "");
        Long idPaciente = sharedPreferences.getLong(SharedPreferencesConfig.ID_PACIENTE_KEY, 0);

        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        Call<List<String>> alimentosCall = apiClient.buscarAlimentosDaRefeicao(token, idPaciente, diaSemana, idRefeicao);
        alimentosCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                if (response.isSuccessful()) {

                    List<String> alimentosRefeicao = response.body();
                    String[] alimentos = alimentosRefeicao.toArray(new String[0]);

                     for (String alimento: alimentos) {
                         Log.d("alimento", alimento);
                     }

                    ArrayAdapter adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, alimentos);
                    alimentosList.setAdapter(adapter);
                    alimentosList.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(view.getContext(),"Falha ao carregar dados. Tente novamente em instantes", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(view.getContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
