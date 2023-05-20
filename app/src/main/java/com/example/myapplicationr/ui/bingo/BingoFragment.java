package com.example.myapplicationr.ui.bingo;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationr.MainActivity;
import com.example.myapplicationr.R;
import com.example.myapplicationr.databinding.FragmentBingoBinding;
import com.example.myapplicationr.databinding.FragmentHobbyBinding;
import com.example.myapplicationr.ui.bingo.data.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class BingoFragment extends Fragment {
    private FragmentBingoBinding binding;
    Dialog dialog;
    LinearLayout ll;

    public static ArrayList<String> tasksList;
    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBingoBinding.inflate(inflater, container, false);

        tasksList = getTasksList();

        return binding.getRoot();
    }


    public ArrayList<String> getTasksList(){
        ArrayList<String> tasks = new ArrayList<>();

        Cursor cursor = mDb.rawQuery("SELECT Tasks FROM database_trackandbingo", null);
        int index = cursor.getColumnIndex("Tasks");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tasks.add(cursor.getString(index));
            cursor.moveToNext();
        }
        cursor.close();

        return tasks;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDBHelper = new DatabaseHelper(getActivity());


        LinearLayout[] massl = {binding.bingo1, binding.bingo2, binding.bingo3, binding.bingo4, binding.bingo5,
                binding.bingo6, binding.bingo7, binding.bingo8, binding.bingo9, binding.bingo10,
                binding.bingo11, binding.bingo12, binding.bingo13, binding.bingo14, binding.bingo15,
                binding.bingo16, binding.bingo17, binding.bingo18, binding.bingo19, binding.bingo20,
                binding.bingo21, binding.bingo22, binding.bingo23, binding.bingo24, binding.bingo25,
                binding.bingo26, binding.bingo27, binding.bingo28, binding.bingo29, binding.bingo30};

        for (int i = 0; i < massl.length; i++) {
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.task);
            TextView tv = (TextView) dialog.findViewById(R.id.tasktext);
            massl[i].setOnClickListener(v -> {
                tv.setText("Если получится, этот код писала Яна, а если нет, то я не знаю кто его писал.");
                dialog.show();
                ll.setBackgroundColor(ContextCompat.getColor(ll.getContext(), R.color.green));
                binding.tx1.setText("Выполнено");
            });
        }

//        for (LinearLayout lal: massl) {
//            lal.setOnClickListener(v -> ClickL(lal.getId()));
//        }

//        ll = binding.bingo1;
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.task);
//        TextView tv = (TextView) dialog.findViewById(R.id.tasktext);
//        tv.setText("Если получится, этот код писала Яна, а если нет, то я не знаю кто его писал.");
//        binding.bingo1.setOnClickListener(v -> {
//                dialog.show();
//                ll.setBackgroundColor(ContextCompat.getColor(ll.getContext(), R.color.green));
//                binding.tx1.setText("Выполнено");
//        });
    }
}