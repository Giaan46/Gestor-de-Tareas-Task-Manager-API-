package com.giaan46.taskmanager.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giaan46.taskmanager.model.Task;
import com.giaan46.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")

public class TaskController {

	private final TaskService service;

	public TaskController(TaskService service) {

		this.service = service;
	}

	@GetMapping
	public List<Task> getAll(@RequestParam(required = false) Boolean completed) {

		return service.getAllTasks(completed);
	}

	@PostMapping
	public Task crate(@Valid @RequestBody Task task) {
		return service.createTask(task);

	}

	@PutMapping("/{id}")
	public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
		return service.updateTask(id, task);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteTask(id);

	}

	@PutMapping("/{id}/complete")
	public Task markAsCompleted(@PathVariable Long id) {
		Task task = service.getTaskById(id);
		task.setCompleted(true);
		return service.updateTask(id, task);

	}


	@PutMapping("/{id}/status")
	public Task updateTaskStatus(
		@PathVariable Long id,
		@RequestParam Boolean completed){
			Task task = service.getTaskById(id);
			task.setCompleted(completed);
			return service.updateTask(id, task);
			
					
		
	}
	@GetMapping("/paged")
	public Page<Task> getAllTaskPaged(
			@RequestParam(required = false) Boolean completed,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id,asc") String[] sort){
		String sortField = sort[0];
		 Sort.Direction sortDirection = sort.length > 1 && sort[1].equalsIgnoreCase("desc")
		            ? Sort.Direction.DESC
		            : Sort.Direction.ASC;
	Pageable pageable = PageRequest.of(page, size,sort.by(sortDirection, sortField));
	
	return service.getAllTaskPaged(completed, pageable);
	
		
	}

}


