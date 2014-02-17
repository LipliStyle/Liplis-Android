//=======================================================================
//  ClassName : MsgShortNews
//  概要      : ショートニュースメッセージ
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//
//
//2013/03/02 ver3.2.0 シリアル化可能
//
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MsgShortNews implements Serializable {

	///=============================
    ///シリアライズドID
	private static final long serialVersionUID = 1L;

	///=============================
    ///プロパティ
	public String url;
//	public String createDate;
//	public String source;
//	public String converted;
	public List<String> nameList;
	public List<Integer> emotionList;
	public List<Integer> pointList;
	public boolean flgSuccess;

	/// <summary>
    /// コンストラクター
    /// </summary>
	public MsgShortNews()
	{
		nameList = new ArrayList<String>();
		emotionList = new  ArrayList<Integer>();
		pointList = new  ArrayList<Integer>();
		flgSuccess = false;
	}

	public MsgShortNews(String name, int emotion, int point)
	{
		nameList = new ArrayList<String>();
		emotionList = new  ArrayList<Integer>();
		pointList = new  ArrayList<Integer>();
		flgSuccess = false;

		nameList.add(name);
		emotionList.add(emotion);
		pointList.add(point);
	}

	/// <summary>
    /// getNameFirst
	///	１個目のネームを返す
    /// </summary>
	public String getMessage()
	{
		if(nameList.size() > 0)
		{
			return nameList.get(0);
		}else
		{
			return "";
		}
	}

}
