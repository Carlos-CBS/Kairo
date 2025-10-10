package com.Kario.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kario.Models.Subject;
import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByUser_IdAndActive(Long userId, boolean isActive);
    boolean existsByUser_IdAndName(Long userId, String name);
}
