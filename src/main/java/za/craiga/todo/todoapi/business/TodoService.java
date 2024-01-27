package za.craiga.todo.todoapi.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.craiga.todo.todoapi.model.Todo;
import za.craiga.todo.todoapi.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TodoService {
    private TodoRepository todoRepository;

    public Iterable<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public long upsertTodo(Todo todo) {
        return Optional.ofNullable(todo.getId())
                .map(id -> {
                    Todo todoToUpdate = todoRepository.findById(id).orElseThrow();
                    updateTodo(todoToUpdate, todo);
                    return todo.getId();
                })
                .orElseGet(() -> todoRepository.save(todo).getId());
    }

    private void updateTodo(Todo oldState, Todo newState) {
        oldState.setTitle(newState.getTitle());
        oldState.setDescription(newState.getDescription());
    }

    public void deleteTodo(long id) {
        this.todoRepository.deleteById(id);
    }

    public void toggleTodo(long id, boolean state) {
        todoRepository.findById(id).ifPresent(todo -> todo.setCompleted(state));
    }

    public void deleteTodosById(List<Long> ids) {
        todoRepository.deleteAllById(ids);
    }
}
