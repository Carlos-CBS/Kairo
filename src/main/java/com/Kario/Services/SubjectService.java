package com.Kario.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.Kario.DTOs.Subject.create.CreateRequest;
import com.Kario.DTOs.Subject.create.SubjectResponse;
import com.Kario.DTOs.Subject.update.UpdateRequest;
import com.Kario.DTOs.Subject.update.UpdateResponse;
import com.Kario.Exceptions.ResourceAlreadyExistsException;
import com.Kario.Exceptions.ResourceNotFoundException;
import com.Kario.Models.Subject;
import com.Kario.Repositories.SubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {
    
    private final SubjectRepository subjectRepo;
    private final UserContext userContext;

 // Retrieve method to Active && Archived subjects --> User

    @PreAuthorize("#request.id == @userContext.getCurrentUserId()")
    public List<SubjectResponse> getActiveSubjectForUser() {
        return subjectRepo.findAllByUser_IdAndActive(userContext.getCurrentUserId(), true);

        // return subList.stream()
        // .map(sub -> SubjectResponse.builder()
        // .id(sub.getId())
        // .name(sub.getName())
        // .color(sub.getColor())
        // .createAt(sub.getCreateAt())
        // .build()
        // ).toList();
    }

    @PreAuthorize("#request.id == @userContext.getCurrentUserId()")
    public List<SubjectResponse> getArchivedSubjectsForUser() {
        return subjectRepo.findAllByUser_IdAndActive(userContext.getCurrentUserId(), false);
    }

 // Retrieve method to Active && Archived subjects --> Admin

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SubjectResponse> getActiveSubjectForUser(Long userId) {
        return subjectRepo.findAllByUser_IdAndActive(userId, true);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SubjectResponse> getArchivedSubjectsForUser(Long userid) {
        return subjectRepo.findAllByUser_IdAndActive(userid, false);
    }    
    
    public SubjectResponse createSubject(CreateRequest request) {

        var subject = Subject.builder()
        .name(request.getName())
        .color(request.getColor())
        .user(userContext.getCurrentUser())
        .build();

        try { subjectRepo.save(subject); }
        catch (DataIntegrityViolationException e) { throw new ResourceAlreadyExistsException("Subject name already exist"); }

    // Return DTO Response    
        return SubjectResponse.builder()
        .id(subject.getId())
        .name(subject.getName())
        .color(subject.getColor())
        .createAt(subject.getCreateAt())
        .build();
    }
 
 // Protect delete method
    @PreAuthorize("#id == @userContext.getCurrentUserId() or hasAuthority('ADMIN')")
    public void deleteSubject(Integer id) {
        
        if (!subjectRepo.existsById(id)) throw new ResourceNotFoundException("Subject not found with provided id");
        subjectRepo.deleteById(id);
    }

    public UpdateResponse updateSubject(Integer id, UpdateRequest request) {
                
        Subject sub = subjectRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subject not found with provided id"));
        
    // Update fields if sent
        sub.setName(request.getName());
        sub.setColor(request.getColor());
        sub.setActive(request.getActive());

        try { subjectRepo.save(sub); }
        catch (DataIntegrityViolationException e) { throw new ResourceAlreadyExistsException("Subject name already exist"); }


        return UpdateResponse.builder()
        .id(id)
        .name(request.getName())
        .color(request.getColor())
        .active(request.getActive())
        .build();
    }
}
