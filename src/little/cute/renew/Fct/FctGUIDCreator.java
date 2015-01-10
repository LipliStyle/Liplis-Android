//=======================================================================
//  ClassName : FctGUIDCreater
//  概要      : GUIDクリエイター
//
//	extends   : 
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Fct;

import java.util.Calendar;
import java.util.UUID;

public class FctGUIDCreator {
	
	/// <summary>
    /// createGuid
	/// 独自Guidを作成する
    /// </summary>
	public static String createGuid()
	{
		return UUID.randomUUID().toString() + Calendar.getInstance().get(Calendar.MILLISECOND);
	}
	
}
