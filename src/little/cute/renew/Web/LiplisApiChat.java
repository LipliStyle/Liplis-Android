//=======================================================================
//  ClassName : LiplisApiChat
//  概要      : Liplis話しかけ応答API
//
//	extends   : LiplisHttpPost
//	impliments:
//
//  LiplisAndroidシステム
//  2015/01/11 Liplis4.5 おしゃべり応答API対応
//
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Obj.Json.ResLpsChatResponse;
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoRss;
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoTw;
import little.cute.renew.Obj.Json.ResLpsTopicSearchWordList;
import little.cute.renew.Obj.Json.ResUserOnetimePass;
import little.cute.renew.Xml.LiplisResponse.LiplisResponse;
import little.cute.renew.Xml.LiplisShortNews.LiplisChatTalkResponseJson;
import little.cute.renew.Xml.LiplisShortNews.LiplisShortNewsIn;
import little.cute.renew.Xml.LiplisShortNews.LiplisShortNewsJpJson;
import little.cute.renew.Xml.LiplisShortNewsList.LiplisShortNewsListJpJson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LiplisApiChat extends LiplisHttpPost{

    ///=====================================
    /// 会話継続コンテキスト
    protected String mode;
    protected String context;

    ///====================================================================
    ///
    ///                      ショートニュース関連
    ///
    ///====================================================================
    public LiplisApiChat()
    {
    	this.mode = "";
        this.context = "";
    }

    ///====================================================================
    ///
    ///                      ショートニュース関連
    ///
    ///====================================================================

    /// <summary>
    /// getShortNews
	/// ver3.0 にて、言語の概念が取り入れられた。
	/// 日本語とそれ以外の言語で処理を分ける為にこちらのメソッドを呼ぶようにする
    /// </summary>
	public MsgShortNews apiPost(String uid, String toneUrl, String version, String sentence) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", uid));
        	nameValuePair.add(new BasicNameValuePair("tone", toneUrl));
        	nameValuePair.add(new BasicNameValuePair("version", version));
        	nameValuePair.add(new BasicNameValuePair("sentence", sentence));
        	nameValuePair.add(new BasicNameValuePair("mode", this.mode));
        	nameValuePair.add(new BasicNameValuePair("context", this.context));

        	//インターフェースの宣言
        	String url = LiplisDefine.API_LIPLIS_CHAT;

        	//結果変換ユーティる
        	LiplisChatTalkResponseJson responseUtil = new LiplisChatTalkResponseJson();

        	//結果取得
        	ResLpsChatResponse rcr = responseUtil.getChatTalkResponseRes(post(url,nameValuePair).getEntity().getContent());

        	//結果をリプリスおしゃべりの共通クラスに変換
        	MsgShortNews msn = responseUtil.itemConvert(rcr);

        	if(rcr.opList != null && rcr.opList.size() == 2)
        	{
            	this.context = rcr.opList.get(0);
            	this.mode = rcr.opList.get(1);
        	}

        	//結果を返す
        	return msn;

	    } catch (MalformedURLException e) {
	        //System.err.println("Invalid URL format: " + urlString);
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	        return null;
	    }
	}
}