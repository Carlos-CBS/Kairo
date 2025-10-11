package com.Kario.Models.Enums;

import lombok.Getter;

@Getter
public enum EvaluationTiming {
    IMMEDIATE(1.0), // 0-15 Minutes
    PROMPT(0.85),   // 15-60
    DELAYED(0.60),  // 60-240   -> 1-4 Horas
    SAME_DAY(0.4),  // 240-1440 -> 4-24 Horas
    VERY_DELAYED(0.2);
    
    private final double liabilityScore;

    EvaluationTiming(double liabilityScore) {
        this.liabilityScore = liabilityScore;
    }

    public static EvaluationTiming fromDelayMinutes(Long delayMinutes) {
        if (delayMinutes <= 15) return IMMEDIATE;
        if (delayMinutes <= 60) return PROMPT;
        if (delayMinutes <= 240) return DELAYED;
        if (delayMinutes <= 1440) return SAME_DAY;
        return VERY_DELAYED;
    }
    
}
