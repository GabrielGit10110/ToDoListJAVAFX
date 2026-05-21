# ToDo List - JavaFX with Repository Pattern and JSON Persistence

This project is a desktop To-Do List application built using JavaFX.  
It allows users to manage tasks dynamically with real-time UI updates using JavaFX Properties and Bindings.

The application follows a layered architecture inspired by BCE (Boundary, Control, Entity) and implements the Repository pattern to support different persistence strategies. Currently, it uses a JSON-based repository to store and load tasks automatically.

---

## Features

- Create tasks with title and optional description
- Edit task descriptions dynamically
- Mark tasks as completed using a checkbox
- Assign a completion date using a DatePicker
- Remove tasks from the list
- Automatic persistence using JSON files
- Load saved tasks on application startup
- Real-time counters:
  - Total tasks
  - Pending tasks
  - Completed tasks
- Reactive updates using JavaFX binding

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
├── repository/
│   ├── TaskRepository.java
│   └── impl/
│       └── JsonTaskRepository.java
└── view/
└── ToDoListUI.java

```

---

## Layer Responsibilities

### Boundary (View)

**Class:** `ToDoListUI`

- Implements the JavaFX interface
- Handles user interaction
- Binds UI components to data (Properties)
- Displays and updates tasks dynamically

---

### Control

**Class:** `TaskController`

- Handles application logic
- Manages task state changes (finish/unfinish)
- Updates counters
- Communicates with the repository

---

### Entity

**Class:** `Task`

- Represents the domain model
- Uses JavaFX `Property` objects:
  - StringProperty
  - BooleanProperty
  - ObjectProperty (LocalDate)
- Supports automatic UI synchronization

---

### Repository

**Interface:** `TaskRepository`  
**Implementation:** `JsonTaskRepository`

- Manages data persistence
- Abstracts storage mechanism from the controller
- Current implementation uses JSON file storage
- Automatically loads tasks on startup and saves on changes

---

## How JSON Persistence Works

- Tasks are serialized to a file named:

```
tasks.json
```

- Location:

```
{user.home}/Documents/Java/ToDoList/tasks.json
```

- The repository:
  - Reads the file on initialization
  - Writes changes whenever a task is added or removed

---

## Technologies Used

- Java 21
- JavaFX
- Gradle
- Jackson (for JSON serialization)

---

## Dependencies

```kotlin
implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.1")
```

***

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/GabrielGit10110/ToDoListJAVAFX
cd ToDoListJAVAFX
```

***

### 2. Build the project

```bash
./gradlew build
```

***

### 3. Run the application

```bash
./gradlew run
```

***

## Generate Executable (JAR)

To generate a runnable JAR:

```bash
./gradlew jar
```

The file will be located at:

```
build/libs/
```

Run it using:

```bash
java -jar your-project-name.jar
```

***

## Architecture Overview

This project implements a layered structure:

```
UI (JavaFX)
   ↓
Controller (Logic)
   ↓
Repository (Persistence)
   ↓
Data (JSON)
```

### Key Concepts Used

* JavaFX Binding (`bindBidirectional`)
* Observable Properties
* Repository Pattern
* Separation of Concerns
* Reactive UI updates

***

## Design Decisions

* The UI interacts only with the controller
* The controller does not know where data is stored
* The repository abstracts persistence details
* The entity uses JavaFX Properties for reactive updates

***

## Possible Improvements

* Replace manual UI rendering with `ListView`
* Add persistent autosave on application exit
* Implement multiple repository types:
  * InMemoryTaskRepository
  * FileTaskRepository (advanced)
  * DatabaseTaskRepository (JDBC/JPA)
* Add filtering (completed vs pending)
* Add UI styling using CSS

***

## License

This project is intended for educational purposes.
