package de.aittr.g_27_shop_project.services.jpa;

import de.aittr.g_27_shop_project.domain.jpa.Task;
import de.aittr.g_27_shop_project.repositories.jpa.TaskRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  private TaskRepository repository;
  private Logger logger = LoggerFactory.getLogger(TaskService.class);

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

//  public void createTask(String description){
//    Task task = new Task(description);
//    logger.info(description);
//    repository.save(task);
//  }
  public void createTask(String description){
    try {
      Task task = new Task(description);
      logger.info(description);
      repository.save(task);
    } catch (Exception e) {
      logger.error("Failed to create task", e);
      // обработка ошибок
    }
  }

  public List<Task> getLastCompletedTasks(int count) {
    return repository.findLastCompletedTasks(count);
  }
}
