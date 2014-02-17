//=======================================================================
//  ClassName : ObjLiplisLogList
//  概要      	  : ログリスト
//
//	extends   : 
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.Obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjLiplisLogList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///=====================================
    /// ログリスト
	private List<ObjLiplisLog> logList = new ArrayList<ObjLiplisLog>();
	
    /// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisLogList()
	{
		
	}
	
    /// <summary>
    /// append
	/// ログを追加する
    /// </summary>
	public void append(ObjLiplisLog log)
	{
		//ログの追加
		logList.add(log);
		
		//100件以上は消す
		if(logList.size() >= 100)
		{
			logList.remove(0);
		}
	}
	public void append(String log, String url)
	{
		int type = 0;
		
		if(url == null)
		{
			type = 0;
		}
		else
		{
			if(url.equals(""))
			{
				type = 0;
			}
			else
			{
				type = 1;
			}
		}
		
		
		//ログの追加
		logList.add(new ObjLiplisLog(log,url,type));
		
		//100件以上は消す
		if(logList.size() >= 100)
		{
			logList.remove(0);
		}
	}
	public void append(String log, String url, int type)
	{
		//ログの追加
		logList.add(new ObjLiplisLog(log,url,type));
		
		//100件以上は消す
		if(logList.size() >= 100)
		{
			logList.remove(0);
		}
	}

    /// <summary>
    /// getLogList
	/// ログリストのゲッター
    /// </summary>
	public List<ObjLiplisLog> getLogList() {
		return logList;
	}

    /// <summary>
    /// getLogList
	/// ログリストのゲッター
    /// </summary>
	public ObjLiplisLog getLog(int idx)
	{
		try
		{
			return logList.get(idx);
		}
		catch(Exception e)
		{
			return new ObjLiplisLog("","",0);
		}
	}
	
	
	
	
}
