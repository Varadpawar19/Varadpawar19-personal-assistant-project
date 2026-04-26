package com.varad.jarvis.scheduler;

import com.varad.jarvis.entity.Task;
import com.varad.jarvis.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class ReminderScheduler {

    @Autowired
    private TaskRepository repo;

    private Set<Long> notifiedTasks = new HashSet<>();

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next15Min = now.plusMinutes(15);

        List<Task> tasks = repo.findUpcomingTasks(now, next15Min);

        for (Task task : tasks) {

            if (!notifiedTasks.contains(task.getId())) {

                System.out.println("🔔 Reminder: " + task.getTitle() +
                        " at " + task.getDueTime());

                notifiedTasks.add(task.getId());
            }
        }
    }
}