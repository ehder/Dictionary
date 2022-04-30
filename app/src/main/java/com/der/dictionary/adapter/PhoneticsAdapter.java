package com.der.dictionary.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.der.dictionary.R;
import com.der.dictionary.model.Phonetics;

import java.io.IOException;
import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsAdapter.ViewHolder> {


    private List<Phonetics> phonetics;
    private Context context;

    public PhoneticsAdapter(List<Phonetics> phonetics, Context context) {
        this.phonetics = phonetics;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonetic_list_item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_phonetic.setText(phonetics.get(position).getText());
        String pos = phonetics.get(position).getAudio();

        holder.imgBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                //Toast.makeText(context, phonetics.get(position).getAudio(), Toast.LENGTH_SHORT).show();
                try {
                    //TODO : Audio Stream Music dose not work.
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https:" + pos);
                    Toast.makeText(context, pos, Toast.LENGTH_SHORT).show();
                    //player.prepare();
                    player.prepareAsync();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return phonetics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_phonetic;
        private ImageButton imgBtnAudio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_phonetic = itemView.findViewById(R.id.tv_phonetics);
            imgBtnAudio = itemView.findViewById(R.id.img_btn_audio);
        }
    }
}
