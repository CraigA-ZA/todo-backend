package za.craiga.todo.todoapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.craiga.todo.todoapi.model.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
