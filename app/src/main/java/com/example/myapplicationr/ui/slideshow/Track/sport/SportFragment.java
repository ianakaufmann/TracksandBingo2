package com.example.myapplicationr.ui.slideshow.Track.sport;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplicationr.databinding.FragmentSportBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.TreeSet;

public class SportFragment extends Fragment {
    private static final String KEY_RADIO_BUTTON_S = "RADIO_BUTTON_S";
    SharedPreferences sharedPreferencess;
    ArrayList<Integer> saveLists;
    private static TreeSet<Integer> idss;
    private FragmentSportBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSportBinding.inflate(inflater, container, false);
        sharedPreferencess = getContext().getSharedPreferences(KEY_RADIO_BUTTON_S, Context.MODE_PRIVATE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton[] massButtons = {binding.sb1, binding.sb2, binding.sb3, binding.sb4, binding.sb5,
                binding.sb6, binding.sb7, binding.sb8, binding.sb9, binding.sb10, binding.sb11, binding.sb12,
                binding.sb13, binding.sb13, binding.sb14, binding.sb15, binding.sb16, binding.sb17, binding.sb18,
                binding.sb19, binding.sb20, binding.sb21, binding.sb22, binding.sb23, binding.sb24, binding.sb25,
                binding.sb26, binding.sb27, binding.sb28, binding.sb29, binding.sb30};

        idss = Select(getLists());
        for (int i = 0; i < massButtons.length; i++) {
            if (idss.contains(massButtons[i].getId()))
                massButtons[i].setChecked(true);
        }

        for (RadioButton radioButtons : massButtons) {
            radioButtons.setOnClickListener(v -> {
                ClickSport(radioButtons.getId());
            });
        }
    }

    private TreeSet<Integer> Select(ArrayList<Integer> saveLists) {
        if (idss == null) {
            idss = new TreeSet<>();
            for (Integer xs : saveLists) {
                idss.add(xs);
            }
            return idss;
        } else return idss;
    }

    private ArrayList<Integer> getLists(){
        ArrayList<Integer> templists = new ArrayList<>();
        String strs = sharedPreferencess.getString(KEY_RADIO_BUTTON_S, "нет");
        if (strs.equals("нет"))
            return new ArrayList<>();
        else {
            String[] temps = strs.split(",");
            for (int s = 0; s < temps.length; s++) {
                templists.add(Integer.parseInt(temps[s]));
            }
        }
        return templists;
    }
    private final static String sharePrefs = "processs";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outStates) {
        super.onSaveInstanceState(outStates);
        if (saveLists == null) {
            saveLists = new ArrayList<>();
            saveLists.addAll(idss);
        }
        outStates.putIntegerArrayList(sharePrefs, saveLists);
        StringBuilder sbs = new StringBuilder();
        for (int s = 0; s < saveLists.size(); s++) {
            sbs.append(saveLists.get(s)).append(",");
        }
        SharedPreferences.Editor prefEditors = sharedPreferencess.edit();
        prefEditors.putString(KEY_RADIO_BUTTON_S, String.valueOf(sbs));
        prefEditors.apply();
    }

    public void ClickSport(int id) {
        if (idss.size() < 30) {
            idss.add(id);
        }
        binding.progressBarS.setProgress(idss.size());
        binding.dayss.setText("Выполнено " + idss.size() + " из 30 дней");
        if (idss.size() == 30) {
            Snackbar.make(binding.getRoot(), "Поздравляем с выполнением 30-дневного челленджа!\uD83C\uDF89", Toast.LENGTH_SHORT).show();
        }
    }
}