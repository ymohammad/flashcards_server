/*******************************************************************************************************************************
Category.java

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
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.model.category;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class Category
*/
@Entity
@Table(name = "T_CATEGORIES")
@Data
@NoArgsConstructor
public class Category implements Serializable {
    
    private static final long serialVersionUID = 5865249698700559417L;
    
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "created_by")
    private Long createdBy;
}
