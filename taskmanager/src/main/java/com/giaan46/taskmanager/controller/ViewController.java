package com.giaan46.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giaan46.taskmanager.model.Task;
import com.giaan46.taskmanager.service.TaskService;



@Controller
@RequestMapping("/view")

public class ViewController {

	   @Autowired
	    private TaskService service;

	    @GetMapping
	    public String index(Model model) {
	        model.addAttribute("tasks", service.getAllTasks(null));
	        return "task-list";
	    }

	    @GetMapping("/new")
	    public String newTaskForm(Model model) {
	        model.addAttribute("task", new Task());
	        return "form";
	    }

	    @PostMapping("/save")
	    public String saveTask(@ModelAttribute Task task, Model model) {
	        service.createTask(task);
	        model.addAttribute("message", "Tarea guardada correctamente");
	        model.addAttribute("tasks", service.getAllTasks(null));
	        return "redirect:/view";
	    }

	    @GetMapping("/edit/{id}")
	    public String editTask(@PathVariable Long id, Model model) {
	        Task task = service.getTaskById(id);
	        model.addAttribute("task", task);
	        return "form";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteTask(@PathVariable("id") Long id, Model model) {
	        service.deleteTask(id);
	        model.addAttribute("message", "❌ Tarea eliminada correctamente");
	        model.addAttribute("tasks", service.getAllTasks(null));
	        return "redirect:/view";
	    }


	    
	}