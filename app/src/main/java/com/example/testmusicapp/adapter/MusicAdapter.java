package com.example.testmusicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmusicapp.PlayerActivity;
import com.example.testmusicapp.R;
import com.example.testmusicapp.databinding.MusicItemBinding;
import com.example.testmusicapp.model.MusicFiles;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    Context context;
    private ArrayList<MusicFiles> mFiles;
    private SongItemClick songItemClick;

    public MusicAdapter(Context context, ArrayList<MusicFiles> mFiles, SongItemClick songItemClick) {
        this.context = context;
        this.mFiles = mFiles;
        this.songItemClick = songItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MusicItemBinding binding =
                MusicItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MusicFiles mF = mFiles.get(position);

        holder.binding.musicName.setText(mF.getTitle());
        byte[] image = getAlbumImage(mF.getPath());
        if (image != null)
            Glide.with(context).asBitmap()
                    .load(image)
                    .into(holder.binding.musicImg);


    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MusicItemBinding binding;

        public MyViewHolder(@NonNull MusicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songItemClick.songClicked(mFiles.get(getLayoutPosition()));
                }
            });
        }
    }

    private byte[] getAlbumImage(String uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    public interface SongItemClick {
        public void songClicked(MusicFiles musicFiles);
    }

}
