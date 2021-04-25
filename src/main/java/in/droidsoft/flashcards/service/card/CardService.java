/*******************************************************************************************************************************
CardService.java

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

package in.droidsoft.flashcards.service.card;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.droidsoft.flashcards.model.card.CardType;
import in.droidsoft.flashcards.repository.card.CardTypeRepository;
import in.droidsoft.flashcards.utils.ApplicationUtils;

/**
* Class CardService
*/
@Service
public class CardService {
    
    @Autowired private CardTypeRepository cardTypeRepo;
    
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
}
