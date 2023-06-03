package br.com.nutriclinic;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Set;

import br.com.nutriclinic.domain.DatabaseMock;

public class DiaRefeicoesAdapter extends FragmentStateAdapter {
    private final List<Integer> diasSemana;


    public DiaRefeicoesAdapter(FragmentActivity fragmentActivity, List<Integer> diasSemana) {
        super(fragmentActivity);
        this.diasSemana = diasSemana;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new DiaRefeicoesFragment();

        Bundle args = new Bundle();

        Integer diaSemana = diasSemana.get(position);

        args.putInt("diaSemana", diaSemana);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return diasSemana.size();
    }
}
