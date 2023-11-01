package pl.unfriend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.unfriend.todolist.ToDo;
import pl.unfriend.todolist.ToDoRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ToDoService implements IToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    @Async
    public CompletableFuture<List<ToDo>> getAllTasksAsync() {
        List<ToDo> tasks = toDoRepository.findAll();
        return CompletableFuture.completedFuture(tasks);
    }

    @Async
    public CompletableFuture<ToDo> getTaskAsync(int id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        return CompletableFuture.completedFuture(toDo.orElse(null));
    }

    @Async
    public CompletableFuture<String> setDoneAsync(int taskId, boolean isDone) {
        Optional<ToDo> taskOptional = toDoRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            ToDo task = taskOptional.get();
            task.setIsdone(isDone);
            toDoRepository.save(task);
            return CompletableFuture.completedFuture("Marked as " + isDone);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<String> deleteTaskAsync(int id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDoRepository.delete(toDo.get());
            return CompletableFuture.completedFuture("Task deleted successfully");
        }
        return CompletableFuture.completedFuture(null);
    }
}
