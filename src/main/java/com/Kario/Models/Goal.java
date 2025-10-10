package com.Kario.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.Kario.Models.Enums.GoalMetric;
import com.Kario.Models.Enums.GoalStatus;
import com.Kario.Models.Enums.GoalType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private GoalType goalType;             
    
    @Enumerated(EnumType.STRING)
    private GoalMetric metric;                
    
    private Integer targetValue;        // Objective value
    private Integer currentValue;       // Actual progress
    
// Time period
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer weekNumber;
    private Integer year;
    
// Status
    @Enumerated(EnumType.STRING)
    private GoalStatus status;  
    
    private boolean isArchived;
    private LocalDateTime archivedAt;

// Relationships
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
