package com.Kario.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.Kario.Models.Enums.StudyMethod;
import com.Kario.Models.Enums.TaskPriority;
import com.Kario.Models.Enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty @NotNull
    private String title;

    private String description;    

    private TaskPriority priority;
    private TaskStatus status;


    private Integer estimatedMinutes;
    private Integer actualMinutes;

    @Enumerated(EnumType.STRING)
    private StudyMethod recommendedMethod;


    @NotEmpty
    private LocalDateTime dueTime;

    @NotEmpty
    private LocalDateTime completedAt;

    private Integer progressPercentage;

    // Relationships 
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "relatedTask")
    private List<StudySession> studySessions;
}
