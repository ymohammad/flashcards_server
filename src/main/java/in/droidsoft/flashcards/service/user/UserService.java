/*******************************************************************************************************************************
UserService.java

Copyright © 2021, DroidSoft Corporation. All rights reserved.
The Programs (which include both the software and documentation) contain proprietary information of DroidSoft Corporation;
they are provided under a license agreement containing restrictions on use and disclosure and are also protected by
copyright, patent and other intellectual and industrial property law. Reverse engineering, disassembly or de-compilation of
the programs is prohibited.
Program Documentation is licensed for use solely to support the deployment of the Programs and not for any other
purpose.
The information contained in this document is subject to change without notice. If you find any problems in the
documentation, please report them to us in writing. DroidSoft Corporation does not warrant that this document is error free.
Except as may be expressly permitted in your license agreement for these Programs, no part of these Programs may be
reproduced or transmitted in any form or by any means, electronic or mechanical, for any purpose, without the express
written permission of DroidSoft Corporation.

Author : ymohammad
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.droidsoft.flashcards.components.CryptoHashing;
import in.droidsoft.flashcards.model.user.User;
import in.droidsoft.flashcards.repository.user.UserRepository;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class UserService
*/
@Service
public class UserService {
    @Autowired private UserRepository userRepo;
    @Autowired private CryptoHashing cryptoHashing;
    
    /**
     * Get the list of users.
     * @return
     */
    public List<User> getAllUsers() {
	return this.userRepo.findAll();
    }
    /**
     * Verifies if user exist in the db.
     * @param userName
     * @return
     */
    public User getActiveUserFromDb(String userName) {
	return this.userRepo.findUserByUserNameAndIsActive(userName, 1);
    }
    
    /**
     * Creates a new User.
     * @param userName
     * @param password
     * @param name
     * @return
     * @throws Exception
     */
    public User createNewUser(String userName, String password, String name) throws Exception {
	System.out.println("UserService.createNewUser() request received... userName:" + userName);
	if (ApplicationUtils.isEmpty(userName) || ApplicationUtils.isEmpty(password) || ApplicationUtils.isEmpty(name)) {
	    System.out.println("UserService.createNewUser() improper data is provided...");
	    throw new Exception("Incomplete Payload to create new user.");
	}
	long t1 = System.currentTimeMillis();
	String hashedPassword = this.cryptoHashing.hash(password);
	System.out.println("UserService.createNewUser() hashedPassword:" + hashedPassword);
	System.out.println("Time taken for Hashing:" + (System.currentTimeMillis()-t1) + " milliseconds.");
	
	User user = new User();
	user.setUserName(userName);
	user.setPasswordHash(hashedPassword);
	user.setIsActive(1);
	user.setCreatedDate(LocalDateTime.now());
	user.setName(name);
	
	User savedUser = this.userRepo.save(user);
	this.userRepo.flush();
	return savedUser;
    }
    
    /**
     * Checks if the valid user.
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public boolean isValidUser(String userName, String password) throws Exception {
	if (ApplicationUtils.isEmpty(userName) || ApplicationUtils.isEmpty(password)) {
	    throw new Exception("Username or password is empty.");
	}
	User userFromDb = getActiveUserFromDb(userName);
	if (userFromDb == null) {
	    throw new Exception("User with this username " + userName + " is not exist.");
	}
	return this.cryptoHashing.verifyHash(password, userFromDb.getPasswordHash());
    }
}
