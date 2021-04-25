/*******************************************************************************************************************************
Card.java

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

package in.droidsoft.flashcards.model.card;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.droidsoft.flashcards.model.category.Category;
import in.droidsoft.flashcards.model.question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class Card
*/
@Entity
@Table(name = "T_CARDS")
@Data
@NoArgsConstructor
public class Card implements Serializable {
    
    private static final long serialVersionUID = 5493600438077383442L;
    
    @Id
    @Column(name = "card_id")
    private Long cardId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "category_id" )
    private Category category;
    
    @Column(name = "sequence")
    private Integer sequence;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="type", referencedColumnName = "card_type_id")
    private CardType cardType;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName="question_id")
    private Question questions;
    
    @Column(name = "answer")
    private String answer;
    
    @Column(name = "is_favourite")
    private Integer isFavourite;
    
    @Column(name = "user_id")
    private Long userId;
}
