package br.com.nutriclinic.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import br.com.nutriclinic.api.DiaSemanaModel;

public class DetalhePlanoAlimentarAdapter extends FragmentStateAdapter {
    private final List<DiaSemanaModel> diasSemana;

    public DetalhePlanoAlimentarAdapter(FragmentActivity fragmentActivity, List<DiaSemanaModel> diasSemana) {
        super(fragmentActivity);
        this.diasSemana = diasSemana;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new DetalhePlanoAlimentarFragment();

        Bundle args = new Bundle();

        Integer diaSemana = diasSemana.get(position).getCodigo();
        String descricaoDiaSemana = diasSemana.get(position).getDiaSemana();

        args.putInt("diaSemana", diaSemana);
        args.putString("descricaoDiaSemana", descricaoDiaSemana);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return diasSemana.size();
    }

}
