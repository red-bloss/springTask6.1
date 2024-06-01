package com.program.spring2.repository;

import com.program.spring2.entities.ApplicationRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ApplicationsRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandledIsTrue();
    List<ApplicationRequest> findByHandledIsFalse();
}
