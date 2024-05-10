package com.example.scrapper.repository;

import com.example.scrapper.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule ,Long> {
}
