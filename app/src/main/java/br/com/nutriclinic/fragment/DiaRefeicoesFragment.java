package br.com.nutriclinic.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.R;
import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.RefeicoesDiaModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import br.com.nutriclinic.domain.DatabaseMock;
import br.com.nutriclinic.domain.RefeicaoDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiaRefeicoesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dia_refeicoes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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

                    TextView textView = view.findViewById(R.id.diaSemana);
                    textView.setText(refeicoesDia.getDiaSemana());

                    List<String> refeicoesDiaResumo = refeicoesDia.getRefeicoes().stream()
                            .map(refeicao -> refeicao.getDescricao() + "----------------" + refeicao.getHorario())
                            .collect(Collectors.toList());

                    ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, refeicoesDiaResumo) {

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {

                            View view = super.getView(position, convertView, parent);
                            TextView text = (TextView) view.findViewById(android.R.id.text1);

                            text.setTextColor(Color.WHITE);

                            return view;
                        }
                    };
                    ListView listView = view.findViewById(R.id.refeicoes);
                    listView.setAdapter(listAdapter);

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