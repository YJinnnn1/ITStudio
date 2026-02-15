package com.itstudio.club_backend.repository;

import com.itstudio.club_backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByIsActive(Boolean isActive);
    List<Member> findByDepartment(String department);
}