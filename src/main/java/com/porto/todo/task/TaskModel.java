package com.porto.todo.task;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime finishAt;
    private LocalDateTime startAt;
    private Priority priority;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUser;

    public void setTitle(String title) throws Exception {
        if (title.length() >= 50) {
            throw new Exception("O Campo do titulo deve conter no maximo 50 caracteres");
        }
        this.title = title;
    }
}
