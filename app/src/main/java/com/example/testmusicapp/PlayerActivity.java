package com.example.testmusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testmusicapp.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {

    ActivityPlayerBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}