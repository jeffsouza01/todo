package com.porto.todo.task;

import com.porto.todo.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;


    @PostMapping("/new")
    public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request){


        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);


        var currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getFinishAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A Data de inicio e/ou termino deve ser maior do que a data atual");
        }

        if (task.getStartAt().isAfter(task.getFinishAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor que a data de termino");
        }

        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);

    }


    @GetMapping
    public ResponseEntity<List<TaskModel>> listTask(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");

        var tasks = taskRepository.findByIdUser((UUID) idUser);

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskUpdate, HttpServletRequest request,@PathVariable UUID id) {

        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa não encontrada!");
        }

        var idUser = request.getAttribute("idUser");

        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar a terefa!");
        }

        Utils.copyNonNullProperties(taskUpdate, task);

        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
