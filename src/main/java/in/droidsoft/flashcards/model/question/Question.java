/*******************************************************************************************************************************
Question.java

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

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class Question
*/
@Entity
@Table(name = "T_QUESTIONS")
@Data
@NoArgsConstructor
public class Question implements Serializable {
    
    private static final long serialVersionUID = 3197614106408277094L;

    @Id
    @Column(name = "question_id")
    @JsonIgnore
    @SequenceGenerator(name = "question_s", sequenceName = "question_s")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_s")
    private Long questionId;
    
    @Column(name = "heading")
    private String heading;
    
    @Column(name = "body")
    private String body;
    
    @Column(name = "footer")
    private String footer;
    
    @Column(name = "selection_type")
    private String selectionType;
    
    @OneToMany(mappedBy="questionId")
    private List<QuestionOption> questionOptions;
}
