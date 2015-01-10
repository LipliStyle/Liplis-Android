//=======================================================================
//  ClassName : LiplisShortNewsJpJson
//  概要      : LiplisショートニュースJSON用
//
//
//2013/03/02 ver3.3.0 LiplisNewsの非同期取得メソッドを変更
//           本APIで一括取得する
//2013/03/23 ver3.3.5 API変更に伴い、インターフェース変更
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml.LiplisShortNewsList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Obj.Json.ObjLeafAndEmotion;
import little.cute.renew.Obj.Json.ResLpsShortNewsJson;
import little.cute.renew.Obj.Json.ResLpsShortNewsListJson;

import org.xmlpull.v1.XmlPullParser;
import com.google.gson.Gson;

public class LiplisShortNewsListJpJson {


    /// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisShortNewsListJpJson(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public List<MsgShortNews> getShortNewsList(InputStream is)
	{
		try
		{
			if(is == null)
			{
				return new ArrayList<MsgShortNews>();
			}

			//取得したインプットストリームをストリングに変換してメソッドに通す
			return loadJson(LiplisUtil.inputStreemToString(is));
		}
		catch(IOException ex)
		{
			return new ArrayList<MsgShortNews>();
		}

	}


    /// <summary>
    /// loadJson
	/// JSONをロードする
    /// </summary>
	public List<MsgShortNews> loadJson(String jsonStr)
	{
		//GSONを使って取得結果をResLpsShortNewsJsonに落としこむ
		return itemConvert(new Gson().fromJson(jsonStr, ResLpsShortNewsListJson.class));
	}

    /// <summary>
    /// itemConvert
	/// ストリングをメッセージリストに変換する
	/// URL;str1,emotion1,point1;str2,emotion2,point2;・・・
	/// と表されている出力結果を分解する。
    /// </summary>
    private List<MsgShortNews> itemConvert(ResLpsShortNewsListJson data)
    {
    	List<MsgShortNews> res = new ArrayList<MsgShortNews>();

    	//ニュースリストを回す
    	for(ResLpsShortNewsJson rsn : data.lstNews)
    	{
    		//ショートニュースオブジェクトを作成。rsn空変換する
        	MsgShortNews msn = new MsgShortNews();
        	msn.url = rsn.url;

        	//コロンで分割
        	String[] leafEmotionPointList = rsn.result.split(";");

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

        	//結果をリストに入れる
        	res.add(msn);
    	}

    	//リストを返す
    	return res;
    }

    /// <summary>
    /// loadXml
	/// 使用しない
    /// </summary>
	public ObjLeafAndEmotion loadXml(XmlPullParser parser) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



}
