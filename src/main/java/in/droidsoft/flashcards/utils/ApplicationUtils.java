/*******************************************************************************************************************************
ApplicationUtils.java

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

package in.droidsoft.flashcards.utils;


import org.springframework.util.StringUtils;

import in.droidsoft.flashcards.dto.ApiResponseObject;

/**
* Class ApplicationUtils
*/
public class ApplicationUtils {
    
    private ApplicationUtils() {
    }
    
    /**
     * Checks emptyness of a string object.
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
	if (StringUtils.hasText(str) && str.trim().length() > 0) {
	    return false;
	}
	return true;
    }
    /**
     * Standard API Response Bean object. It is expected to be sent by all the API responses.
     * @param resultObj
     * @param ex
     * @param message
     * @return
     */
    public static ApiResponseObject prepareStandardAPIBeanResponse(Object resultObj, Exception ex, String message) {
	ApiResponseObject response = new ApiResponseObject();
	if (ex != null) {
	    response.setStatus(0);
	    response.setMsg(exceptionMsg(ex));
	    response.setData("");
	} else {
	    response.setStatus(1);
	    response.setMsg(message);
	    response.setData(resultObj);
	}
	return response;
    }
    
    /**
     * Gets exception Message from the object.
     * 
     * @param ex
     * @return
     */
    public static String exceptionMsg(Exception ex) {
	if (ex == null)
	    return null;

	String msg = ex.getMessage() != null && ex.getMessage().length() > 0 ? ex.getMessage() : ex.toString();
	return msg;
    }
}
