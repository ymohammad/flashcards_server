/*******************************************************************************************************************************
CardService.java

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

package in.droidsoft.flashcards.service.card;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.droidsoft.flashcards.dto.CardRequest;
import in.droidsoft.flashcards.model.card.Card;
import in.droidsoft.flashcards.model.card.CardType;
import in.droidsoft.flashcards.model.category.Category;
import in.droidsoft.flashcards.model.question.Question;
import in.droidsoft.flashcards.model.user.User;
import in.droidsoft.flashcards.repository.card.CardRepository;
import in.droidsoft.flashcards.repository.card.CardTypeRepository;
import in.droidsoft.flashcards.repository.category.CategoryRepository;
import in.droidsoft.flashcards.repository.question.QuestionRepository;
import in.droidsoft.flashcards.repository.user.UserRepository;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class CardService
*/
@Service
public class CardService {
    
    @Autowired private CardRepository cardRepo;
    @Autowired private CardTypeRepository cardTypeRepo;
    @Autowired private CategoryRepository categoryRepo;
    @Autowired private QuestionRepository questionRepo;
    @Autowired private UserRepository userRepo;
    
    public List<CardType> getAllCardTypes() {
	return this.cardTypeRepo.findAll();
    }
    /**
     * Create new Card Types.
     * @param cardTypeCode
     * @param description
     * @return
     * @throws Exception
     */
    public CardType createNewCardType(String cardTypeCode, String description) throws Exception {
	if (ApplicationUtils.isEmpty(cardTypeCode)) {
	    throw new Exception("Card Type Code is required.");
	}
	if (ApplicationUtils.isEmpty(description)) {
	    throw new Exception("Card Type Description is required.");
	}
	
	CardType type = new CardType();
	type.setCardTypeCode(cardTypeCode);
	type.setDescription(description);
	type.setCreatedDate(LocalDateTime.now());
	
	CardType savedCardType = this.cardTypeRepo.save(type);
	return savedCardType;
    }
    
    /**
     * Get all Cards for the user in a given category.
     * 
     * @param userName
     * @param categoryName
     * @return
     */
    public List<Card> getAllCardsForUserInACategory(String userName, String categoryName) {
	return null;
    }
    
    /**
     * It creates a new Card.
     * @param cardReq
     * @return
     * @throws Exception
     */
    public Card createNewCard(CardRequest cardReq) throws Exception {
	validateCardCreation(cardReq);
	
	User userObj = this.userRepo.findUserByUserName(cardReq.getUserName());
	if (userObj == null) throw new Exception("User information is not exist in the database.");
	
	//create a category if not exist.
	Category categoryDb = createNewCategoryIfNotExist(cardReq.getCategoryName(), userObj.getUserId());
	Question createNewQuestion = createNewQuestion(cardReq.getQuestion());
	CardType cardTypeDb = this.cardTypeRepo.findCardTypeByCardTypeCode(cardReq.getCardTypeCode());
	
	//New Card object.
	Card card = new Card();
	card.setCategoryId(categoryDb.getCategoryId());
	card.setQuestionId(createNewQuestion.getQuestionId());
	card.setCardTypeId(cardTypeDb.getCardTypeId());
	card.setSequence(cardReq.getSequence());
	card.setAnswer(cardReq.getAnswer());
	card.setIsFavourite(cardReq.getIsFavourite());
	card.setUserId(userObj.getUserId());
	
	Card savedCard = this.cardRepo.save(card);
	System.out.println("CardService.createNewCard() savedCard :" + savedCard);
	return savedCard;
    }
    
    /**
     * Creates a new Question into the database.
     * 
     * @param question
     * @return
     * @throws Exception
     */
    public Question createNewQuestion(Question question) throws Exception {
	if (question == null) throw new Exception("Question information is required.");
	
	return this.questionRepo.save(question);
    }
    /**
     * Create a new Category into the database.
     * @param categoryName
     * @param userObj
     * @return
     */
    public Category createNewCategoryIfNotExist(String categoryName, Long userId) {
	Category categoryInDb = this.categoryRepo.findCategeoryByCategoryName(categoryName);
	if (categoryInDb == null) {
	    categoryInDb = new Category();
	    categoryInDb.setCategoryName(categoryName);
	    categoryInDb.setDescription(categoryName);
	    categoryInDb.setCreatedBy(userId);
	    categoryInDb.setCreatedDate(LocalDateTime.now());
	    categoryInDb = this.categoryRepo.save(categoryInDb);
	}
	return categoryInDb;
    }
    
    /**
     * Validates the Card details.
     * @param cardReq
     * @throws Exception
     */
    private void validateCardCreation(CardRequest cardReq) throws Exception {
	if (cardReq == null) throw new Exception("Incomplete Card request details provided.");
	if (ApplicationUtils.isEmpty(cardReq.getCategoryName())) throw new Exception("Category is required to create a new card.");
	if (ApplicationUtils.isEmpty(cardReq.getCardTypeCode())) throw new Exception("Card Type code is required.");
	if (cardReq.getQuestion() == null || ApplicationUtils.isEmpty(cardReq.getQuestion().getBody())) throw new Exception("Question information is not available.");
	if (ApplicationUtils.isEmpty(cardReq.getAnswer())) throw new Exception("Please include Answer in the card.");
	if (ApplicationUtils.isEmpty(cardReq.getUserName())) throw new Exception("Username is required to create a new card.");
    }
}
