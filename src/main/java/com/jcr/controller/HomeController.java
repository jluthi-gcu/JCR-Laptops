package com.jcr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.jcr.model.LoginModel;

/**
 * Controller responsible for managing user home operations. It handles
 * incoming GET requests for the "/" URL.
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public String home(Authentication authentication) {

		//Log
		logger.info("Entering HomeController.home()");
		
		if (authentication.isAuthenticated()) {
			//Log
			logger.info("Exiting HomeController.home()");
			return "redirect:/dashboard/";
		}else {
			//Log
			logger.info("Exiting HomeController.home()");
			return "redirect:/login/";
		}
		
		

		
	}
}
