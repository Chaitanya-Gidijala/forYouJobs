package com.foryou.jobs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foryou.jobs.entity.JobDescription;

@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long>
{

	@Query("SELECT jd FROM JobDescription jd WHERE jd.category = :category")
	List<JobDescription> findByCategory(@Param("category") String category);

	@Query("SELECT jd FROM JobDescription jd WHERE jd.jobTitle = :jobTitle")
	JobDescription findByJobTitle(@Param("jobTitle") String jobTitle);

	 // New method with pagination and sorting by date in descending order
    Page<JobDescription> findAllByOrderByDatePostedDesc(Pageable pageable);

	long countByCategory(String string);

	Page<JobDescription> findByJobTitleContainingIgnoreCase(String keyword, Pageable paging);
	
	

}
