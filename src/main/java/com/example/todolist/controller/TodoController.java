package com.example.todolist.controller;

import com.example.todolist.model.todo;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<todo> getAllTodoLists(){
        return todoService.getAllTodoLists();
    }
}
