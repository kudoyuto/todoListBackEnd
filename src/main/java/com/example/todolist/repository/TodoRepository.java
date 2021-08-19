package com.example.todolist.repository;

import com.example.todolist.model.todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<todo,Integer> {
}
