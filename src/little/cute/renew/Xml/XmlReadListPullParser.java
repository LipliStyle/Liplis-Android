//=======================================================================
//  ClassName : XmlReadListPullParser
//  概要      : XMLを読み込むクラス(Java版)
//			    汎用使用は難しいかも
//
//  Liplisシステム      
//  Copyright(c) 2010-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import little.cute.renew.Common.LiplisDefine;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.util.Xml;

public abstract class XmlReadListPullParser {

    /// <summary>
    /// readXml
    /// XMLを読み込む
    /// </summary>
    protected XmlPullParser getXmlFromSt(InputStream st)
    {
        try
        {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(st, LiplisDefine.POST_RETURN_CHARCODE);
            return parser;
        }
        catch (Exception err)
        {
        	return null;
        }
    }
	
    /// <summary>
    /// readXml
    /// XMLを読み込む
    /// </summary>
    protected XmlPullParser getXmlFromUrl(String xmlUrl)
    {
        try
        {
            XmlPullParser parser = Xml.newPullParser();
            //URLを指定して接続
            URL url = new URL(xmlUrl);
            URLConnection connect = url.openConnection();
            parser.setInput(connect.getInputStream(), LiplisDefine.POST_RETURN_CHARCODE);
            return parser;
        }
        catch (Exception err)
        {
        	return null;
        }
    }
    
    //loadXmlを実装
    
    /// <summary>
    /// loadXml
	/// XMLをロードする
    /// </summary>
	protected String loadXmlStr(XmlPullParser parser)
	{
		String result = "";
        int eventType;
        try {
			while((eventType = parser.next()) != XmlPullParser.END_DOCUMENT){
			    //laeListタグを見つけたらaddItemへ

			    //タイトル取得
			    if(eventType == XmlPullParser.START_TAG && "TITLE".equals(parser.getName())){
			    	eventType = parser.next();
			    	if(eventType == XmlPullParser.TEXT){
			    		result = (parser.getText());
			        }
			    }
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
    
}
