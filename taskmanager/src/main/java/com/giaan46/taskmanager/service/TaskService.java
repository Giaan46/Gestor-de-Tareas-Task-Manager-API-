package com.giaan46.taskmanager.service;


import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giaan46.taskmanager.model.Task;
import com.giaan46.taskmanager.repository.TaskRepository;





@Service
public class TaskService {

	private final TaskRepository repository;

	public TaskService(TaskRepository repository) {
		this.repository = repository;
	}

	public List<Task> getAllTasks(Boolean completed) {
		if (completed == null) {
			return repository.findAll();
		} else {
			return repository.findByCompleted(completed);
		}
	}

	public Task createTask(Task task) {
		return repository.save(task);
	}

	public Task updateTask(Long id, Task updatedTask) {
		return repository.findById(id).map(task -> {
			task.setTitle(updatedTask.getTitle());
			task.setDescription(updatedTask.getDescription());
			task.setCompleted(updatedTask.isCompleted());
			task.setDueDate(updatedTask.getDueDate());
			return repository.save(task);
		}).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
	}

	public void deleteTask(Long id) {
		repository.deleteById(id);
	}
	public Task getTaskById(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
	}
	public Page<Task> getAllTasksPaged(Boolean completed, Pageable pageable) {
	    if (completed == null) {
	        return repository.findAll(pageable);
	    } else {
	        return repository.findByCompleted(completed, pageable);
	    }
	}

}
