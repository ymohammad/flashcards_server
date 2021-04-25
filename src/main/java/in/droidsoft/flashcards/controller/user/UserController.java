/*******************************************************************************************************************************
UserController.java

Copyright © 2021, Power Integrations Corporation. All rights reserved.
The Programs (which include both the software and documentation) contain proprietary information of Power Integrations Corporation;
they are provided under a license agreement containing restrictions on use and disclosure and are also protected by
copyright, patent and other intellectual and industrial property law. Reverse engineering, disassembly or de-compilation of
the programs is prohibited.
Program Documentation is licensed for use solely to support the deployment of the Programs and not for any other
purpose.
The information contained in this document is subject to change without notice. If you find any problems in the
documentation, please report them to us in writing. Power Integrations Corporation does not warrant that this document is error free.
Except as may be expressly permitted in your license agreement for these Programs, no part of these Programs may be
reproduced or transmitted in any form or by any means, electronic or mechanical, for any purpose, without the express
written permission of Power Integrations Corporation.

Author : ymohammad
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.droidsoft.flashcards.dto.ApiResponseObject;
import in.droidsoft.flashcards.dto.UserInputRequest;
import in.droidsoft.flashcards.model.user.User;
import in.droidsoft.flashcards.service.user.UserService;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class UserController
*/
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired UserService userService;
    
    @GetMapping("/all")
    public ApiResponseObject getAllUsers() {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("RuleController.getAllPricingRules() received request.");
	    List<User> allUsers = this.userService.getAllUsers();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(allUsers, null, "List of Users.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
    
    @PostMapping("/create")
    public ApiResponseObject createUser(@RequestBody UserInputRequest userInput) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("RuleController.createUser() received request.");
	    User createNewUser = this.userService.createNewUser(userInput.getUserName(), userInput.getPassword(), userInput.getName());
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(createNewUser, null, "User created successfully.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
    
    @PostMapping("/login")
    public ApiResponseObject loginUser(@RequestBody UserInputRequest userInput) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("RuleController.loginUser() received request.");
	    boolean isValid = this.userService.isValidUser(userInput.getUserName(), userInput.getPassword());
	    if (isValid) {
		returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(isValid, null, "Valid User");
	    } else {
		returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, new Exception("Incorrect password."), null);
	    }
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
}
