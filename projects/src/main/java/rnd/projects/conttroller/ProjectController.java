package rnd.projects.conttroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import rnd.projects.model.Project;
import rnd.projects.repository.ProjectRepository;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api")
@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Async
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Async
    @PostMapping("/project")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @Async
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
        Project project = projectRepository.findOne(projectId);
        if(project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(project);
    }

    @Async
    @PutMapping("/project/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
                                           @Valid @RequestBody Project projectDetails) {
        Project project = projectRepository.findOne(projectId);
        if(project == null) {
            return ResponseEntity.notFound().build();
        }
        project.setProject_name(projectDetails.getProject_name());

        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @Async
    @DeleteMapping("/project/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") Long projectId) {
        Project project = projectRepository.findOne(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        projectRepository.delete(project);
        return ResponseEntity.ok().build();
    }
}
