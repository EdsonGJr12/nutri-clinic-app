package br.com.nutriclinic.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import br.com.nutriclinic.R;
import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.RefeicoesDiaModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalhePlanoAlimentarFragment extends Fragment {

    public DetalhePlanoAlimentarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhe_plano_alimentar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ListView list = view.findViewById(R.id.refeicoesList);

        Bundle args = getArguments();

        Integer diaSemana = args.getInt("diaSemana");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SharedPreferencesConfig.TOKEN_KEY, "");
        Long idPaciente = sharedPreferences.getLong(SharedPreferencesConfig.ID_PACIENTE_KEY, 0);

        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        Call<RefeicoesDiaModel> refeicoesDiaCall = apiClient.buscarRefeicoesDoDia(token, idPaciente, diaSemana);
        refeicoesDiaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RefeicoesDiaModel> call, Response<RefeicoesDiaModel> response) {

                if (response.isSuccessful()) {

                    RefeicoesDiaModel refeicoesDia = response.body();

                    BaseAdapter adapter = new RefeicaoItemAdapter(getActivity(), refeicoesDia.getRefeicoes(), diaSemana);

                    list.setAdapter(adapter);


                } else {
                    Toast.makeText(view.getContext(),"Falha ao carregar dados. Tente novamente em instantes", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RefeicoesDiaModel> call, Throwable t) {
                Toast.makeText(view.getContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });






    }
}