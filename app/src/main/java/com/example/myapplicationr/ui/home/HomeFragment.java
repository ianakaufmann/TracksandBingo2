package com.example.myapplicationr.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.myapplicationr.R;

import com.example.myapplicationr.databinding.FragmentHomeBinding;
import com.example.myapplicationr.ui.bingo.GalleryFragment;
import com.example.myapplicationr.ui.slideshow.SlideshowFragment;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.contenerHome.trekButton.setOnClickListener(view1 -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new SlideshowFragment())
                    .addToBackStack(null)
                    .commit();
        });

        binding.contenerHome.bingoButton.setOnClickListener(view1 -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new GalleryFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }


}


