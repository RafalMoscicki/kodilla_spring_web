package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(123, "title", "content");
        Task task = new Task(123, "title", "content");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(result.getId(), task.getId());
        assertEquals(result.getTitle(), task.getTitle());
        assertEquals(result.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        TaskDto taskDto = new TaskDto(123, "title", "content");
        Task task = new Task(123, "title", "content");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(result.getId(), task.getId());
        assertEquals(result.getTitle(), task.getTitle());
        assertEquals(result.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList = Arrays.asList(
                new Task(123, "title1", "content1"),
                new Task(1234, "title2", "content2")
        );

        //When
        Collection<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assertions.assertThat(result)
                .extracting(TaskDto::getId, TaskDto::getTitle, TaskDto::getContent)
                .containsExactly(
                        Tuple.tuple(123L, "title1", "content1"),
                        Tuple.tuple(1234L, "title2", "content2")
                );
    }
}