package com.Kario.Models;

import java.time.LocalDateTime;
import java.util.Set;

import com.Kario.Models.Enums.DistractionType;
import com.Kario.Models.Enums.MoodLevel;
import com.Kario.Models.Enums.StudyEnvironment;
import com.Kario.Models.Enums.GoalCompletion;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class SessionEvaluation {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

// Main metrics
    @NotNull
    private Integer ConcentrationLevel;

    @NotNull
    private Integer energyLevel;

    @NotNull
    private boolean goalAchived;

    @Enumerated(EnumType.STRING)
    private GoalCompletion GoalCompletion;

// Distraction metrics
    private Integer distractionCounts;

    @Enumerated(EnumType.STRING)
    private DistractionType distraction;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DistractionType> allDistractions;
    
    @Enumerated(EnumType.STRING)
    private MoodLevel mood;

    private Integer motivationLevel;            // 1-10
    private Integer difficultyLevel;            // 1-10
    
// Qualitative feedback
    private String notes;                       // comments
    private String whatWorkedWell;              
    private String whatToImprove;               
    
// Environment
    @Enumerated(EnumType.STRING)
    private StudyEnvironment environment;
    
    private Boolean musicUsed;                  // usó música?
    private String musicType;                   // "lofi", "classical"
    
// Timing
    private LocalDateTime evaluatedAt;
    private Integer evaluationDelay;            // minutos entre fin y evaluación
    
// Relationships
    @OneToOne
    @JoinColumn(name = "study_session_id", nullable = false)
    private StudySession studySession;


}
