package com.todo.api.web;

import com.todo.api.domain.Todo;
import com.todo.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Todo> create(@RequestParam(value = "todoTitle") String todoTitle) {
        Todo todo = new Todo(todoTitle);
        todoRepository.save(todo);
        return new ResponseEntity(todo, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        todoRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Todo> update(@PathVariable(value = "id") Long id, @RequestBody Todo requestTodo) {
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo == null) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }

        todo.setTodo(requestTodo.getTodo());
        todoRepository.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }
}
