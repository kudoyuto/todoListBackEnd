package com.example.todolist.controller;

import com.example.todolist.model.todo;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public todo addTodoItem(@RequestBody todo addItem){
        return todoService.addTodoItem(addItem);

    }
    @PutMapping(path = "/{id}")
    public todo updateTodoItem(@PathVariable Integer id, @RequestBody todo updateItem){
        return todoService.updateTodoItem(id, updateItem);

    }
    @DeleteMapping(path = "/{id}")
    public void deleteTodoItem(@PathVariable Integer id) {
        todoService.deleteTodoItem(id);

    }


}
