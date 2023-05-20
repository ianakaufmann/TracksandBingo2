package com.example.myapplicationr.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationr.R;
import com.example.myapplicationr.databinding.FragmentSlideshowBinding;
import com.example.myapplicationr.ui.slideshow.Track.hobby.HobbyFragment;
import com.example.myapplicationr.ui.slideshow.Track.lesson.LessonFragment;
import com.example.myapplicationr.ui.slideshow.Track.personal.PersonalFragment;
import com.example.myapplicationr.ui.slideshow.Track.sport.SportFragment;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.hobby.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new HobbyFragment())
                    .addToBackStack(null)
                    .commit();
        });

        binding.lesson.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new LessonFragment())
                    .addToBackStack(null)
                    .commit();
        });

        binding.personal.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new PersonalFragment())
                    .addToBackStack(null)
                    .commit();
        });

        binding.sport.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new SportFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}