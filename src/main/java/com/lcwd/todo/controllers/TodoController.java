package com.lcwd.todo.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.impl.TodoServiceImpl;

@RestController
@RequestMapping("/todos")
public class TodoController {

	Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoService todoService;

	Random random = new Random();

	// create todo

	@PostMapping
	public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
		// create todo
		
//		String str=null;
//		logger.info("{}",str.length());
		int id = random.nextInt(999999);
		todo.setId(id);
		Date currentDate=new Date();
		logger.info("Current Date: {}", currentDate);
		logger.info("todoDate {}",todo.getToDoDate());
		todo.setAddedDate(currentDate);
		logger.info("Create Todo");
		Todo todo1 = todoService.createTodo(todo);
		return new ResponseEntity<>(todo1, HttpStatus.CREATED);
	}

	// get all todo method
	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodoHandler() {
		List<Todo> allTodos = todoService.getAllTodos();
		return new ResponseEntity<>(allTodos, HttpStatus.OK);
	}

	// get single todos
	@GetMapping("/{todoId}")
	public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
		Todo todo = todoService.getTodo(todoId);
		return ResponseEntity.ok(todo);
	}
	
	//update todo
	@PutMapping("/{todoId}")
	public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId)
	{
		Todo todo =todoService.updateTodo(todoId,todoWithNewDetails);
		return ResponseEntity.ok(todo);
	}
	
	//delete todo
	@DeleteMapping("/{todoId}")
	public ResponseEntity<String> deleteTodo(@PathVariable int todoId)
	{
	    todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo Delete Successfully");
		
	}
//	@ExceptionHandler(NullPointerException.class)
//	public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex) {
//		System.out.println(ex.getMessage());
//		System.out.println("Null Pointer exception generated");
//		return new ResponseEntity<>("Null pointer exception generated " +ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	@ExceptionHandler(NumberFormatException.class)
//	public ResponseEntity<String> numberPointerExceptionHandler(NumberFormatException ex) {
//		System.out.println(ex.getMessage());
//		System.out.println("Number Pointer exception generated");
//		return new ResponseEntity<>("Null pointer exception generated " +ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
