package com.example.repository;

import com.example.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Transactional
    @Modifying
    @Query("delete from TaskEntity t where t.id = ?1")
    void deleteTaskById(Long id);
}