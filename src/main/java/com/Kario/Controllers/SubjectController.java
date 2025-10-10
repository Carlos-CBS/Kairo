package com.Kario.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.Kario.DTOs.Subject.create.CreateRequest;
import com.Kario.DTOs.Subject.create.CreateResponse;
import com.Kario.Models.Subject;
import com.Kario.Services.SubjectService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    
    private final SubjectService subjectService;

    @GetMapping("/actives")
    public ResponseEntity<List<Subject>> getAllActives() {
        return ResponseEntity.ok(subjectService.getActiveSubjectForUser());
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Subject>> getAllArchived() {
        return ResponseEntity.ok(subjectService.getArchivedSubjectsForUser());
    }
    
    @PostMapping("/create")
    public ResponseEntity<CreateResponse> create(@RequestBody CreateRequest request) {        
        return ResponseEntity.ok(subjectService.createSubject(request));
    }
    
}
