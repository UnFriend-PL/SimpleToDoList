package pl.unfriend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class ToDoController {
    @Autowired
    public IToDoService toDoService;

    @GetMapping("/tasks")
    public List<ToDo> getTasks(){
        return toDoService.getAllTasks();
    }

    @PatchMapping("/tasks/setDone")
    public String setDone( @RequestParam int id, @RequestParam boolean isDone){
        return toDoService.setDone(id, isDone);
    }

}
