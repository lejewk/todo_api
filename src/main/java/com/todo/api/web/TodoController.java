package com.todo.api.web;

import com.todo.api.domain.Todo;
import com.todo.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> todos() {
        List<Todo> todos = todoRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(todos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Todo> create(@RequestParam(value = "title") String title) {
        Todo todo = new Todo(title);
        todoRepository.save(todo);
        return new ResponseEntity(todo, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        todoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Todo> update(@PathVariable(value = "id") Long id, @RequestBody Todo requestTodo) {
        Optional<Todo> todo = todoRepository.findById(id);

        if (!todo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        todo.ifPresent(it -> {
            it.setTitle(requestTodo.getTitle());
            todoRepository.save(it);
        });

        return ResponseEntity.noContent().build();
    }
}