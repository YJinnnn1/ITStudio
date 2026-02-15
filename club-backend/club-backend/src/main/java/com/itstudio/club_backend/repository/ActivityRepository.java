package com.itstudio.club_backend.repository;

import com.itstudio.club_backend.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
