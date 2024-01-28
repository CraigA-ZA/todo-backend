package za.craiga.todo.todoapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.craiga.todo.todoapi.model.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Modifying
    @Query("update Todo t set t.archived = true where t.id in :ids")
    void setAllAsArchivedByIds(List<Long> ids);
}
