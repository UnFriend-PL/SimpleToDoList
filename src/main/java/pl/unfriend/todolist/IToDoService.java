package pl.unfriend.todolist;

import java.util.List;

public interface IToDoService {
    public String setDone(int taskId, boolean isDone);
    public ToDo getTask(int id);
    public List<ToDo> getAllTasks();

}
