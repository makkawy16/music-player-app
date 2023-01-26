package com.example.testmusicapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.testmusicapp.fragments.AlbumsFragment;
import com.example.testmusicapp.fragments.SongsFragment;

public class fragmentViewpager extends FragmentStateAdapter {
    public fragmentViewpager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new SongsFragment();
            case 1:
                return new AlbumsFragment();
            default:
                return new SongsFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
