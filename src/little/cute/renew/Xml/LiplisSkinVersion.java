package little.cute.renew.Xml;

import java.io.IOException;
import java.io.InputStream;

import little.cute.renew.Obj.ObjLiplisVersion;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LiplisSkinVersion extends XmlReadListPullParser {
	///=====================================
    /// タグ定義
	private static String TAG_SKIN_VERSION = "skin";
	private static String TAG_Liplis_VERSION = "min";
	private static String TAG_URL = "url";
	private static String TAG_APK_URL = "apkUrl";


	/// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisSkinVersion(){	}

    /// <summary>
    /// バージョンオブジェクトの取得
    /// </summary>
	public ObjLiplisVersion getVersion(InputStream is)
	{
		return loadXml(getXmlFromSt(is));
	}

	public ObjLiplisVersion getVersion(XmlPullParser parser)
	{
		return loadXml(parser);
	}

    /// <summary>
    /// 最新バージョンのオブジェクトを取得する
	/// URLには、バージョンXMLのURLタグの値を指定する。
    /// </summary>
	public ObjLiplisVersion getNewVersion(String url)
	{
		return loadXml(getXmlFromUrl(url));
	}


	/// <summary>
    /// loadXmlOld
	/// XMLをロードする(プロトタイプAPI使用)
    /// </summary>
	private ObjLiplisVersion loadXml(XmlPullParser parser)
	{
		ObjLiplisVersion obj = new ObjLiplisVersion();
        int eventType;
        try {
			while((eventType = parser.next()) != XmlPullParser.END_DOCUMENT){
			    //バージョンを取得する
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_SKIN_VERSION)){
			    	eventType = parser.next();

			        if(eventType == XmlPullParser.TEXT){
			        	obj.skinVersion = parser.getText();
			        }
			    }

			    //Liplis最小バージョンを取得する
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_Liplis_VERSION)){
			    	eventType = parser.next();

			        if(eventType == XmlPullParser.TEXT){
			        	obj.liplisMinVersion = parser.getText();
			        }
			    }

			    //URLを取得する
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_URL)){
			    	eventType = parser.next();

			        if(eventType == XmlPullParser.TEXT){
			        	obj.url = parser.getText();
			        }
			    }

			    //APK URLを取得する
			    if(eventType == XmlPullParser.START_TAG  && parser.getName().equals(TAG_APK_URL)){
			    	eventType = parser.next();

			        if(eventType == XmlPullParser.TEXT){
			        	obj.apkUrl = parser.getText();
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
			return new ObjLiplisVersion();
		}
	}
}
