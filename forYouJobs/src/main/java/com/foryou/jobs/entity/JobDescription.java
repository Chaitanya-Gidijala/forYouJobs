package com.foryou.jobs.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "job_descriptions")
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @Column(nullable = false)
    private String companyName;

    private String companyImage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date datePosted;

    @Transient
    private String datePostedFormatted;


    @Column(nullable = false)
    private String jobTitle;

    @Column(name = "job_description", columnDefinition = "LONGTEXT", nullable = false)
    private String jobDetails;

    @Column(name = "job_applylink")
    private String applyLink;
    
    
    public JobDescription() {
    }

    public JobDescription(String companyName, String companyImage, Date datePosted, String jobTitle, String jobDescription, String applyLink) {
        this.companyName = companyName;
        this.companyImage = companyImage;
        this.datePosted = datePosted;
        this.jobTitle = jobTitle;
        this.jobDetails = jobDescription;
        this.applyLink = applyLink;
    }


  
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    

    public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	

	public String getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(String jobDetails) {
		this.jobDetails = jobDetails;
	}

	public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getDatePostedFormatted() {
        return datePostedFormatted;
    }

    public void setDatePostedFormatted(String datePostedFormatted) {
        this.datePostedFormatted = datePostedFormatted;
    }

	public String getApplyLink() {
		return applyLink;
	}

	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}
    
    
    
    
}
