package com.example.myapplicationr.ui.slideshow.Track.personal;

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


import com.example.myapplicationr.databinding.FragmentPersonalBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.TreeSet;

public class PersonalFragment extends Fragment {

    private static final String KEY_RADIO_BUTTON_P = "RADIO_BUTTON_P";
    SharedPreferences sharedPreferencesp;
    ArrayList<Integer> saveListp;
    private static TreeSet<Integer> idsp;
    private FragmentPersonalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        sharedPreferencesp = getContext().getSharedPreferences(KEY_RADIO_BUTTON_P, Context.MODE_PRIVATE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton[] massButtonp = {binding.pb1, binding.pb2, binding.pb3, binding.pb4, binding.pb5,
                binding.pb6, binding.pb7, binding.pb8, binding.pb9, binding.pb10, binding.pb11, binding.pb12,
                binding.pb13, binding.pb13, binding.pb14, binding.pb15, binding.pb16, binding.pb17, binding.pb18,
                binding.pb19, binding.pb20, binding.pb21, binding.pb22, binding.pb23, binding.pb24, binding.pb25,
                binding.pb26, binding.pb27, binding.pb28, binding.pb29, binding.pb30};

        idsp = Selectp(getListp());

        if(idsp.size()<30){
            binding.daysp.setText("Выполнено " + idsp.size() + " из 30 дней");
        } else if (idsp.size()==30) {
            binding.daysp.setText("Вы выполнили челлендж!");
        }

        for (int i = 0; i < massButtonp.length; i++) {
            if (idsp.contains(massButtonp[i].getId()))
                massButtonp[i].setChecked(true);
        }

        for (RadioButton radioButtonp : massButtonp) {
            radioButtonp.setOnClickListener(v -> {
                ClickPersonal(radioButtonp.getId());
            });
        }

        binding.btnp.setOnClickListener(v ->{
            if (idsp.size() != 0){
                for (int i = 0; i < massButtonp.length; i++) {
                    if (idsp.contains(massButtonp[i].getId()))
                        massButtonp[i].setChecked(false);
                }
                idsp.clear();
                binding.progressBarP.setProgress(idsp.size());
                binding.daysp.setText("Выполнено " + idsp.size() + " из 30 дней");
            }
        });
        binding.progressBarP.setProgress(idsp.size());
    }

    private TreeSet<Integer> Selectp(ArrayList<Integer> saveListp) {
        if (idsp == null) {
            idsp = new TreeSet<>();
            for (Integer xp : saveListp) {
                idsp.add(xp);
            }
            return idsp;
        } else return idsp;
    }

    private ArrayList<Integer> getListp(){
        ArrayList<Integer> templistp = new ArrayList<>();
        String strp = sharedPreferencesp.getString(KEY_RADIO_BUTTON_P, "нетушки");
        if (strp.equals("нетушки"))
            return new ArrayList<>();
        else {
            try {
                String[] tempp = strp.split(",");
                for (int p = 0; p < tempp.length; p++) {
                    templistp.add(Integer.parseInt(tempp[p]));
                }
            }catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return templistp;
    }

    private final static String sharePref = "process";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outStatep) {
        super.onSaveInstanceState(outStatep);
        if (saveListp == null) {
            saveListp = new ArrayList<>();
            saveListp.addAll(idsp);
        }
        outStatep.putIntegerArrayList(sharePref, saveListp);
        StringBuilder sbp = new StringBuilder();
        for (int p = 0; p < saveListp.size(); p++) {
            sbp.append(saveListp.get(p)).append(",");
        }
        SharedPreferences.Editor prefEditorp = sharedPreferencesp.edit();
        prefEditorp.putString(KEY_RADIO_BUTTON_P, String.valueOf(sbp));
        prefEditorp.apply();
    }

    public void ClickPersonal(int id) {
        if (idsp.size() < 30) {
            idsp.add(id);
        }
        binding.progressBarP.setProgress(idsp.size());
        binding.daysp.setText("Выполнено " + idsp.size() + " из 30 дней");
        if (idsp.size() == 30) {
            Snackbar.make(binding.getRoot(), "Поздравляем с выполнением 30-дневного челленджа!\uD83C\uDF89", Toast.LENGTH_SHORT).show();
        }
    }
}