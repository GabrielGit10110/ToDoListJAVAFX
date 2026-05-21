package edu.curso.controller;

import java.time.LocalDate;

import edu.curso.model.Task;
import edu.curso.repository.TaskRepository;
import edu.curso.repository.impl.JsonTaskRepository;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TaskController {
    private TaskRepository taskList = new JsonTaskRepository();

    private StringProperty title = new SimpleStringProperty("");
    private StringProperty description = new SimpleStringProperty("");
    private BooleanProperty finished = new SimpleBooleanProperty(false);
    private ObjectProperty<LocalDate> dateFinished = new SimpleObjectProperty<>(null);

    private IntegerProperty total = new SimpleIntegerProperty(0);
    private IntegerProperty pendente = new SimpleIntegerProperty(0);
    private IntegerProperty concluida = new SimpleIntegerProperty(0);

    
    public ObservableList<Task> getAllTasks() {
        return taskList.getAll();
    }

    public void finishedTask(Task t) {
        t.setFinished(true);

        this.concluida.set(this.concluida.get() + 1);
        this.pendente.set(this.pendente.get() - 1);
    }

    public void unfinishTask(Task t) {
        t.setFinished(false);
        this.concluida.set(this.concluida.get() - 1);
        this.pendente.set(this.pendente.get() + 1);
    }

    public void fromEntity(Task t) {
        if (t != null) {
            this.title.set(t.getTitle());
            this.description.set(t.getDescription());
            this.finished.set(t.isFinished());
            this.dateFinished.set(t.getDateFinished());
        }
    }

    public Task toEntity() {
        Task t = new Task();
        t.setTitle(this.title.get());
        t.setDescription(this.description.get());
        t.setFinished(this.finished.get());
        t.setDateFinished(this.dateFinished.get());
        return t;
    }
    
    public Task saveAndReturn() {
        Task t = toEntity();        

        this.taskList.add(t);               

        total.set(total.get() + 1);
        pendente.set(pendente.get() + 1);

        return t;
    }

    public String deleteItem(Task t) {
        String taskInformation = t.toString();
        System.out.println("Deletando tarefa: '" + t.getTitle() + "'");

        t.setDateFinished(null);
        t.setDescription(null);
        t.setTitle(null);
        t.setFinished(false);

        this.total.set(this.total.get() - 1);

        if (t.isFinished()) {
            this.concluida.set(this.concluida.get() - 1);
        } else {
            if (this.getPendente() > 0) {
                this.pendente.set(this.pendente.get() - 1);
            }
            
        }

        this.taskList.remove(t);
        return taskInformation;
    }

    public int getTotal() {
        return this.total.get();
    }

    public int getPendente() {
        return this.pendente.get();
    }

    public int getConcluida() {
        return this.concluida.get();
    }

    public String getTitle() {
        return this.title.get();
    }

    public StringProperty titleProperty() {
        return this.title;
    }

    public String getDescription() {
        return this.description.get();
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }

    public boolean getFinished() {
        return this.finished.get();
    }

    public BooleanProperty finishedProperty() {
        return this.finished;
    }

    public LocalDate getDateFinished() {
        return this.dateFinished.get();
    }

    public ObjectProperty<LocalDate> dateFinishedProperty() {
        return this.dateFinished;
    }
    
    public IntegerProperty totalProperty() {
        return total;
    }

    public IntegerProperty pendenteProperty() {
        return pendente;
    }

    public IntegerProperty concluidaProperty() {
        return concluida;
    }

}
