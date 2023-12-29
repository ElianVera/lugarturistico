package com.example.lugarturistico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.LugarViewHolder> {

    private ArrayList<Lugar> lugares;

    public LugarAdapter(ArrayList<Lugar> lugares) {
        this.lugares = lugares;
    }

    public static class LugarViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private TextView textViewTelefono;

        public LugarViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreLugar);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefonoLugar);
        }

        public void bindData(Lugar lugar) {
            textViewNombre.setText(lugar.getNombre());
            textViewTelefono.setText(lugar.getTelefono());
        }
    }

    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new LugarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarViewHolder holder, int position) {
        holder.bindData(lugares.get(position));
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }
}
