package pl.unfriend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RestController
public class ToDoController {
    @Autowired
    public IToDoService toDoService;

    @GetMapping("/tasks")
    public ResponseEntity<List<ToDo>> getTasks() {
        var listOfToDo = toDoService.getAllTasksAsync();
        if (listOfToDo != null) {
            return ResponseEntity.ok(listOfToDo.join());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<ToDo> getTask(@PathVariable int id) {
        var toDo = toDoService.getTaskAsync(id);
        if (toDo != null) {
            return ResponseEntity.ok(toDo.join());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody ToDoCreateDTO request) {
        var newToDo = toDoService.addTaskAsync(new ToDo(request.getName(), request.getDeadline()));
        return ResponseEntity.ok(newToDo.join());
    }

    @PatchMapping("/tasks/{id}/setDone")
    public ResponseEntity<String> setDone(@PathVariable int id, @RequestBody boolean isDone) {
        var toDo = toDoService.setDoneAsync(id, isDone);
        if (toDo != null) {
            return ResponseEntity.ok(toDo.join());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/tasks/{id}/delete")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        var toDo = toDoService.deleteTaskAsync(id);
        if (toDo != null) {
            return ResponseEntity.ok(toDo.join());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
