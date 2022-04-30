package com.der.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.der.dictionary.R;
import com.der.dictionary.model.Meaning;
import com.der.dictionary.model.Phonetics;

import java.util.List;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningsAdapter.ViewHolder> {

    private List<Meaning> meanings;
    private Context context;

    public MeaningsAdapter(List<Meaning> meanings, Context context) {
        this.meanings = meanings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_list_item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(meanings.get(position).getDefinitions(), context);

        holder.tvPartsOfSpeech.setText("Parts of speech: "+meanings.get(position).getPartOfSpeech());
        holder.recyclerDefinitions.setAdapter(definitionAdapter);
        holder.recyclerDefinitions.setHasFixedSize(true);
        holder.recyclerDefinitions.setLayoutManager(new GridLayoutManager(context, 1));

    }

    @Override
    public int getItemCount() {
        return meanings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvPartsOfSpeech;
        RecyclerView recyclerDefinitions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartsOfSpeech = itemView.findViewById(R.id.tv_partsOfSpeech);
            recyclerDefinitions = itemView.findViewById(R.id.recycler_definitions);
        }
    }
}
