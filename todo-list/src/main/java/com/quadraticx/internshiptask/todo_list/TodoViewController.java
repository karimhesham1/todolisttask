package com.quadraticx.internshiptask.todo_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoViewController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public String viewTodos(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todo";
    }

    @PostMapping("/todos")
    public String addTodo(@RequestParam String title, @RequestParam String description) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);  
        todo.setCompleted(false);
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/complete")
    public String completeTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            todo.setCompleted(true);
            todoService.saveTodo(todo);
        }
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}


