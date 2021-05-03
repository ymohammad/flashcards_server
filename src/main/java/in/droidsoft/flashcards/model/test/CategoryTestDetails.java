/*******************************************************************************************************************************
CategoryTestDetails.java

Copyright © 2021, DroidSoft All rights reserved.
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
written permission of DroidSoft

Author : ymohammad
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.model.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.droidsoft.flashcards.model.card.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class CategoryTestDetails
*/
@Entity
@Table(name = "T_TEST_DETAILS")
@Data
@NoArgsConstructor
public class CategoryTestDetails {
    @Id
    @Column(name = "test_detail_id")
    private Long testDetailedId;
    
    @Column(name = "test_id")
    private Long testId;
    
    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName="card_id")
    private Card card;
	
    @Column(name = "is_correct_ans")
    private Integer isCorrectAnswer;
    
    @Column(name = "is_skip")
    private Integer isAnswerSkip;
}
