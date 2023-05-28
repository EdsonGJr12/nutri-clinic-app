package br.com.nutriclinic;

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

import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.domain.DatabaseMock;
import br.com.nutriclinic.domain.RefeicaoDTO;


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

        String diaSemana = args.getString("diaSemana");

        Log.d("diaSemana", "dia da semana: " + diaSemana);

        List<String> refeicoes = DatabaseMock.getData().stream()
                .filter(dia -> dia.getDiaSemana().equals(diaSemana))
                .findFirst()
                .get()
                .getRefeicoes()
                .stream()
                .map(refeicao -> refeicao.getNome() + " --------- " + refeicao.getHorario())
                .collect(Collectors.toList());


        TextView textView = view.findViewById(R.id.diaSemana);
        textView.setText(diaSemana);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, refeicoes) {

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


    }
}