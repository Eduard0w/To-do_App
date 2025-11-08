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

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton;
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
    }
}