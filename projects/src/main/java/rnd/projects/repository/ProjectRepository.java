package rnd.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.projects.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
