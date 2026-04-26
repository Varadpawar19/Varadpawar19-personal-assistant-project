package com.varad.jarvis.controller;

import com.varad.jarvis.ai.GeminiService;
import com.varad.jarvis.dto.TaskDTO;
import com.varad.jarvis.entity.Task;
import com.varad.jarvis.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;
    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return service.save(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getAll();
    }

    @PostMapping("/smart-add")
    public Task smartAdd(@RequestBody String input) {

        String aiJson = geminiService.extractTask(input);

        try {
            ObjectMapper mapper = new ObjectMapper();
            TaskDTO dto = mapper.readValue(aiJson, TaskDTO.class);

            Task task = new Task();
            task.setTitle(dto.getTitle());
            task.setDueTime(LocalDateTime.parse(dto.getTime()));
            task.setCompleted(false);

            return service.save(task);

        } catch (Exception e) {
            throw new RuntimeException("AI parsing failed: " + aiJson);
        }
    }
}