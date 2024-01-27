package za.craiga.todo.todoapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import za.craiga.todo.todoapi.business.TodoService;
import za.craiga.todo.todoapi.model.Todo;

import java.util.List;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    @GetMapping("/all")
    public Iterable<Todo> getAll() {
        return todoService.getAll();
    }

    @PutMapping("/upsert")
    public long upsertTodo(@RequestBody Todo todo) {
        return todoService.upsertTodo(todo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable long id) {
        this.todoService.deleteTodo(id);
    }

    @DeleteMapping("/delete")
    public void deleteTodosById(@RequestParam List<Long> ids) {
        this.todoService.deleteTodosById(ids);
    }

    @PutMapping("/toggle/{id}")
    public void toggleTodo(@PathVariable long id, @RequestBody boolean state) {
        this.todoService.toggleTodo(id, state);
    }
}
