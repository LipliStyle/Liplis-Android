package little.cute.Xml;

import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;


public class LiplisAndroidInfo extends XmlReadListPullParser {
	/// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisAndroidInfo(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public String getAndroidInfo(InputStream is)
	{
		return loadXml(getXmlFromSt(is));
	}
	
    /// <summary>
    /// loadXml
	/// XMLをロードする
    /// </summary>
	private String loadXml(XmlPullParser parser)
	{
		return loadXmlStr(parser);
	}
}
