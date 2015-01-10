//=======================================================================
//  ClassName : LiplisShortNews
//  概要      : Liplisショートニュース
//
//  Liplisシステム
//  Copyright(c) 2010-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml.LiplisShortNews;

import java.io.IOException;
import java.io.InputStream;

import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Xml.XmlReadListPullParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;


public class LiplisShortNewsJp extends XmlReadListPullParser implements LiplisShortNewsBase{

    /// --------------------------------------
    /// パス定義
	private static final String TAG_RESULT  = "result";


    /// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisShortNewsJp(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public MsgShortNews getShortNews(InputStream is)
	{
		return loadXml(getXmlFromSt(is));
	}


    /// <summary>
    /// loadXml
	/// XMLをロードする
    /// </summary>
	public MsgShortNews loadXml(XmlPullParser parser)
	{
		MsgShortNews msgShortNews = new MsgShortNews();
        int eventType;
        try {
			while((eventType = parser.next()) != XmlPullParser.END_DOCUMENT){
			    //タイトル取得
			    if(eventType == XmlPullParser.START_TAG && TAG_RESULT.equals(parser.getName())){
			    	eventType = parser.next();
			    	if(eventType == XmlPullParser.TEXT){
			    		msgShortNews = itemConvert(parser.getText());
			        }
			    }
			}
			return msgShortNews;
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("LiplisShortNewsJp loadXml",e.toString());
			return msgShortNews;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MsgShortNews();
		}
	}

    /// <summary>
    /// itemConvert
	/// ストリングをメッセージリストに変換する
	/// URL;str1,emotion1,point1;str2,emotion2,point2;・・・
	/// と表されている出力結果を分解する。
    /// </summary>
    private MsgShortNews itemConvert(String xml)
    {
    	MsgShortNews res = new MsgShortNews();
    	int idx = 1;

    	try
    	{
        	String[] wordList = xml.split(";");

        	res.url = wordList[0];

        	if(wordList.length > 1)
        	{
            	for(idx = 1; idx < wordList.length; idx++)
            	{
            		String[] bufList = wordList[idx].split(",");
            		try
            		{
            			res.nameList.add(bufList[0]);
            			res.emotionList.add(Integer.valueOf(bufList[1]));
            			res.pointList.add(Integer.valueOf(bufList[2]));
            		}
            		catch(Exception e)
            		{
            			if(bufList.length > 0)
            			{
            				res.nameList.add(bufList[0]);
            			}else
            			{
            				res.nameList.add("");
            			}
            			res.emotionList.add(0);
            			res.pointList.add(0);
            		}
        		}
        	}

    	}
    	catch(Exception e)
    	{
    		res = new MsgShortNews();
    	}

    	return res;

    }
}
