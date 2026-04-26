package com.varad.jarvis.repository;

import com.varad.jarvis.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.completed = false AND t.dueTime BETWEEN :now AND :next15Min")
    List<Task> findUpcomingTasks(LocalDateTime now, LocalDateTime next15Min);
}
