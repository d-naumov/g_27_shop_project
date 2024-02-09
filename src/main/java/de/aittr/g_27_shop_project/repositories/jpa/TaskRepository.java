package de.aittr.g_27_shop_project.repositories.jpa;

import de.aittr.g_27_shop_project.domain.jpa.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Integer> {

  @Query("SELECT t FROM Task t WHERE t.executedAt IS NOT NULL ORDER BY t.executedAt DESC")
  List<Task> findLastCompletedTasks(@Param("limit") int limit);



}
