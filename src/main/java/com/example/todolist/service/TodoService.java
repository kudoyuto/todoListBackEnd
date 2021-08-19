package com.example.todolist.service;

import com.example.todolist.model.todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }



    public List<todo> getAllTodoLists() {return todoRepository.findAll();}


}
