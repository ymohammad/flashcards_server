/*******************************************************************************************************************************
CategoryService.java

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
Date   : Apr 26, 2021

Last modified by : ymohammad
Last modified on : Apr 26, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.service.category;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.droidsoft.flashcards.dto.CategoryRequest;
import in.droidsoft.flashcards.model.category.Category;
import in.droidsoft.flashcards.model.user.User;
import in.droidsoft.flashcards.repository.category.CategoryRepository;
import in.droidsoft.flashcards.repository.user.UserRepository;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class CategoryService
*/
@Service
public class CategoryService {
    
    @Autowired private CategoryRepository catRepo;
    @Autowired private UserRepository userRepo;
    
    public List<Category> getAllCategories() {
	return this.catRepo.findAll();
    }
    /**
     * Creates a new Category.
     * @param catReq
     * @return
     * @throws Exception
     */
    public Category createNewCategory(CategoryRequest catReq) throws Exception {
	if (catReq == null) {
	    throw new Exception("Category payload is empty.");
	}
	if (ApplicationUtils.isEmpty(catReq.getCategoryName())) {
	    throw new Exception("Category Name is required.");
	}
	if (ApplicationUtils.isEmpty(catReq.getUserName())) {
	    throw new Exception("Username is empty. Failed to create a category.");
	}

	User foundUserInDb = this.userRepo.findUserByUserNameAndIsActive(catReq.getUserName(), 1);
	if (foundUserInDb == null) {
	    throw new Exception("User is not exist in the database.");
	}
	
	Category category = new Category();
	category.setCategoryName(catReq.getCategoryName());
	category.setDescription(catReq.getDescription());
	category.setCreatedBy(foundUserInDb.getUserId());
	category.setCreatedDate(LocalDateTime.now());
	
	Category savedCategory = this.catRepo.save(category);
	System.out.println("CategoryService.createNewCategory() savedCategory :" + savedCategory);
	return savedCategory;
    }
}
