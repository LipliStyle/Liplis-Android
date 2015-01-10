//=======================================================================
//  ClassName : LiplisApi
//  概要      : リプリスWebAPIラッパー
//
//	extends   : LiplisHttpPost
//	impliments:
//
//  LiplisAndroidシステム
//  2014/04/28 Liplis4.0 Clalis4.0対応
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
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoRss;
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoTw;
import little.cute.renew.Obj.Json.ResLpsTopicSearchWordList;
import little.cute.renew.Obj.Json.ResUserOnetimePass;
import little.cute.renew.Xml.LiplisResponse.LiplisResponse;
import little.cute.renew.Xml.LiplisShortNews.LiplisShortNewsIn;
import little.cute.renew.Xml.LiplisShortNews.LiplisShortNewsJpJson;
import little.cute.renew.Xml.LiplisShortNewsList.LiplisShortNewsListJpJson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import android.util.Log;

public class LiplisApi extends LiplisHttpPost{

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
	public static MsgShortNews getShortNews(List<NameValuePair> nameValuePair, int lngCode) {
        try
        {
        	//インターフェースの宣言
        	//LiplisShortNewsBase xml;
        	String url = LiplisDefine.API_SHORT_NEWS_URL_NEW;

        	//ランゲージチェック
        	if(lngCode == 0)
        	{
        		//2013/03/01 ver3.1.0 Json方式に変更
        		//return new LiplisShortNewsJp().getShortNews(post(url,nameValuePair).getEntity().getContent());
        		return new LiplisShortNewsJpJson().getShortNews(post(url,nameValuePair).getEntity().getContent());
        	}
        	else
        	{
        		//2013/03/01 ver3.1.0現在、こちらのロジックには来ない。
        		url = LiplisDefine.API_SHORT_NEWS_IN_URL;
        		return  new LiplisShortNewsIn().getShortNews(post(url,nameValuePair).getEntity().getContent());
        	}

        	//ニュースの取得
            //return xml.getShortNews(post(url,nameValuePair).getEntity().getContent());

	    } catch (MalformedURLException e) {
	        //System.err.println("Invalid URL format: " + urlString);
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	        return null;
	    }
	}

    /// <summary>
    /// getShortNews
	/// ver3.2 ニュースの一括取得の実装
    /// </summary>
	public static List<MsgShortNews> getShortNewsList(List<NameValuePair> nameValuePair, int lngCode) {
        try
        {
        	//ランゲージチェック(互換性保持と将来複数言語実装に備えてlngCodeは引数として設定しておく)
        	//if(lngCode == 0){}else{}

        	return new LiplisShortNewsListJpJson().getShortNewsList(post(LiplisDefine.API_SHORT_NEWS_URL_LSIT,nameValuePair).getEntity().getContent());

	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	        return null;
	    }
	}

    ///====================================================================
    ///
    ///                         Androidシステム
    ///
    ///====================================================================

	/// <summary>
    /// liplisSendError
	/// リプリスエラー送付
    /// </summary>
	public static void liplisSendError(String Uid, String errorCode, String errorDiscription)
	{
		try
		{
			//ショートニュース
	    	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
	    	nameValuePair.add( new BasicNameValuePair("uid",Uid));
	    	nameValuePair.add( new BasicNameValuePair("errorCode",errorCode));
	    	nameValuePair.add( new BasicNameValuePair("errorDiscription",errorDiscription));

	    	//ポストしゅる
	        post(LiplisDefine.API_LIPLIS_ERROR,nameValuePair);
		}
		catch(Exception e)
		{
			//エラー送信でエラーが発生しても何もしない
		}
	}

    ///====================================================================
    ///
    ///                   ワンタイムパスワードの取得要求
    ///
    ///====================================================================

    /// <summary>
    /// registerTwitterUser
	/// ver3.3.6 ツイッター情報の登録機能の実装
    /// </summary>
	public static String getOnetimePass(String userId) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));

        	//APIを実行してワンタイムパスワードを得る
        	return new Gson().fromJson(LiplisUtil.inputStreemToString(post(LiplisDefine.API_ONETIME_PASS, nameValuePair).getEntity().getContent()), ResUserOnetimePass.class).oneTimePass;

	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "";
	    }
	}

    ///====================================================================
    ///
    ///                     ツイッターユーザーを登録する
    ///
    ///====================================================================

    /// <summary>
    /// registerTwitterUser
	/// ver3.3.6 ツイッター情報の登録機能の実装
    /// </summary>
	public static boolean registerUser(String userId,String token, String secret) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("token", token));
        	nameValuePair.add(new BasicNameValuePair("secret", secret));

        	//APIを実行してリターンコードを得る
        	String returnCode = LiplisResponse.getResponse(post(LiplisDefine.API_TWITTER_INFO_REGIST, nameValuePair).getEntity().getContent());

        	//リターンコードを判定する
        	if(returnCode.equals("0") || returnCode.equals("1"))
        	{
        		//成功
        		return true;
        	}
        	else
        	{
        		//失敗
        		return false;
        	}
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return false;
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return false;
	    }
	}

    /// <summary>
    /// registerTwitterUser
	/// ver3.3.6 ツイッターユーザーの登録機能の実装
	/// 廃止予定
    /// </summary>
//	public static List<String> registerTwitterUser(String userId, String token, String secret, String userList) {
//        try
//        {
//        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
//        	nameValuePair.add(new BasicNameValuePair("userid", userId));
//        	nameValuePair.add(new BasicNameValuePair("token", token));
//        	nameValuePair.add(new BasicNameValuePair("secret", secret));
//        	nameValuePair.add(new BasicNameValuePair("userlist", userList));
//
//        	//APIを実行してリターンコードを得る
//        	String response = LiplisResponse.getResponse(post(LiplisDefine.API_TWITTER_USER_REGIST, nameValuePair).getEntity().getContent());
//
//        	//結果をリストにする
//        	List<String> responseList = Arrays.asList(response.split(","));
//
//        	//リターンコードを判定する
//        	if(responseList.size() > 1)
//        	{
//        		//成功
//        		return responseList;
//        	}
//        	else if(responseList.size() == 1)
//        	{
//        		if(responseList.get(0).equals("0"))
//        		{
//        			return responseList;
//        		}
//        		else
//        		{
//        			return new ArrayList<String>();
//        		}
//        	}
//        	else
//        	{
//        		//失敗
//        		return new ArrayList<String>();
//        	}
//	    } catch (MalformedURLException e) {
//	    	Log.d("LiplisApi",e.toString());
//	    	return new ArrayList<String>();
//	    } catch (IOException e) {
//	    	Log.d("LiplisApi",e.toString());
//	    	return new ArrayList<String>();
//	    }
//	}


    /// <summary>
    /// addTwitterUser
	/// ver3.3.7 ツイッターユーザーを登録する
    /// </summary>
	public static String addTwitterUser(String userId, String token, String secret, String addUser) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("token", token));
        	nameValuePair.add(new BasicNameValuePair("secret", secret));
        	nameValuePair.add(new BasicNameValuePair("addUser", addUser));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_TWITTER_USER_ADD, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}

    /// <summary>
    /// delTwitterUser
	/// ver3.3.7 ツイッターユーザーを削除するする
    /// </summary>
	public static String delTwitterUser(String userId, String token, String secret, String delUser) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("token", token));
        	nameValuePair.add(new BasicNameValuePair("secret", secret));
        	nameValuePair.add(new BasicNameValuePair("delUser", delUser));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_TWITTER_USER_DEL, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}


    /// <summary>
    /// getTwitterUserList
	/// ver3.3.7 ツイッターユーザーリストを取得する
    /// </summary>
	public static ResLpsLoginRegisterInfoTw getTwitterUserList(String userId) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));

        	return new Gson().fromJson(LiplisUtil.inputStreemToString(post(LiplisDefine.API_TWITTER_USER_LIST, nameValuePair).getEntity().getContent()), ResLpsLoginRegisterInfoTw.class);
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsLoginRegisterInfoTw();
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsLoginRegisterInfoTw();
	    }
	}





    /// <summary>
    /// getRssUrlList
	/// ver3.3.7 RSSURLを登録する
    /// </summary>
	public static String addRssUrl(String userId, String addRssUrl) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("addRssUrl", addRssUrl));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_RSS_URL_ADD, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}

    /// <summary>
    /// getRssUrlList
	/// ver3.3.7 RSSURLを削除するする
    /// </summary>
	public static String delRssUrl(String userId, String addRssUrl) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("addRssUrl", addRssUrl));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_RSS_URL_DEL, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}


    /// <summary>
    /// getRssUrlList
	/// ver3.3.7 RSSURLリストを取得する
    /// </summary>
	public static ResLpsLoginRegisterInfoRss getRssUrlList(String userId) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));

        	return new Gson().fromJson(LiplisUtil.inputStreemToString(post(LiplisDefine.API_RSS_URL_LIST, nameValuePair).getEntity().getContent()), ResLpsLoginRegisterInfoRss.class);
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsLoginRegisterInfoRss();
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsLoginRegisterInfoRss();
	    }
	}


    /// <summary>
    /// addSearchWord
	/// ver3.5.0 検索設定を登録する
    /// </summary>
	public static String addSearchWord(String userId, String topicId, String word, String flgEnable) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("topicId", topicId));
        	nameValuePair.add(new BasicNameValuePair("word", URLEncoder.encode(word,"utf-8")));
        	nameValuePair.add(new BasicNameValuePair("flgEnable", flgEnable));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_SETTING_ADD, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}

    /// <summary>
    /// delSearchWord
	/// ver3.5.0 検索設定を削除するする
    /// </summary>
	public static String delSearchWord(String userId,String topicId, String word) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));
        	nameValuePair.add(new BasicNameValuePair("topicId", topicId));
        	nameValuePair.add(new BasicNameValuePair("word", URLEncoder.encode(word,"utf-8")));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_SETTING_DEL, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    }
	}

    /// <summary>
    /// ResLpsTopicSearchWordList
	/// ver3.5.0 検索設定リストを取得する
    /// </summary>
	public static ResLpsTopicSearchWordList getSearchSettingList(String userId) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userid", userId));

        	return new Gson().fromJson(LiplisUtil.inputStreemToString(post(LiplisDefine.API_SETTING_LIST, nameValuePair).getEntity().getContent()), ResLpsTopicSearchWordList.class);
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsTopicSearchWordList();
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return new ResLpsTopicSearchWordList();
	    }
	}

    /// <summary>
    /// saveTopicSetting
	/// ver3.5.0 話題設定を登録する
    /// </summary>
	public static String saveTopicSetting(String userId, String range,String already, String f_ne, String f_2c, String f_ni, String f_rs, String f_tp, String f_tm, String f_tr, String f_tmode, String f_runout) {
        try
        {
        	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        	nameValuePair.add(new BasicNameValuePair("userId", userId));
        	nameValuePair.add(new BasicNameValuePair("range", range));
        	nameValuePair.add(new BasicNameValuePair("already", already));
        	nameValuePair.add(new BasicNameValuePair("f_ne", f_ne));
        	nameValuePair.add(new BasicNameValuePair("f_2c", f_2c));
        	nameValuePair.add(new BasicNameValuePair("f_ni", f_ni));
        	nameValuePair.add(new BasicNameValuePair("f_rs", f_rs));
        	nameValuePair.add(new BasicNameValuePair("f_tp", f_tp));
        	nameValuePair.add(new BasicNameValuePair("f_tm", f_tm));
        	nameValuePair.add(new BasicNameValuePair("f_tr", f_tr));
        	nameValuePair.add(new BasicNameValuePair("f_tmode", f_tmode));
        	nameValuePair.add(new BasicNameValuePair("f_runout", f_runout));

        	return LiplisResponse.getResponse(post(LiplisDefine.API_TOPIC_SETTING, nameValuePair).getEntity().getContent());
	    } catch (MalformedURLException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (IOException e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
	    } catch (Exception e) {
	    	Log.d("LiplisApi",e.toString());
	    	return "-1";
        }
	}
}
