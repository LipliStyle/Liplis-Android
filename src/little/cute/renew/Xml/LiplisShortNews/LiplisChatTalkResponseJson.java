//=======================================================================
//  ClassName : LiplisChatTalkResponseJson
//  概要      : Liplisチャットトーク応答JSON用
//
//
//  Liplisシステム
//  Copyright(c) 2010-2015 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml.LiplisShortNews;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Obj.Json.ObjLeafAndEmotion;
import little.cute.renew.Obj.Json.ResLpsChatResponse;

import org.xmlpull.v1.XmlPullParser;

import com.google.gson.Gson;

public class LiplisChatTalkResponseJson implements LiplisShortNewsBase{

    /// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisChatTalkResponseJson(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public ResLpsChatResponse getChatTalkResponseRes(InputStream is)
	{
		try
		{
			if(is == null)
			{
				return new ResLpsChatResponse();
			}

			//取得したインプットストリームをストリングに変換してメソッドに通す
			return new Gson().fromJson(LiplisUtil.inputStreemToString(is), ResLpsChatResponse.class);
		}
		catch(IOException ex)
		{
			return new ResLpsChatResponse();
		}

	}


    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public MsgShortNews getChatTalkResponse(InputStream is)
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
		return itemConvert(new Gson().fromJson(jsonStr, ResLpsChatResponse.class));
	}

    /// <summary>
    /// itemConvert
	/// ストリングをメッセージリストに変換する
	/// URL;str1,emotion1,point1;str2,emotion2,point2;・・・
	/// と表されている出力結果を分解する。
    /// </summary>
    public MsgShortNews itemConvert(ResLpsChatResponse data)
    {
    	MsgShortNews msn = new MsgShortNews();
    	msn.url = data.url;

    	//コロンで分割
    	List<String> lstEp = new ArrayList<String>();

    	//データ取得
    	for(String line : data.descriptionList)
    	{
    		lstEp.addAll(Arrays.asList(line.split(";")));
    	}

    	//データを回してショートニュースメッセージを作成する
    	for(String leafEmotionPoint : lstEp)
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
