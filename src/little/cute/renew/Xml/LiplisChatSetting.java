//=======================================================================
//  ClassName : LiplisChatSetting
//  概要      : Liplisチャット設定
//
//2013/09/15 ver3.4.4 バージョン 追加
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Obj.ObjLiplisChat;

public class LiplisChatSetting extends XmlReadListPullParser {
	///=====================================
    /// タグ定義
	private static String TAG_CHATDESCRIPTION = "chatDiscription";
	private static String TAG_VERSION = "version";
	private static String TAG_NAME = "name";
	private static String TAG_TYPE = "type";
	private static String TAG_DESCRIPTION = "discription";
	private static String TAG_EMOTION = "emotion";
	private static String TAG_PREREQUISITE = "prerequisite";

	/// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisChatSetting(){	}

    /// <summary>
    /// チャットオブジェクトの取得
    /// </summary>
	public ObjLiplisChat getChatSetting(InputStream is)
	{
		return loadXml(getXmlFromSt(is));
	}
	public ObjLiplisChat getChatSetting(XmlPullParser parser)
	{
		return loadXml(parser);
	}

	/// <summary>
    /// loadXmlOld
	/// XMLをロードする(プロトタイプAPI使用)
    /// </summary>
	private ObjLiplisChat loadXml(XmlPullParser parser)
	{
		ObjLiplisChat obj = new ObjLiplisChat();
        int eventType;
        try {
			while((eventType = parser.next()) != XmlPullParser.END_DOCUMENT){
			    //chatDiscriptionタグを見つけたらaddItemへ
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_CHATDESCRIPTION)){
			        addItem(parser,obj);
			    }

			    //versionタグを見つけたらaddItemへ
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_VERSION)){
			        eventType = parser.next();

			        if(eventType == XmlPullParser.TEXT){
			        	obj.version = parser.getText();
			        }
			    }
			}
			return obj;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			System.out.println(e);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return new ObjLiplisChat();
		}
	}

    /// <summary>
    /// addItem
	/// アイテムアッド
    /// </summary>
    private void addItem(XmlPullParser parser, ObjLiplisChat obj)
	    throws XmlPullParserException, IOException{

		while(true){
		    int eventType = parser.next();

		    //chatdescriptionのエンドタグを見つけたらループ終了
		    if(eventType == XmlPullParser.END_TAG  && parser.getName().equals(TAG_CHATDESCRIPTION)){
		        break;
		    }

		    //nameの取り出し
		    if(eventType == XmlPullParser.START_TAG && parser.getName().equals(TAG_NAME)){
		        eventType = parser.next();

		        if(eventType == XmlPullParser.TEXT){
		        	obj.nameList.add(parser.getText());
		        }
		    }

		    //タイプの取り出し
		    if(eventType == XmlPullParser.START_TAG && parser.getName().equals(TAG_TYPE)){
		        eventType = parser.next();

		        if(eventType == XmlPullParser.TEXT){
		        	obj.typeList.add(parser.getText());
		        }
		    }

		    //ディスクリプションの取り出し
		    if(eventType == XmlPullParser.START_TAG && parser.getName().equals(TAG_DESCRIPTION)){
		        eventType = parser.next();

		        if(eventType == XmlPullParser.TEXT){
		        	obj.discriptionList.add(parser.getText());
		        }
		    }

		    //エモーションの取り出し
		    if(eventType == XmlPullParser.START_TAG && parser.getName().equals(TAG_EMOTION)){
		        eventType = parser.next();

		        if(eventType == XmlPullParser.TEXT){
		        	obj.emotionList.add(LiplisUtil.parseInt(parser.getText()));
		        }
		    }

		    //条件の取り出し
		    if(eventType == XmlPullParser.START_TAG && parser.getName().equals(TAG_PREREQUISITE)){
		        eventType = parser.next();

		        if(eventType == XmlPullParser.TEXT){
		        	obj.prerequisiteList.add(parser.getText());
		        }
		    }
		}
	}

}
