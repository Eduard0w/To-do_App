package com.Trabalho.todo_list;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Trabalho.todo_list.adapter.TarefaAdapter;
import com.Trabalho.todo_list.dao.DaoTarefa;
import com.Trabalho.todo_list.model.Tarefa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TarefaAdapter.Listener {
    private TarefaAdapter tarefaAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tarefaAdapter = new TarefaAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(tarefaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaAdapter.setListener(this);
    }

    @Override
    public void onClick(int position) {
        // Pega a lista atual de tarefas do DAO
        ArrayList<Tarefa> tarefas = DaoTarefa.getInstance().getTarefas();

        // Verifica se a posição é válida
        if (position >= 0 && position < tarefas.size()) {
            // Pega a tarefa que precisa ser removida
            Tarefa tarefaParaRemover = tarefas.get(position);

            // Remove a tarefa da fonte de dados (DAO)
            DaoTarefa.getInstance().removerTarefa(tarefaParaRemover);

            // Notifica o adapter que o item foi removido
            tarefaAdapter.notifyItemRemoved(position);

            // Notifica o adapter que o range de itens mudou
            // Isso previne erros de "Inconsistency detected"
            tarefaAdapter.notifyItemRangeChanged(position, tarefas.size());
        }
    }
}