package com.example.testmusicapp.fragments;

import static com.example.testmusicapp.MainActivity.musicFiles;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testmusicapp.PlayerActivity;
import com.example.testmusicapp.R;
import com.example.testmusicapp.adapter.MusicAdapter;
import com.example.testmusicapp.databinding.FragmentSongsBinding;
import com.example.testmusicapp.model.MusicFiles;


public class SongsFragment extends Fragment implements MusicAdapter.SongItemClick {

    MusicAdapter musicAdapter;
    FragmentSongsBinding binding;

    public SongsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view =inflater.inflate(R.layout.fragment_songs, container, false);
        binding = FragmentSongsBinding.bind(view);
        binding.musicRecycler.setHasFixedSize(true);

        if (!(musicFiles.size() < 1))
        {
            musicAdapter = new MusicAdapter(getContext(),musicFiles , this);
            binding.musicRecycler.setAdapter(musicAdapter);
            binding.musicRecycler.setLayoutManager(new LinearLayoutManager(getContext(),binding.musicRecycler.VERTICAL,
                    false));
        }
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentSongsBinding.bind(view);
    }

    @Override
    public void songClicked(MusicFiles musicFiles) {
        startActivity(new Intent(getActivity(), PlayerActivity.class));
    }
}