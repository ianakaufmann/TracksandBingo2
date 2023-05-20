package com.example.myapplicationr.ui.slideshow.Track.lesson;


import android.annotation.SuppressLint;
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

import com.example.myapplicationr.databinding.FragmentHobbyBinding;
import com.example.myapplicationr.databinding.FragmentLessonBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.TreeSet;

public class LessonFragment extends Fragment {
    private static final String KEY_RADIO_BUTTON_L = "RADIO_BUTTON_L";
    SharedPreferences sharedPreferencesl;
    ArrayList<Integer> saveListl;
    private static TreeSet<Integer> idsl;
    private FragmentLessonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLessonBinding.inflate(inflater, container, false);
        sharedPreferencesl = getContext().getSharedPreferences(KEY_RADIO_BUTTON_L, Context.MODE_PRIVATE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton[] massButtonl = {binding.lb1, binding.lb2, binding.lb3, binding.lb4, binding.lb5,
                binding.lb6, binding.lb7, binding.lb8, binding.lb9, binding.lb10, binding.lb11, binding.lb12,
                binding.lb13, binding.lb14, binding.lb15, binding.lb16, binding.lb17, binding.lb18, binding.lb19,
                binding.lb20, binding.lb21, binding.lb22, binding.lb23, binding.lb24, binding.lb25, binding.lb26,
                binding.lb27, binding.lb28, binding.lb29, binding.lb30};

        idsl = Selectl(getListl());
        for (int i = 0; i < massButtonl.length; i++) {
            if (idsl.contains(massButtonl[i].getId()))
                massButtonl[i].setChecked(true);
        }

        for (RadioButton radioButtonl : massButtonl) {
            radioButtonl.setOnClickListener(v -> {
                ClickLesson(radioButtonl.getId());
            });
        }
    }

    private TreeSet<Integer> Selectl(ArrayList<Integer> saveListl) {
        if (idsl == null) {
            idsl = new TreeSet<>();
            for (Integer xl : saveListl) {
                idsl.add(xl);
            }
            return idsl;
        } else return idsl;
    }

    private ArrayList<Integer> getListl(){
        ArrayList<Integer> templistl = new ArrayList<>();
        String strl = sharedPreferencesl.getString(KEY_RADIO_BUTTON_L, "нет");
        if (strl.equals("нет"))
            return new ArrayList<>();
        else {
            String[] templ = strl.split(",");
            for (int l = 0; l < templ.length; l++) {
                templistl.add(Integer.parseInt(templ[l]));
            }
        }
        return templistl;
    }

    private final static String sharePrefl = "processl";


    @Override
    public void onSaveInstanceState(@NonNull Bundle outStatel) {
        super.onSaveInstanceState(outStatel);
        if (saveListl == null) {
            saveListl = new ArrayList<>();
            saveListl.addAll(idsl);
        }
        outStatel.putIntegerArrayList(sharePrefl, saveListl);
        StringBuilder sbl = new StringBuilder();
        for (int l = 0; l < saveListl.size(); l++) {
            sbl.append(saveListl.get(l)).append(",");
        }
        SharedPreferences.Editor prefEditorl = sharedPreferencesl.edit();
        prefEditorl.putString(KEY_RADIO_BUTTON_L, String.valueOf(sbl));
        prefEditorl.apply();
    }

    public void ClickLesson(int idl) {
        if (idsl.size() < 30) {
            idsl.add(idl);
        }
        binding.progressBarL.setProgress(idsl.size());
        binding.daysl.setText("Выполнено " + idsl.size() + " из 30 дней");
        if (idsl.size() == 30) {
            Snackbar.make(binding.getRoot(), "Поздравляем с выполнением 30-дневного челленджа!\uD83C\uDF89", Toast.LENGTH_SHORT).show();
        }
    }
}