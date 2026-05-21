package edu.curso.repository.impl;

import edu.curso.model.Task;
import edu.curso.repository.TaskRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTaskRepository implements TaskRepository {
    File dir = new File(System.getProperty("user.home"), "Documents/Java/ToDoList");

    File file = new File(dir, "tasks.json");

    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public JsonTaskRepository() {
        createDirs();
        load();
    }

    private void createDirs() {
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        }
    }

    private void load() {
        try {
            if (file.exists()) {
                List<Task> list = mapper.readValue(file, new TypeReference<List<Task>>() {});
                tasks.addAll(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Task> getAll() {
        return tasks;
    }

    @Override
    public void add(Task t) {
        tasks.add(t);
        save();
    }

    @Override
    public void remove(Task t) {
        tasks.remove(t);
        save();
    }
}