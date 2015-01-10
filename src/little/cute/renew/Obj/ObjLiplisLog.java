//=======================================================================
//  ClassName : ObjLiplisLog
//  概要      	  : ログオブジェクト
//
//	extends   : 
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Obj;

import java.io.Serializable;

public class ObjLiplisLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///=====================================
    /// クラス
	private String log;
	private String url;
	private int type;

    /// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisLog(String log, String url, int type)
	{
		this.log = log;
		this.url = url;
		this.type = type;
	}
	
	public final String getLog() {
		return log;
	}

	public final String getUrl() {
		return url;
	}

	public final int getType() {
		return type;
	}

	
	
}
