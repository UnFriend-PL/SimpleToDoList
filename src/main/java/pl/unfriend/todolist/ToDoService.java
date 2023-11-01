package pl.unfriend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService implements IToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public List<ToDo> getAllTasks(){
        return toDoRepository.findAll();
    }

    public ToDo getTask(int id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        return toDo.orElse(null);
    }
    public String setDone(int taskId, boolean isDone) {
        Optional<ToDo> taskOptional = toDoRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            ToDo task = taskOptional.get();
            task.setIsdone(isDone);
            toDoRepository.save(task);
            return "Marked as " + isDone;
        }
        return "Cannot find this task or mark as " + isDone ;
    }
}
