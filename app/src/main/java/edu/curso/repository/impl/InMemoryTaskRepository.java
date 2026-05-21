package edu.curso.repository.impl;

import edu.curso.model.Task;
import edu.curso.repository.TaskRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InMemoryTaskRepository implements TaskRepository{
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public ObservableList<Task> getAll() {
        return this.tasks;
    }

    @Override
    public void add(Task t) {
        this.tasks.add(t);
    }

    @Override
    public void remove(Task t) {
        this.tasks.remove(t);
    }
    
}
