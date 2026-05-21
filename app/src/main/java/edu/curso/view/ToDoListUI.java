package edu.curso.view;

import edu.curso.controller.TaskController;
import edu.curso.model.Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.control.DatePicker;
import javafx.beans.binding.Bindings;

public class ToDoListUI extends Application {

    private GridPane gridTasks = new GridPane();
    private ScrollPane scrollTasks = new ScrollPane();
    private GridPane gridStatus = new GridPane();

    private final TaskController controller = new TaskController();

    private TextField txtNewTask = new TextField();
    private TextArea txaDesc = new TextArea();
    private Button btnAddTask = new Button("Adicionar");

    private Label lblTotalVlr = new Label("0");
    private Label lblPendenteVlr = new Label("0");
    private Label lblConcluidaVlr = new Label("0");

    private int col;

    private void finishTask(CheckBox c, DatePicker d, Task t) {
            if (c.isSelected()) {
                d.setVisible(true);
                this.controller.finishedTask(t);
                System.out.println("A tarefa: "+t.getTitle()+" foi finalizada...");
            } else {
                d.setVisible(false);
                this.controller.unfinishTask(t);
                System.out.println("A tarefa: "+t.getTitle()+" deixou de ser finalizada...");
            }
    }

    private void removeFromUI(HBox tasks) {
            this.gridTasks.getChildren().remove(tasks);
    }

    private void newTaskMsg(Task t) {
        StringBuilder msg = new StringBuilder("Nova tarefa criada: ");
        msg.append("Nome: ");
        msg.append(t.getTitle()).append("\n");
        
        if (!t.getDescription().isEmpty()) {
            msg.append("Descricao: ");
            msg.append(t.getDescription()).append("\n");
        }

        System.out.println(msg.toString());
    }

    private void newDateMsg(Task t) {
        System.out.println("Tarefa: "+t.getTitle()+" foi concluida em: "+t.getDateFinished());
    }

    private void updateDescriptionMsg(Task t) {
        System.out.println("Tarefa: "+t.getTitle()+" teve sua descricao mudada para: "+t.getDescription());
    }

    public void createItem(Task t) {
        GridPane thisTask = new GridPane();
        GridPane buttonsTask = new GridPane();
        HBox taskBox = new HBox(10);
        taskBox.getChildren().addAll(thisTask, buttonsTask);

        CheckBox chConcluida = new CheckBox(t.getTitle());
        TextArea txaDescricao = new TextArea(
            t.getDescription() != null ? t.getDescription() : ""
        );
        
        Bindings.bindBidirectional(txaDescricao.textProperty(),t.descriptionProperty());
        txaDescricao.setPrefRowCount(2);

        DatePicker dateFinished = new DatePicker();
        dateFinished.setVisible(false);

        Bindings.bindBidirectional(dateFinished.valueProperty(),t.dateFinishedProperty());

        Button btnDeletar = new Button("Deletar");
        Button btnAtualizar = new Button("Atualizar");

        thisTask.setPadding(new Insets(10));
        thisTask.setHgap(10);
        thisTask.setVgap(10);

        buttonsTask.setPadding(new Insets(10));
        buttonsTask.setVgap(5);

        chConcluida.setOnAction(e -> {
            finishTask(chConcluida, dateFinished, t);
        });

        dateFinished.valueProperty().addListener((obs, oldVal, newVal) -> {
            newDateMsg(t);
        });


        btnDeletar.setOnAction(e -> {
            removeFromUI(taskBox);
            System.out.println(this.controller.deleteItem(t));
        });

        btnAtualizar.setOnAction(e -> {
            updateDescriptionMsg(t);
        });

        thisTask.add(chConcluida, 0, 0);
        thisTask.add(txaDescricao, 0, 1);
        thisTask.add(dateFinished, 1, 0);

        buttonsTask.add(btnDeletar, 0, 0);
        buttonsTask.add(btnAtualizar, 0, 1);

        this.gridTasks.add(taskBox, 0, col);

        col++;
    }

    @Override
    public void start(Stage stage) {
        Bindings.bindBidirectional(this.txtNewTask.textProperty(), this.controller.titleProperty());
        Bindings.bindBidirectional(this.txaDesc.textProperty(), this.controller.descriptionProperty());

        this.lblTotalVlr.textProperty().bind(this.controller.totalProperty().asString());
        this.lblPendenteVlr.textProperty().bind(this.controller.pendenteProperty().asString());
        this.lblConcluidaVlr.textProperty().bind(this.controller.concluidaProperty().asString());

        this.txaDesc.setPrefRowCount(2);

        BorderPane root = new BorderPane();
        Scene scn = new Scene(root, 800, 600);

        GridPane top = new GridPane();
        top.setPadding(new Insets(15));
        top.setHgap(10);
        top.setVgap(10);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);

        top.getColumnConstraints().addAll(new ColumnConstraints(), c1, new ColumnConstraints());

        top.add(new Label("Nova tarefa:"), 0, 0);
        top.add(this.txtNewTask, 1, 0);
        top.add(this.btnAddTask, 2, 0);

        top.add(new Label("Descrição:"), 0, 1);
        top.add(this.txaDesc, 1, 1);

        // botão criar
        this.btnAddTask.setOnAction(e -> {
            Task t = this.controller.saveAndReturn();
            createItem(t);

            this.txtNewTask.clear();
            this.txaDesc.clear();
            newTaskMsg(t);
        });

        // status
        this.gridStatus.setPadding(new Insets(10));
        this.gridStatus.setHgap(10);
        this.gridStatus.setAlignment(Pos.CENTER);

        this.gridStatus.add(new Label("Total:"), 0, 0);
        this.gridStatus.add(lblTotalVlr, 1, 0);
        this.gridStatus.add(new Label("Pendente:"), 2, 0);
        this.gridStatus.add(lblPendenteVlr, 3, 0);
        this.gridStatus.add(new Label("Concluída:"), 4, 0);
        this.gridStatus.add(lblConcluidaVlr, 5, 0);

        this.scrollTasks.setContent(gridTasks);
        this.scrollTasks.setFitToWidth(true);

        root.setTop(top);
        root.setCenter(scrollTasks);
        root.setBottom(gridStatus);

        stage.setScene(scn);
        stage.show();
    }
}