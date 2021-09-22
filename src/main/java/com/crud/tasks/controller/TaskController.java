package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/task")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping("{taskId}")
    public TaskDto getTask(@PathVariable long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.findTaskById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId)));
    }

    @DeleteMapping("{taskId}")
    public void deleteTask(@PathVariable long taskId) throws TaskNotFoundException {
        service.deleteById(taskId);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto) throws TaskNotFoundException {
        if (service.findTaskById(taskDto.getId()).isPresent()) {
            Task task = taskMapper.mapToTask(taskDto);
            Task saveTask = service.saveTask(task);
            return taskMapper.mapToTaskDto(saveTask);
        } else {
            throw new TaskNotFoundException(taskDto.getId());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }
}
