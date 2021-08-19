package com.example.todolist.IntegretationTest;

import com.example.todolist.model.todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ListToDoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;


    @BeforeEach
    public void before() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todo_list_when_call_get_all_todo_list_api() throws Exception {
        //should
        todo todoItem1 = new todo("Do homework");
        todo todoItem2 = new todo("Research about React, Redux");
        todoRepository.save(todoItem1);
        todoRepository.save(todoItem2);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("Do homework"))
                .andExpect(jsonPath("$[1].text").value("Research about React, Redux"));

    }
    @Test
    void should_add_todo_list_when_call_add_todo_item_api() throws Exception {
        //should
        String todo = "" +
                "" +
                "{\n" +
                "\n" +
                "        \"text\": \"Skill to the moon\"\n" +
                "}" +
                "";
        //when&then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Skill to the moon"));
    }
    @Test
    void should_update_todo_list_when_call_update_todo_item_api() throws Exception {
        //given
        final todo todoItem = new todo("Skill to the moon");
        final todo savedTodo = todoRepository.save(todoItem);
        String todoListInfo = "{\n" +
                "\n" +
                "        \"text\": \"zoon to the moon\",\n" +
                "        \"done\": true\n" +
                "}";
        //when
        Integer savedId = savedTodo.getId();
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}",savedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoListInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("zoon to the moon"))
                .andExpect(jsonPath("$.done").value(true));

    }
    @Test
    void should_delete_todo_list_when_call_delete_todo_item_api() throws Exception {
        //given
        todo savedTodo=todoRepository.save(new todo("Skill to the moon"));
        //when&then
        Integer savedId = savedTodo.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}",savedId))
                .andExpect(status().isOk());

    }
}
