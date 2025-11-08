package com.Trabalho.todo_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Trabalho.todo_list.R;
import com.Trabalho.todo_list.dao.DaoTarefa;
import com.Trabalho.todo_list.model.Tarefa;

import java.util.ArrayList;

//Essa classe é a ponte entre os dados/BD(tarefa, DAO) e a interface, a parte visual do app.
public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    public ArrayList<Tarefa> tarefas;

    public TarefaAdapter(){
        tarefas = DaoTarefa.getInstance().getTarefas();
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView cv = (CardView) inflater.inflate(R.layout.box_to_do, parent, false);
        return new TarefaViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        CardView card = holder.cardView;
        TextView tarefaNome = card.findViewById(R.id.textoTarefa);

        tarefaNome.setText(tarefas.get(position).getTarefa());
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public TarefaViewHolder(@NonNull CardView view) {
            super(view);
            cardView = view;
        }
    }
}
