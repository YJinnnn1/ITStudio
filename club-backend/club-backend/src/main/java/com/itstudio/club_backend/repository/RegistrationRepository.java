package com.itstudio.club_backend.repository;

import com.itstudio.club_backend.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentId(String studentId);
}
