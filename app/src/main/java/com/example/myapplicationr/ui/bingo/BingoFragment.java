package com.example.myapplicationr.ui.bingo;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import java.util.TreeSet;


public class BingoFragment extends Fragment {

    private static final String KEY_LL = "LL";
    SharedPreferences sharedPreferences;
    ArrayList<Integer> saveListLL;
    private FragmentBingoBinding binding;
    Dialog dialog;
    LinearLayout ll;
    private static TreeSet<Integer> idll;

    public static ArrayList<String> tasksList;
    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBingoBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(KEY_LL, MODE_PRIVATE);
        mDBHelper = new DatabaseHelper(getActivity());

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        return binding.getRoot();
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

        tasksList = getTasksList();
        idll = SelectLL(saveListLL());

        for (int k = 0; k < massl.length; k++) {
            if (idll.contains(massl[k].getId())) {
                ll = massl[k];
                ll.setBackgroundColor(ContextCompat.getColor(ll.getContext(), R.color.green));
            }
        }
        
        for (int i = 0; i < massl.length; i++) {
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.task);
            int finalI = i;
            int finalI1 = i;
            massl[i].setOnClickListener(v -> {
                tvtext(finalI1);
                dialog.show();
                ll = massl[finalI];
                ll.setBackgroundColor(ContextCompat.getColor(ll.getContext(), R.color.green));
            });
        }
    }

    private TreeSet<Integer> SelectLL(ArrayList<Integer> saveList) {

        if (idll == null) {
            idll = new TreeSet<>();
            for (Integer x : saveList) {
                idll.add(x);
            }
            return idll;
        } else return idll;
    }

    private ArrayList<Integer> saveListLL() {
        ArrayList<Integer> lllist = new ArrayList<>();
        String strll = sharedPreferences.getString(KEY_LL, "неопределено");
        if (strll.equals("неопределено"))
            return new ArrayList<>();
        else {
            try {
                String[] ll  = strll.split(",");
                for (int i = 0; i < ll.length; i++) {
                    lllist.add(Integer.parseInt(ll[i]));
                }
            }catch (NumberFormatException e){
                System.out.println(e);
            }
        }
        return lllist;
    }

    private final static String sharePrefll = "processll";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (saveListLL == null){
            saveListLL = new ArrayList<>();
            saveListLL.addAll(idll);
        }
        outState.putIntegerArrayList(sharePrefll, saveListLL);
        StringBuilder sbll = new StringBuilder();
        for (int i = 0; i < saveListLL.size(); i++) {
            sbll.append(saveListLL.get(i)).append(",");
        }
        SharedPreferences.Editor prefEditorLL = sharedPreferences.edit();
        prefEditorLL.putString(KEY_LL, String.valueOf(sbll));
        prefEditorLL.apply();
    }
    public ArrayList<String> getTasksList(){
        ArrayList<String> tasks = new ArrayList<>();

        Cursor cursor = mDb.rawQuery("SELECT tasks FROM taskstable", null);
        int index = cursor.getColumnIndex("tasks"); // замените "Tasks" на "tasks"
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tasks.add(cursor.getString(index));
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }
    private void tvtext(int i) {
        TextView tv = (TextView) dialog.findViewById(R.id.tasktext);
        for (int j = 0; j < tasksList.size(); j++) {
            tv.setText(tasksList.get(i));
        }
    }


}