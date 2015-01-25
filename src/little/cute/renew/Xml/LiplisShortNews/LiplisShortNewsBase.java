//=======================================================================
//  ClassName : LiplisShortNewsBase
//  概要      : Liplisショートニュースインターフェース
//
//  Liplisシステム      
//  Copyright(c) 2010-2012 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Xml.LiplisShortNews;

import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;

import little.cute.renew.Msg.MsgShortNews;

public interface LiplisShortNewsBase {

	/// <summary>
    /// ショートニュースの取得
    /// </summary>
	public MsgShortNews getChatTalkResponse(InputStream is);
	
    /// <summary>
    /// loadXml
	/// XMLをロードする
    /// </summary>
	public MsgShortNews loadXml(XmlPullParser parser);
	
}
