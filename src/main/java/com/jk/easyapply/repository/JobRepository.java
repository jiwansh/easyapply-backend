package com.jk.easyapply.repository;

import com.jk.easyapply.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // search by tech stack
    List<Job> findByTechStackContainingIgnoreCase(String techStack);

    // search by batch
    List<Job> findByBatch(String batch);

    // search by title keyword
    List<Job> findByTitleContainingIgnoreCase(String keyword);
}
