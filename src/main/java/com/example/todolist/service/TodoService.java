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


    public todo addTodoItem(todo addTodo) {
        return todoRepository.save(addTodo);
    }

    public todo updateTodoItem(Integer id, todo updateItem) {
       todo currentTodo = todoRepository.getById(id);
       return todoRepository.save(updateTodoItemInfo(currentTodo,updateItem));
    }

    private todo updateTodoItemInfo(todo currentTodo, todo itemToBeUpdated) {
        if (itemToBeUpdated.getText() != null) {
            currentTodo.setText(itemToBeUpdated.getText());
        }
        if (itemToBeUpdated.getDone() != null) {
            currentTodo.setDone(itemToBeUpdated.getDone());
        }
        return currentTodo;
    }
}
