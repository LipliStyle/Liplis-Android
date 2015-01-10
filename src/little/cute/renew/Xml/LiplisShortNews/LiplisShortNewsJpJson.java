//=======================================================================
//  ClassName : LiplisShortNewsJpJson
//  概要      : LiplisショートニュースJSON用
//
//
//2013/03/23 ver3.3.5 API変更に伴い、インターフェース変更
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml.LiplisShortNews;

import java.io.IOException;
import java.io.InputStream;

import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Obj.Json.ObjLeafAndEmotion;
import little.cute.renew.Obj.Json.ResLpsShortNewsJson;

import org.xmlpull.v1.XmlPullParser;

import com.google.gson.Gson;

public class LiplisShortNewsJpJson implements LiplisShortNewsBase{

    /// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisShortNewsJpJson(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public MsgShortNews getShortNews(InputStream is)
	{
		try
		{
			if(is == null)
			{
				return new MsgShortNews();
			}

			//取得したインプットストリームをストリングに変換してメソッドに通す
			return loadJson(LiplisUtil.inputStreemToString(is));
		}
		catch(IOException ex)
		{
			return new MsgShortNews();
		}

	}


    /// <summary>
    /// loadJson
	/// JSONをロードする
    /// </summary>
	public MsgShortNews loadJson(String jsonStr)
	{
		//GSONを使って取得結果をResLpsShortNewsJsonに落としこむ
		return itemConvert(new Gson().fromJson(jsonStr, ResLpsShortNewsJson.class));
	}

    /// <summary>
    /// itemConvert
	/// ストリングをメッセージリストに変換する
	/// URL;str1,emotion1,point1;str2,emotion2,point2;・・・
	/// と表されている出力結果を分解する。
    /// </summary>
    private MsgShortNews itemConvert(ResLpsShortNewsJson data)
    {
    	MsgShortNews msn = new MsgShortNews();
    	msn.url = data.url;

    	//コロンで分割
    	String[] leafEmotionPointList = data.result.split(";");

    	//データを回してショートニュースメッセージを作成する
    	for(String leafEmotionPoint : leafEmotionPointList)
    	{
    		ObjLeafAndEmotion oae = LiplisUtil.getOae(leafEmotionPoint.split(","));

    		if(oae.name.equals("EOS"))
			{
    			break;
			}
    		msn.nameList.add(oae.name);
    		msn.pointList.add(oae.point);
    		msn.emotionList.add(oae.emotion);
    	}

    	return msn;
    }

    /// <summary>
    /// loadXml
	/// 使用しない
    /// </summary>
	public MsgShortNews loadXml(XmlPullParser parser) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
