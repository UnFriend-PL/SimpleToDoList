package pl.unfriend.todolist;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IToDoService {
    public CompletableFuture<List<ToDo>> getAllTasksAsync();
    public CompletableFuture<ToDo> getTaskAsync(int id);
    public CompletableFuture<String> setDoneAsync(int taskId, boolean isDone);
    public CompletableFuture<String> deleteTaskAsync(int id);
    public CompletableFuture<String> addTaskAsync(ToDo toDo);

}
