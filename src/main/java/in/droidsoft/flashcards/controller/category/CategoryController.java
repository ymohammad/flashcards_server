/*******************************************************************************************************************************
CategoryController.java

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

package in.droidsoft.flashcards.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.droidsoft.flashcards.dto.ApiResponseObject;
import in.droidsoft.flashcards.dto.CategoryRequest;
import in.droidsoft.flashcards.model.category.Category;
import in.droidsoft.flashcards.service.category.CategoryService;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class CategoryController
*/
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired private CategoryService categoryService;
    
    @GetMapping("/all")
    public ApiResponseObject getAllCategories() {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("CategoryController.getAllCategories() received request.");
	    List<Category> categoryList = this.categoryService.getAllCategories();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(categoryList, null, "Categories list.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
    
    @PostMapping("/createCategory")
    public ApiResponseObject createNewCardType(CategoryRequest categoryReq) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("CategoryController.createNewCardType() received request.");
	    if (categoryReq == null) {
		throw new Exception("Invalid payload to create Category.");
	    }
	    Category createNewCategory = this.categoryService.createNewCategory(categoryReq);
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(createNewCategory, null, "Category is created successfully.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
}
