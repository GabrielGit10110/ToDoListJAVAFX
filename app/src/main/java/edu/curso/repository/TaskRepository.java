package edu.curso.repository;

import edu.curso.model.Task;
import javafx.collections.ObservableList;

public interface TaskRepository {
    ObservableList<Task> getAll();

    void add(Task t);

    void remove(Task t);
}
