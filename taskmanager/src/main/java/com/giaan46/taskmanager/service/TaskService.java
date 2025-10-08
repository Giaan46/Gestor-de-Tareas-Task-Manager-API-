package com.giaan46.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giaan46.taskmanager.model.Task;
import com.giaan46.taskmanager.repository.TaskRepository;

@Service

public class TaskService {

	
	private final TaskRepository repository;
	
	public TaskService(TaskRepository repository) {
		this.repository = repository;
		
	}
	
	public List<Task> getAllTasks(){
		return repository.findAll();
		                
	}
	public Task createTask(Task task){
		return repository.save(task);
	
	}
	

	public Task UpdateTask(Long id, Task updatedTask) {
		return repository.findById(id)
				.map(task-> {
					task.setTitle(updatedTask.getTitle());
					task.setDescription(updatedTask.getDescription());
					task.setCompleted(updatedTask.isCompleted());
					return repository.save(task);
					
				})
				.orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
		
	}
	public void deleteTask(Long id) {
		repository.deleteById(id);
		
	}
	
	
}
