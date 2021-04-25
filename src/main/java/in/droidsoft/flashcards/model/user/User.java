/*******************************************************************************************************************************
User.java

Copyright © 2021, DroaidSoft Corporation. All rights reserved.
The Programs (which include both the software and documentation) contain proprietary information of DroaidSoft Corporation;
they are provided under a license agreement containing restrictions on use and disclosure and are also protected by
copyright, patent and other intellectual and industrial property law. Reverse engineering, disassembly or de-compilation of
the programs is prohibited.
Program Documentation is licensed for use solely to support the deployment of the Programs and not for any other
purpose.
The information contained in this document is subject to change without notice. If you find any problems in the
documentation, please report them to us in writing. DroaidSoft Corporation does not warrant that this document is error free.
Except as may be expressly permitted in your license agreement for these Programs, no part of these Programs may be
reproduced or transmitted in any form or by any means, electronic or mechanical, for any purpose, without the express
written permission of DroaidSoft Corporation.

Author : ymohammad
Date   : Apr 25, 2021

Last modified by : ymohammad
Last modified on : Apr 25, 2021

*******************************************************************************************************************************/

package in.droidsoft.flashcards.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Class User
*/
@Entity
@Table(name = "T_USERS")
@Data
@NoArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = -108704262592874859L;

    @Id
    @SequenceGenerator(name = "user_s", sequenceName = "user_s")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_s")
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "password_hash")
    private String passwordHash;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "is_active")
    private Integer isActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
