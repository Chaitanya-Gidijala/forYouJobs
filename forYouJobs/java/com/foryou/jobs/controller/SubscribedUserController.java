package com.foryou.jobs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foryou.jobs.entity.SubscribedUser;
import com.foryou.jobs.service.EmailService;
import com.foryou.jobs.service.SubscribedUserService;

import jakarta.mail.MessagingException;


@Controller
public class SubscribedUserController {
	
    private final SubscribedUserService userService;
    private final EmailService emailService;

    @Autowired
    public SubscribedUserController(SubscribedUserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }
    
	@GetMapping("/user-form")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("user", new SubscribedUser());

		return "subscribed-user";
	}
	@PostMapping("/save-user")
	public String saveUser(SubscribedUser user, @RequestParam("email") String email, Model model) throws MessagingException {
	    List<SubscribedUser> subscribedUsers = userService.getAllSubscribedUsers();
	    boolean emailExists = false;

	    for (SubscribedUser userFromDb : subscribedUsers) {
	        if (userFromDb.getEmail().equals(email)) {
	            emailExists = true;
	            break;
	        }
	    }

	    if (emailExists) {
	        System.out.println("Your already a registered user...");
	        emailService.sendEmail(email,"Your Alredy Register",
   				 "Dear User,\n\n" +
                    "Your already register with" + " 'ForYouJobs' portal" + ".\n" +
                    "Thank You for revisting...\n\n" +
                    "Best regards,\n" +
                    "ForYouJobs");
   		
   		return "redirect:/";
	    } else {
	        System.out.println("You're a new user. Thank you for registering with us...");
	        userService.saveSubscribedUser(user);
	        
    		System.out.println("user saved into your Database...");
    		
    	    model.addAttribute("successMessage", "Your subscription saved successfully!");

    		emailService.sendEmail(user.getEmail(), "Registration Confirmation!", 
    				"Dear User,\n\n" +
                    "Thank you for registering with " + " 'ForYouJobs' portal" + ".\n" +
                    "Your registration was successful.\n\n" +
                    "Best regards,\n" +
                    "ForYouJobs");
    		System.out.println("-------------- Mail Sent Successfully..-----------------");
	    }

	    return "redirect:/";
	}

    
//    @PostMapping("/save-new-user")
//	public String saveJobDescription(SubscribedUser user, @RequestParam("email") String email,
//			Model model) {
//    	List<SubscribedUser> subscribedUsers = userService.getAllSubscribedUsers();
//        System.out.println("1st line of the code--------");
//    	for (SubscribedUser user1 : subscribedUsers) {
//        	System.out.println("-----------2nd line of the code");
//	    	if(email == user1.getEmail()) {
//	    		
//	    		 emailService.sendEmail(email,"Your Alredy Register",
//	    				 "Dear User,\n\n" +
//	                     "Your already register with" + " 'ForYouJobs' portal" + ".\n" +
//	                     "Thank You for revisting...\n\n" +
//	                     "Best regards,\n" +
//	                     "ForYouJobs");
//	    		
//	    		return "Mail Sent for already registered user";
//	    	}
//	    	
//	    	else {
//	    		userService.saveSubscribedUser(user);
//	    		System.out.println("user saved into your Database...");
//	    		emailService.sendEmail(user.getEmail(), "Registration Confirmation!", 
//        				"Dear User,\n\n" +
//                        "Thank you for registering with " + " 'ForYouJobs' portal" + ".\n" +
//                        "Your registration was successful.\n\n" +
//                        "Best regards,\n" +
//                        "ForYouJobs");
//	    		System.out.println("User saved and successfull sent mail...Lol...");
//	    		System.out.println("--------------------------");
//	    	}
//        }
//        
//        System.out.println("direct------page");
//		return "redirect:/user-form";
//	}
    @GetMapping("/send-emails")
    public String sendEmails() {
        List<SubscribedUser> subscribedUsers = userService.getAllSubscribedUsers();
        for (SubscribedUser user : subscribedUsers) {
        	
        	
            emailService.sendEmail(user.getEmail(), "Registration Confirmation!", 
            				"Dear User,\n\n" +
                            "Thank you for registering with " + " 'ForYouJobs' portal" + ".\n" +
                            "Your registration was successful.\n\n" +
                            "Best regards,\n" +
                            "ForYouJobs");
        }
        return "Emails sent successfully!";
    }
}
