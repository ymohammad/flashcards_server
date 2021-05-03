/*******************************************************************************************************************************
CardController.java

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

package in.droidsoft.flashcards.controller.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.droidsoft.flashcards.dto.ApiResponseObject;
import in.droidsoft.flashcards.dto.CardRequest;
import in.droidsoft.flashcards.dto.CardTypeRequest;
import in.droidsoft.flashcards.model.card.Card;
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
    
    @GetMapping("/all/{userName}/{category}")
    public ApiResponseObject getAllCardsForUserInACategory(@PathVariable(value = "userName") String userName, 
	    @PathVariable(value = "category") String categoryName) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("CardController.getAllCardsForUserInACategory() received request.");
	    List<Card> allCards = this.cardService.getAllCardsForUserInACategory(userName, categoryName);
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(allCards, null, "Card Type is created successfully.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
    
    @PostMapping("/createNewCard")
    public ApiResponseObject createNewCard(CardRequest cardReq) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("CardController.createNewCard() received request.");
	    if (cardReq == null) {
		throw new Exception("Invalid payload to create new card.");
	    }
	    Card createdCard = this.cardService.createNewCard(cardReq);
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(createdCard, null, "Card is created successfully.");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    returnObj = ApplicationUtils.prepareStandardAPIBeanResponse(null, ex, null);
	}
	return returnObj;
    }
    
    @GetMapping("/cardTypes")
    public List<CardType> getCardTypes() {
	return this.cardService.getAllCardTypes();
    }
    
    @PostMapping("/createCardType")
    public ApiResponseObject createNewCardType(CardTypeRequest cardRequest) {
	ApiResponseObject returnObj = null;
	try {
	    System.out.println("CardController.createNewCardType() received request.");
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
