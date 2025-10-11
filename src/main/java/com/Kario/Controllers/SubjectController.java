package com.Kario.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.Kario.DTOs.Subject.create.CreateRequest;
import com.Kario.DTOs.Subject.create.SubjectResponse;
import com.Kario.DTOs.Subject.update.UpdateRequest;
import com.Kario.DTOs.Subject.update.UpdateResponse;
import com.Kario.Models.Subject;
import com.Kario.Services.SubjectService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    
    private final SubjectService subjectService;

    @GetMapping("/actives")
    public ResponseEntity<List<SubjectResponse>> getAllActives() {
        return ResponseEntity.ok(subjectService.getActiveSubjectForUser());
    }

    @GetMapping("/archived")
    public ResponseEntity<List<SubjectResponse>> getAllArchived() {
        return ResponseEntity.ok(subjectService.getArchivedSubjectsForUser());
    }
    
    @PostMapping("/create")
    public ResponseEntity<SubjectResponse> create(@RequestBody CreateRequest request) {        
        return ResponseEntity.ok(subjectService.createSubject(request));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UpdateResponse> update(@PathVariable Integer id, @RequestBody UpdateRequest request) {        
        return ResponseEntity.ok(subjectService.updateSubject(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok().build();
    }
    
}
