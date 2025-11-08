package com.Trabalho.todo_list.dao;

import com.Trabalho.todo_list.model.Tarefa;

import java.util.ArrayList;

public class DaoTarefa {
    //inicialização das tarefas, ou seja, onde elas vão ser inicializadas, salvas e criadas.
    //Intermediario entre a aplicação e o "banco de dados" que armazena as tarefas.

    private ArrayList<Tarefa> listaTarefas;
    private static DaoTarefa instance = null;

    private DaoTarefa(){
        listaTarefas = new ArrayList<>();
        gerarTarefas();
    }

    public static DaoTarefa getInstance(){
        if(instance == null) {
            instance = new DaoTarefa();
        }
        return instance;
    }



    public void gerarTarefas(){
        this.listaTarefas.add(new Tarefa("Orar"));
        this.listaTarefas.add(new Tarefa("Levar lixo para fora"));
        this.listaTarefas.add(new Tarefa("Levar cachorro para passear"));
    }


    public ArrayList<Tarefa> getTarefas(){
        return this.listaTarefas;
    }
}
