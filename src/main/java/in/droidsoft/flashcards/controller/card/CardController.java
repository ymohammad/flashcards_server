/*******************************************************************************************************************************
CardController.java

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
Date   : Apr 26, 2021

Last modified by : ymohammad
Last modified on : Apr 26, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.controller.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.droidsoft.flashcards.dto.ApiResponseObject;
import in.droidsoft.flashcards.dto.CardTypeRequest;
import in.droidsoft.flashcards.model.card.CardType;
import in.droidsoft.flashcards.service.card.CardService;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class CardController
*/
@RestController
@RequestMapping("/api/card")
public class CardController {
    
    @Autowired private CardService cardService;
    
    @GetMapping("/cardTypes")
    public List<CardType> getCardTypes() {
	return this.cardService.getAllCardTypes();
    }
    
    @PostMapping("/createCardType")
    public ApiResponseObject createNewCardType(CardTypeRequest cardRequest) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("RuleController.createUser() received request.");
	    if (cardRequest == null) {
		throw new Exception("Invalid payload to create card type.");
	    }
	    CardType createdCardType = this.cardService.createNewCardType(cardRequest.getCardTypeCode(), cardRequest.getDescription());
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(createdCardType, null, "Card Type is created successfully.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
}
