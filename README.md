# ToDo List - JavaFX

This project is a simple desktop To-Do List application built using JavaFX.  
It allows users to create tasks, edit descriptions, mark tasks as completed, and track their status in real time.

The application follows a simplified BCE (Boundary, Control, Entity) architecture and uses JavaFX Properties and Bindings to keep the UI synchronized with the data model.

---

## Features

- Create new tasks with title and optional description
- Edit task descriptions dynamically
- Mark tasks as completed using a checkbox
- Select a completion date using a DatePicker
- Remove tasks from the list
- Real-time counters:
  - Total tasks
  - Pending tasks
  - Completed tasks
- Automatic UI updates using JavaFX binding

---

## Project Structure

```

src/
└── main/
└── java/
└── edu/
└── curso/
├── App.java
├── controller/
│   └── TaskController.java
├── model/
│   └── Task.java
└── view/
└── ToDoListUI.java

```

### Description

- **App.java**
  - Entry point of the application

- **model/Task**
  - Represents a task entity
  - Uses JavaFX `Property` classes for reactive updates

- **controller/TaskController**
  - Handles application logic
  - Manages task list and counters
  - Provides properties for binding with the UI

- **view/ToDoListUI**
  - JavaFX UI layer
  - Handles user interaction
  - Uses binding to keep UI and data synchronized

---

## Technologies Used

- Java 21
- JavaFX
- Gradle

---

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
````

***

### 2. Build the project
T
```bash
./gradlew build
```

***

### 3. Run the application

```bash
./gradlew run
```

***

## How to Generate an Executable (JAR)

If you want a runnable JAR file:

```bash
./gradlew jar
```

The JAR will be located in:

```
build/libs/
```

You can execute it with:

```bash
java -jar your-project-name.jar
```

***

## Notes About JavaFX

JavaFX is not included in modern JDKs, so you must ensure it is properly configured in your Gradle build.

This project uses the `org.openjfx` plugin to automatically manage JavaFX dependencies.

***

## Architecture

This project loosely follows the **BCE pattern**:

* **Boundary (View)** → `ToDoListUI`
* **Control** → `TaskController`
* **Entity** → `Task`

Additionally, it uses JavaFX's reactive model:

* `Property`
* `Binding`
* `ObservableList`

This allows automatic UI updates without manual synchronization.

***

## Possible Improvements

* Replace manual UI rendering with `ListView`
* Add persistent storage (file or database)
* Implement filtering (completed vs pending)
* Improve styling using CSS

***

## License

This project is for educational purposes.
