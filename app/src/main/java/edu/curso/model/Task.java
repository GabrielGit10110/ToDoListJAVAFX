package edu.curso.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Task {

    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private BooleanProperty finished = new SimpleBooleanProperty(false);
    private ObjectProperty<LocalDate> dateFinished = new SimpleObjectProperty<>();

    public StringProperty titleProperty() { return title; }
    public String getTitle() { return title.get(); }
    public void setTitle(String value) { title.set(value); }

    public StringProperty descriptionProperty() { return description; }
    public String getDescription() { return description.get(); }
    public void setDescription(String value) { description.set(value); }

    public BooleanProperty finishedProperty() { return finished; }
    public boolean isFinished() { return finished.get(); }
    public void setFinished(boolean value) { finished.set(value); }

    public ObjectProperty<LocalDate> dateFinishedProperty() { return dateFinished; }
    public LocalDate getDateFinished() { return dateFinished.get(); }
    public void setDateFinished(LocalDate value) { dateFinished.set(value); }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Informacoes da tarefa:");
        msg.append("\n").append("\n");
        
        msg.append("Titulo: ").append(this.getTitle()).append("\n");

        if (!this.getDescription().isEmpty()) {
            msg.append("Descricao: ").append(this.getDescription()).append("\n");
        }

        msg.append("Status: ");

        if (this.isFinished()) {
            msg.append("Finalizada");
        } else {
            msg.append("Nao finalizada");
        }

        msg.append("\n");
        

        if (this.getDateFinished() != null) {
            msg.append("Data de conclusao: ").append(this.getDateFinished());
        }

        return msg.toString();
    }
}
