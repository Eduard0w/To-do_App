package com.Trabalho.todo_list.model;

public class Tarefa {
    private String tarefa;
    private boolean concluida;

    public Tarefa(String t){
        this.tarefa = t;
        this.concluida = false;
    }


    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
