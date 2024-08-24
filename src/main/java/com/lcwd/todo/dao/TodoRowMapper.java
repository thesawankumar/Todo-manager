package com.lcwd.todo.dao;

import com.lcwd.todo.helper.Helper;
import com.lcwd.todo.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId((int) rs.getInt("id"));
        todo.setTitle((String) rs.getString("title"));
        todo.setContent((String) rs.getString("content"));
        todo.setStatus((String) rs.getString("status"));
        try {
            todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
            todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return todo;
    }
}
