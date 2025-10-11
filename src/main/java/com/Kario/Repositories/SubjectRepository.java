package com.Kario.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Kario.DTOs.Subject.create.SubjectResponse;
import com.Kario.Models.Subject;
import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT new com.Kario.DTOs.Subject.create.SubjectResponse(s.id, s.name, s.color, s.createAt) " +
    "FROM Subject s WHERE s.user.id = :userId AND s.active = :isActive")
    List<SubjectResponse> findAllByUser_IdAndActive(@Param("userId") Long id, @Param("isActive") boolean isActive);
}
