package com.foryou.jobs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foryou.jobs.entity.JobDescription;
import com.foryou.jobs.repository.JobDescriptionRepository;

@Service
public class JobNotificationService {
	
	@Autowired
    private JobDescriptionRepository jobDescriptionRepository;
	
	public Page<JobDescription> findAllPageableAndSortedByDateDesc(Pageable pageable) {
	    return jobDescriptionRepository.findAllByOrderByDatePostedDesc(pageable);
	}
}