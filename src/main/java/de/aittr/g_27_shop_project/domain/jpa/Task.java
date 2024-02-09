package de.aittr.g_27_shop_project.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "description")
  private String description;
  @Column(name = "executed_at")
  private Timestamp executedAt;

  public Task() {
    executedAt = new Timestamp(System.currentTimeMillis());
  }

  public Task(String description) {
    this.description = description;
    executedAt = new Timestamp(System.currentTimeMillis());
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Task task = (Task) o;

    if (id != task.id) {
      return false;
    }
    if (!Objects.equals(description, task.description)) {
      return false;
    }
    return Objects.equals(executedAt, task.executedAt);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (executedAt != null ? executedAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", executedAt=" + executedAt +
        '}';
  }
}
