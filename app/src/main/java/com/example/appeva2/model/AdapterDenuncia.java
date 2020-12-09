package com.example.appeva2.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeva2.R;

import java.util.List;

public class AdapterDenuncia extends RecyclerView.Adapter <AdapterDenuncia.DenunciaHolder> {
    private Activity activity;
    private int layout;
    private List<Denuncia> list;

    public AdapterDenuncia(Activity activity, int layout, List<Denuncia> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent,false);
        return new DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncia denuncia = list.get(position);
        holder.title.setText(denuncia.getTitle());
        holder.direction.setText(denuncia.getDirection());
        holder.id = denuncia.getId();
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


    public class DenunciaHolder extends RecyclerView.ViewHolder{
        TextView title, direction,name;
        String id;

        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_denuncia_title);
            direction = itemView.findViewById(R.id.item_denuncia_direction);

        }
    }


}
