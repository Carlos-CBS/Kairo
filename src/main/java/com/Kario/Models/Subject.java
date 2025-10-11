package com.Kario.Models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"})
)
public class Subject {
    
    // TODO -> SubCategories

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty @NotNull
    private String name;

    @NotNull
    private String color; // #10B981 (hex)

    @NotNull @Builder.Default
    private boolean active = true; // archived

    @NotNull
    private LocalDateTime createAt;

    @PrePersist
    public void PrePersist() {
        this.createAt = LocalDateTime.now();
    }

// * Analystics calc

    @Builder.Default
    private Double averageConcentration = 0.0;
    
    @Builder.Default
    private Integer totalHoursStudied = 0;

    @Builder.Default
    private Integer totalSessions = 0;

// Relationships

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "subject")
    private List<StudySession> studySessions;

    @OneToMany(mappedBy = "subject")
    private List<Task> tasks;

    @OneToMany(mappedBy = "subject")
    private List<Goal> goals;
}
