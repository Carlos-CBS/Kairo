package com.Kario.Services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.Kario.DTOs.Subject.deleteRequest;
import com.Kario.DTOs.Subject.create.CreateRequest;
import com.Kario.DTOs.Subject.create.CreateResponse;
import com.Kario.Models.Subject;
import com.Kario.Repositories.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {
    
    private final SubjectRepository subjectRepo;
    private final UserContext userContext;

    @PreAuthorize("#request.id == @userContext.getCurrentUserId()")
    public List<Subject> getActiveSubjectForUser() {
        return subjectRepo.findAllByUser_IdAndActive(userContext.getCurrentUserId(), true);
    }

    @PreAuthorize("#request.id == @userContext.getCurrentUserId()")
    public List<Subject> getArchivedSubjectsForUser() {
        return subjectRepo.findAllByUser_IdAndActive(userContext.getCurrentUserId(), false);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Subject> getActiveSubjectForUser(Long userId) {
        return subjectRepo.findAllByUser_IdAndActive(userId, true);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Subject> getArchivedSubjectsForUser(Long userid) {
        return subjectRepo.findAllByUser_IdAndActive(userid, false);
    }    
    
    public CreateResponse createSubject(CreateRequest request) {

        if (subjectRepo.existsByUser_IdAndName(userContext.getCurrentUserId(), request.getName())) {
            throw new IllegalArgumentException("Subject name already exist for the current user");
        }

        var subject = Subject.builder()
        .name(request.getName())
        .color(request.getColor())
        .build();

        subjectRepo.save(subject);

        return CreateResponse.builder()
        .id(subject.getId())
        .name(subject.getName())
        .color(subject.getColor())
        .createAt(subject.getCreateAt())
        .build();
    }

    @PreAuthorize("#request.id == @userContext.getCurrentUserId() or hasAuthority('ADMIN')")
    public void deleteSubject(deleteRequest request) {
        subjectRepo.deleteById(request.getId());
    }
}
