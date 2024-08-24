package com.lcwd.todo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lcwd.todo.models.Todo;

//@Component
@Service
public class TodoServiceImpl implements com.lcwd.todo.services.TodoService {

	Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	List<Todo> todos = new ArrayList<>();

	// create todo method
	public Todo createTodo(Todo todo) {
		// change the logic

		todos.add(todo);
		logger.info("Todos {}", this.todos);
		return todo;
	}

	public List<Todo> getAllTodos() {
		// TODO Auto-generated method stub
		return todos;
	}

	public Todo getTodo(int todoId) {
		// TODO Auto-generated method stub
		Todo todo = todos.stream().filter(t -> todoId == t.getId()).findAny().get();
		logger.info("TODO : {}", todo);
		return todo;
	}

	public Todo updateTodo(int todoId, Todo todo) {
		// TODO Auto-generated method stub
		List<Todo> newUpdatedList = todos.stream().map(t -> {
			if (t.getId() == todoId) {
				// perfrom update
				t.setTitle(todo.getTitle());
				t.setContent(todo.getContent());
				t.setStatus(todo.getStatus());
				return t;
			} else {
				return t;
			}
		}).collect(Collectors.toList());
		todos = newUpdatedList;
		todo.setId(todoId);
		return todo;
	}

	public void deleteTodo(int todoId) {
		// TODO Auto-generated method stub
		logger.info("Deleting todo");

		List<Todo> newList = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
		todos=newList;
		
	}
}
