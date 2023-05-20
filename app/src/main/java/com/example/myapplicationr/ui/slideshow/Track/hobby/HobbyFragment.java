package com.example.myapplicationr.ui.slideshow.Track.hobby;

import android.annotation.SuppressLint;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.TreeSet;

import static android.content.Context.MODE_PRIVATE;

public class HobbyFragment extends Fragment {

    private static final String KEY_RADIO_BUTTON = "RADIO_BUTTON";
    SharedPreferences sharedPreferences;
    ArrayList<Integer> saveList;
    private static TreeSet<Integer> ids;
    private FragmentHobbyBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHobbyBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(KEY_RADIO_BUTTON, MODE_PRIVATE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton[] massButtons = {binding.hobbyButton1, binding.hobbyButton2, binding.hobbyButton3, binding.hobbyButton4, binding.hobbyButton5,
                binding.hobbyButton6, binding.hobbyButton7, binding.hobbyButton8, binding.hobbyButton9, binding.hobbyButton10, binding.hobbyButton11,
                binding.hobbyButton12, binding.hobbyButton13, binding.hobbyButton14, binding.hobbyButton15, binding.hobbyButton16, binding.hobbyButton17,
                binding.hobbyButton18, binding.hobbyButton19, binding.hobbyButton20, binding.hobbyButton21, binding.hobbyButton22, binding.hobbyButton23,
                binding.hobbyButton24, binding.hobbyButton25, binding.hobbyButton26, binding.hobbyButton27, binding.hobbyButton28, binding.hobbyButton29,
                binding.hobbyButton30};


        ids = Select(getList());
        for (int i = 0; i < massButtons.length; i++) {
            if (ids.contains(massButtons[i].getId()))
                massButtons[i].setChecked(true);
        }

        for (RadioButton radioButton : massButtons) {
            radioButton.setOnClickListener(v -> {
                Clickhobby(radioButton.getId());
            });
        }
    }

    private TreeSet<Integer> Select(ArrayList<Integer> saveList) {

        if (ids == null) {
            ids = new TreeSet<>();
            for (Integer x : saveList) {
                ids.add(x);
            }
            return ids;
        } else return ids;
    }

    private ArrayList<Integer> getList() {
        ArrayList<Integer> templist = new ArrayList<>();
        String str = sharedPreferences.getString(KEY_RADIO_BUTTON, "не определено");
        if (str.equals("не определено"))
            return new ArrayList<>();
        else {
            String[] temp = str.split(",");
            for (int i = 0; i < temp.length; i++) {
                templist.add(Integer.parseInt(temp[i]));
            }
        }
        return templist;
    }

    private final static String sharePref = "process";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (saveList == null) {
            saveList = new ArrayList<>();
            saveList.addAll(ids);
        }
        outState.putIntegerArrayList(sharePref, saveList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < saveList.size(); i++) {
            sb.append(saveList.get(i)).append(",");
        }
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(KEY_RADIO_BUTTON, String.valueOf(sb));
        prefEditor.apply();
    }

    public void Clickhobby(int id) {
        if (ids.size() < 30) {
            ids.add(id);
        }
        binding.progressBarH.setProgress(ids.size());
        binding.daysh.setText("Выполнено " + ids.size() + " из 30 дней");
        if (ids.size() == 30) {
            Snackbar.make(binding.getRoot(), "Поздравляем с выполнением 30-дневного челленджа!\uD83C\uDF89", Toast.LENGTH_SHORT).show();
        }
    }
}