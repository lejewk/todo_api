package com.todo.api.repository;

import com.todo.api.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
