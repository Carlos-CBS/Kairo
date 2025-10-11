package com.Kario.Models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.Kario.Models.Enums.SessionStatus;
import com.Kario.Models.Enums.SessionType;
import com.Kario.Models.Enums.StudyMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class StudySession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goal;
    private Integer plannedDuration;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @Enumerated(EnumType.STRING)
    private StudyMethod studyMethod;

    @NotNull
    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private Integer actualDuration;

// Pomodoro specific
    private Integer workBlocks;                 // Planned blocks
    private Integer completedBlocks;           
    private Integer breaksTaken;               
    private Integer breaksSkipped;            
    
// Distractions
    private Integer pauseCount;            
    private Integer pauseTotalTime;    
    private Integer distractionCount;
    
// Status
    @Enumerated(EnumType.STRING)
    private SessionStatus status;               
    
    private Boolean wasCompleted;              
    
// Effectiveness
    private Double productivityRatio;           // productivity time / total time
    private Boolean methodFollowed;            
    private Integer methodDeviations;              

// Task integration
    private Boolean taskCompleted;             
    private Integer taskProgress;           
    
// Pre-calculated for analytics (performance)
    private Integer hour;                       // 9, 10, 11...
    private DayOfWeek dayOfWeek;              
    private Integer weekOfYear;                 // 1-52
    private Integer monthOfYear;              
    private Integer year;                      
    
// Relationships
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @ManyToOne
    @JoinColumn(name = "related_task_id")
    private Task relatedTask;
    
    @OneToOne(mappedBy = "studySession", cascade = CascadeType.ALL)
    private SessionEvaluation evaluation;
}
