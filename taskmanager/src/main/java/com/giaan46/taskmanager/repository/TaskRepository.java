package com.giaan46.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giaan46.taskmanager.model.Task;


 
public interface TaskRepository extends JpaRepository<Task, Long> {

	  List<Task> findByCompleted(Boolean completed);



}
