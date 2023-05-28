package br.com.nutriclinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.nutriclinic.domain.DatabaseMock;
import br.com.nutriclinic.domain.DiaRefeicoesDTO;
import br.com.nutriclinic.domain.RefeicaoDTO;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewPager);

        List<String> diasSemana = DatabaseMock.getData().stream()
                .map(DiaRefeicoesDTO::getDiaSemana)
                .collect(Collectors.toList());

        DiaRefeicoesAdapter adapter = new DiaRefeicoesAdapter(this, diasSemana);
        viewPager.setAdapter(adapter);


    }
}