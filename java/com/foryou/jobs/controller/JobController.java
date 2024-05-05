package com.foryou.jobs.controller;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foryou.jobs.entity.JobDescription;
import com.foryou.jobs.entity.SubscribedUser;
import com.foryou.jobs.repository.JobDescriptionRepository;
import com.foryou.jobs.service.JobNotificationService;

@Controller
public class JobController {

	@Autowired
	private JobDescriptionRepository jobDescriptionRepository;

	@Autowired
	private JobNotificationService jobNotificationService;

	@GetMapping("/admin/add-job")
	public String showAddJobForm(Model model) {
		System.out.println("form for adding");
		JobDescription jobDescription = new JobDescription();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		String formattedDate = formatter.format(new Date());
		jobDescription.setDatePostedFormatted(formattedDate);
		model.addAttribute("jobDescription", jobDescription);
		return "form-for-add-job";
	}
	
	

	@PostMapping("/saveJob")
	public String saveJobDescription(JobDescription jobDescription, @RequestParam("postedDate") Date postedDate,
			Model model) {
	    	System.out.println(jobDescription.getCompanyName());
	    	System.out.println(jobDescription.getDatePosted());
	    	System.out.println(jobDescription.getJobTitle());
	    	System.out.println(jobDescription.getJobTitle());
	    	

		return "errorPage";
	}
//	 @GetMapping("/furniture-store/product/upload")
//	    public String FormToAddProduct( Model model){
//	    	JobDescription jobDescription = new JobDescription();
//	    	model.addAttribute("image",jobDescription);
//	    	return "homepage";
//	    }

	@PostMapping("/save")
	public String saveJobDescription(@RequestParam("category") String category,
			@RequestParam("companyName") String companyName, @RequestParam("companyImage") String companyImage,
			@RequestParam("jobTitle") String jobTitle, @RequestParam("datePosted") Date datePosted,
			@RequestParam("applyLink") String applyLink, @RequestParam("jobDetails") String jobDetails, Model model,
			JobDescription jobDescription

	) {

		JobDescription jobDescription1 = new JobDescription();

		jobDescription1.setCompanyName(companyName);
		jobDescription1.setJobTitle(jobTitle);
		jobDescription1.setCategory(category);
		jobDescription1.setCompanyImage(companyImage);
		jobDescription1.setDatePosted(datePosted);
		jobDescription1.setApplyLink(applyLink);
		jobDescription1.setJobDetails(jobDetails);
		jobDescriptionRepository.save(jobDescription1);
		return "redirect:/admin/add-job"; // Redirect to the form page after saving
	}

	
	@GetMapping("/")
	public String getAll(Model model, @RequestParam(required = false) String keyword,
	                     @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		
	    // Call the common method to fetch and populate the model
		 totalJobsWithSortedOrder(model, page, size);


	    return "jobsList";
	}


	private void totalJobsWithSortedOrder(Model model, int page, int size) {
		long totalJobs = jobDescriptionRepository.countByCategory("job-notifications"); // Assuming jobs have a category  "job"
		 long totalInternships = jobDescriptionRepository.countByCategory("internships"); // Assuming internships have a category "internship"

		 model.addAttribute("totalJobs", totalJobs);
		 model.addAttribute("totalInternships", totalInternships);
	    Pageable pageable = PageRequest.of(page - 1, size);
	    Page<JobDescription> pageTuts = jobNotificationService.findAllPageableAndSortedByDateDesc(pageable);

	    List<JobDescription> tutorials = pageTuts.getContent();

	    model.addAttribute("tutorials", tutorials);
	    model.addAttribute("currentPage", pageTuts.getNumber() + 1);
	    model.addAttribute("totalItems", pageTuts.getTotalElements());
	    model.addAttribute("totalPages", pageTuts.getTotalPages());
	    model.addAttribute("pageSize", size);
	}
	
	
	@GetMapping("/category/{category}")
	public String getjobInfoByCategory(@PathVariable("category") String category, Model model,
			 @RequestParam(required = false) String keyword,
             @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "7") int size) {
		
		long totalJobs = jobDescriptionRepository.countByCategory("job-notifications"); // Assuming jobs have a category
		long totalInternships = jobDescriptionRepository.countByCategory("internships"); // Assuming internships have a
																							// category "internship"
		model.addAttribute("totalJobs", totalJobs);
		model.addAttribute("totalInternships", totalInternships);

		// Retrieve images from the database based on the category
		List<JobDescription> tutorials = jobDescriptionRepository.findByCategory(category);

		List<JobDescription> sortedJobs = tutorials.stream()
				.sorted(Comparator.comparing(JobDescription::getDatePosted).reversed()).collect(Collectors.toList());

		 Pageable pageable = PageRequest.of(page - 1, size);
		    Page<JobDescription> pageTuts = jobNotificationService.findAllPageableAndSortedByDateDesc(pageable);


		    model.addAttribute("tutorials", sortedJobs);
		    model.addAttribute("currentPage", pageTuts.getNumber() + 1);
		    model.addAttribute("totalItems", pageTuts.getTotalElements());
		    model.addAttribute("totalPages", pageTuts.getTotalPages());
		    model.addAttribute("pageSize", size);

		return "homepage";
	}

	@GetMapping("/{jobTitle}")
	public String getProductById(@PathVariable String jobTitle, Model model) {
		JobDescription job = jobDescriptionRepository.findByJobTitle(jobTitle);

		model.addAttribute("job", job);
		return "jobDetails";

	}
}
