package de.aittr.g_27_shop_project.repositories.jpa;

import de.aittr.g_27_shop_project.domain.jpa.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Integer> {

//  @Query("value = \"select * from task order by id desc limit :limit\", nativeQuery = true")
//  List<Task> findLastCompletedTasks(@Param("limit") int limit);

  @Query(value = "SELECT * FROM task ORDER BY id DESC LIMIT :limit", nativeQuery = true)
  List<Task> findLastCompletedTasks(@Param("limit") int limit);


}
