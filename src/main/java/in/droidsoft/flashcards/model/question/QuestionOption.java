/*******************************************************************************************************************************
QuestionOption.java

Copyright © 2021, DroidSoft. All rights reserved.
The Programs (which include both the software and documentation) contain proprietary information of DroidSoft;
they are provided under a license agreement containing restrictions on use and disclosure and are also protected by
copyright, patent and other intellectual and industrial property law. Reverse engineering, disassembly or de-compilation of
the programs is prohibited.
Program Documentation is licensed for use solely to support the deployment of the Programs and not for any other
purpose.
The information contained in this document is subject to change without notice. If you find any problems in the
documentation, please report them to us in writing. DroidSoft does not warrant that this document is error free.
Except as may be expressly permitted in your license agreement for these Programs, no part of these Programs may be
reproduced or transmitted in any form or by any means, electronic or mechanical, for any purpose, without the express
written permission of DroidSoft.

Author : ymohammad
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class QuestionOption
*/
@Entity
@Table(name = "T_QUESTION_OPTIONS")
@Data
@NoArgsConstructor
public class QuestionOption {
    
    @Id
    @Column(name = "option_id")
    private Long optionId;
    
    @Column(name = "option_text")
    private String optionText;
    
    @Column(name = "is_answer")
    private Integer isAnswer;
    
    @ManyToOne (targetEntity = Question.class)
    @JoinColumn(name = "question_id", nullable=false)
    private Long questionId;
}
