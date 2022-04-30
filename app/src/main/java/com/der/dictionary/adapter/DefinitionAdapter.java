package com.der.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.der.dictionary.R;
import com.der.dictionary.model.Definitions;
import com.der.dictionary.model.Phonetics;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.ViewHolder> {

    private List<Definitions> definitions;
    private Context context;

    public DefinitionAdapter(List<Definitions> definitions, Context context) {
        this.definitions = definitions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_list_item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDefinition.setText("Definitions: "+definitions.get(position).getDefinition());
        holder.tvExample.setText("Example: "+definitions.get(position).getExample());

        StringBuffer synonyms = new StringBuffer();
        synonyms.append(definitions.get(position).getSynonyms());
        StringBuffer antonyms = new StringBuffer();
        antonyms.append(definitions.get(position).getAntonyms());

        holder.tvSynonyms.setText("Synonyms: "+ synonyms);
        holder.tvSynonyms.setSelected(true);
        holder.tvAntonyms.setText("Antonyms: "+ antonyms);
        holder.tvAntonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDefinition, tvExample, tvSynonyms, tvAntonyms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDefinition = itemView.findViewById(R.id.tv_definition);
            tvExample = itemView.findViewById(R.id.tv_example);
            tvSynonyms = itemView.findViewById(R.id.tv_synonyms);
            tvAntonyms = itemView.findViewById(R.id.tv_antonyms);

        }
    }
}
