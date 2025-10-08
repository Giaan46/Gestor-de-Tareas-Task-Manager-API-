package com.giaan46.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giaan46.taskmanager.model.Task;
import com.giaan46.taskmanager.service.TaskService;



@RestController
@RequestMapping("/api/task")

public class TaskController {
	
	private final TaskService service;
	
	
	public TaskController(TaskService service) {
		
		this.service = service;
	}

	
	@GetMapping
	public List<Task> getAll(){
		return service.getAllTasks();
	}
	@PostMapping 
	public Task crate(@RequestBody Task task){
		return service.createTask(task);
		
	}
	
	@PutMapping("/{id}")
	public Task update(@PathVariable Long id, @RequestBody Task task) {
		return service.UpdateTask(id, task);
		
}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	service.deleteTask(id);
	
}
}

