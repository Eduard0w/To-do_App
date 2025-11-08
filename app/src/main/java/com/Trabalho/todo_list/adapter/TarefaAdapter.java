package com.Trabalho.todo_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Trabalho.todo_list.R;
import com.Trabalho.todo_list.dao.DaoTarefa;
import com.Trabalho.todo_list.model.Tarefa;

import java.util.ArrayList;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private final ArrayList<Tarefa> tarefas;

    private Listener listener;
    public TarefaAdapter() {
        tarefas = DaoTarefa.getInstance().getTarefas();
    }

    public interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
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
        RadioButton radioButton = card.findViewById(R.id.radioButton);

        Tarefa tarefa = tarefas.get(position);

        tarefaNome.setText(tarefa.getTarefa());

        // Evita que o listener antigo dispare durante a atualização do estado
        radioButton.setOnCheckedChangeListener(null);
        radioButton.setChecked(tarefa.isConcluida());

        radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                int posAtual = holder.getAbsoluteAdapterPosition();
                if(posAtual != RecyclerView.NO_POSITION){
                    listener.onClick(posAtual);
                }
            }
//            if (isChecked) {
//                tarefa.setConcluida(true);
//
//                int adapterPosition = holder.getBindingAdapterPosition();
//                if (adapterPosition != RecyclerView.NO_POSITION &&
//                        adapterPosition >= 0 &&
//                        adapterPosition < tarefas.size()) {
//
//                    // Remove com segurança do DAO e da lista local
//                    DaoTarefa.getInstance().removerTarefa(tarefa);
//                    tarefas.remove(adapterPosition);
//
//                    // Atualiza o RecyclerView de forma segura
//                    notifyItemRemoved(adapterPosition);
//                }
//            }
        });
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public TarefaViewHolder(@NonNull CardView view) {
            super(view);
            this.cardView = view;
        }
    }
}
