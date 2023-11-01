package pl.unfriend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RestController
public class ToDoController {
    @Autowired
    public IToDoService toDoService;

    @GetMapping("/tasks")
    public ResponseEntity<CompletableFuture<List<ToDo>>> getTasks() {
        CompletableFuture<List<ToDo>> listOfToDo = toDoService.getAllTasksAsync();
        return ResponseEntity.ok(listOfToDo);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<CompletableFuture<ToDo>> getTask(@PathVariable int id) {
        CompletableFuture<ToDo> toDo = toDoService.getTaskAsync(id);
        return ResponseEntity.ok(toDo);
    }

    @PatchMapping("/tasks/{id}/setDone")
    public ResponseEntity<CompletableFuture<String>> setDone(@PathVariable int id, @RequestParam boolean isDone) {
        CompletableFuture<String> toDo = toDoService.setDoneAsync(id, isDone);
        return ResponseEntity.ok(toDo);
    }

    @Async
    @DeleteMapping("/tasks/{id}/delete")
    public ResponseEntity<CompletableFuture<String>> deleteTask(@PathVariable int id) {
        CompletableFuture<String> toDo = toDoService.deleteTaskAsync(id);
        return ResponseEntity.ok(toDo);
    }
}
