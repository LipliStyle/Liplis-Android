//=======================================================================
//  ClassName : LiplisDefine
//  概要      	  : リプリスの定義
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Common;

public class LiplisDefine {

    ///=============================
    /// ボタン定義
    public static final String LIPLIS_CLICK_ACTION = "LIPLIS_CLICK_ACTION";
    public static final String LIPLIS_CLICK_ACTION_NEST = "LIPLIS_CLICK_ACTION_NEXT";
    public static final String LIPLIS_CLICK_ACTION_JUMP = "LIPLIS_CLICK_ACTION_JUMP";
    public static final String LIPLIS_CLICK_ACTION_SLEEP = "LIPLIS_CLICK_ACTION_SLEEP";
    public static final String LIPLIS_CLICK_ACTION_SETTING = "LIPLIS_CLICK_ACTION_SETTING";
    public static final String LIPLIS_CLICK_ACTION_BATTERY = "LIPLIS_CLICK_ACTION_BATTERY";
    public static final String LIPLIS_CLICK_ACTION_CLOCK = "LIPLIS_CLICK_ACTION_CLOCK";
    public static final String LIPLIS_CLICK_ACTION_CHAT_TALK = "LIPLIS_CLICK_ACTION_CHAT_TALK";		//ver4.5 2015/01/11
    //public static final String LIPLIS_UPDATE_ALARM = "LIPLIS_UPDATE_ALARM";

    public static final String LIPLIS_SETTING_START = "LIPLIS_SETTING_START";
    public static final String LIPLIS_SETTING_FINISH = "LIPLIS_SETTING_FINISH";

    ///=============================
    /// ニュースキューの保持数
    public static final int LPS_NEWS_QUEUE = 512;
    public static final int LPS_NEWS_QUEUE_HOLD_CNT = 25;
    public static final int LPS_NEWS_QUEUE_GET_CNT = 50;

    ///=============================
    /// 動作定義
	public static final int LIPLIS_UPDATE_RATE = 1000;
	public static final int LIPLIS_SMALL_UPDATE_RATE = 100;


    ///=============================
    /// ログ定義
	public static final String LOG_TAG = "LiplisAndroid";

    ///=============================
    /// キャラクターパッケージ定義
	public static final String CHAR_PACKAGE = "little.pretty";

    ///=============================
    /// URIスキーマ
    public static final String URI_SCHEME = "LiplisAndroidRenew";

    ///=============================
    /// プリファレンス名
    public static final String PREFS_NAME = "LiplisPrefs";

    ///=============================
    /// サイト
    public static final String SITE_MAIN 	= "http://liplis.mine.nu";																		//メイン
    public static final String SITE_HELP 	= "http://liplis.mine.nu/lipliswiki/webroot/?LiplisAndroid%20Manual";							//ヘルプ
    public static final String SITE_MARKET 	= "https://play.google.com/store/apps/details?id=little.cute.renew";						//マーケット
    public static final String SITE_MARKET_SMALL 	= "https://play.google.com/store/apps/details?id=little.cute.Smallapp";					//マーケット
    //public static final String SITE_MARKET 	= "https://market.android.com/details?id=little.cute";										//マーケット(旧)

    ///=============================
    /// ショートニュース定数
    public static final String API_SHORT_NEWS_TONE 		= "http://liplis.mine.nu/FileSystem/Xml/Tone/little.pretty.lilirenew.xml";										//トーンXMLL
//    public static final String API_SHORT_NEWS_URL_NEW 	= "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisWeb.aspx";					//ショートニュースAPI
//    public static final String API_SHORT_NEWS_URL_LSIT 	= "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisWebFx.aspx";					//ショートニュースリストAPI
    public static final String API_SHORT_NEWS_URL_NEW 	= "http://liplis.mine.nu/Clalis/v40/Liplis/ClalisForLiplisWeb.aspx";					//ショートニュースAPI
    public static final String API_SHORT_NEWS_URL_LSIT 	= "http://liplis.mine.nu/Clalis/v40/Liplis/ClalisForLiplisWebFx.aspx";					//ショートニュースリストAPI
    public static final String API_ONETIME_PASS		 	= "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisGetOnetimePass.aspx";			//ワンタイムパスワード要求API
    public static final String API_TWITTER_INFO_REGIST 	= "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisRegisterTwitterInfo.aspx";	//ツイッターユーザー登録API
    public static final String API_TWITTER_USER_ADD 		= "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisRegisterAddTwitterUser.aspx";	//ツイッターユーザー登録API
    public static final String API_TWITTER_USER_DEL 		= "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisRegisterDelTwitterUser.aspx";	//ツイッターユーザー削除API
    public static final String API_RSS_URL_ADD 	        = "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisRegisterAddRssUrl.aspx";		//RSS URL登録API
    public static final String API_RSS_URL_DEL 	        = "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisRegisterDelRssUrl.aspx";		//RSS URL削除API
    public static final String API_TWITTER_USER_LIST		= "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisSettingTwitterUserList.aspx";	//ツイッターユーザーリスト取得API
    public static final String API_RSS_URL_LIST	        = "http://liplis.mine.nu/Clalis/v31/Liplis/ClalisForLiplisSettingRssUrlList.aspx";		//RSSユーザーリスト取得API
    public static final String API_SETTING_LIST	        = "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisSettingGetSearchWord.aspx";	//話題検索設定リスト取得API
    public static final String API_SETTING_ADD 	        = "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisSettingAddSearchWord.aspx";	//話題検索設定追加API
    public static final String API_SETTING_DEL 	        = "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisSettingDelSearchWord.aspx";	//話題検索設定削除API
    public static final String API_TOPIC_SETTING 	        = "http://liplis.mine.nu/Clalis/v32/Liplis/ClalisForLiplisSettingTopicSetting.aspx";	//話題設定
    public static final String API_SHORT_NEWS_IN_URL 		= "http://liplis.mine.nu/clalis/v30/liplis/clalis.asmx/clalisShortNewsIn";				//ショートニュースインターナショナルAPI
    public static final String API_LIPLIS_ERROR 			= "http://liplis.mine.nu/Clalis/v20/Api.asmx/sendErr";									//エラー送信
    public static final String API_LIPLIS_CHAT 			= "http://liplis.mine.nu/Clalis/v40/Liplis/ClalisForLiplisTalk.aspx";					//おしゃべり応答

    ///=============================
    /// ツイッターキー
    public static final String TWITTER_OAUTH_CONSUMERKEY = "W1tQBXDr3pQu1atfIwp6A";													//トーンXMLL
    public static final String TWITTER_OAUTH_CONSUMERSECRET = "eTFat5surbln3MH7f0uIlwmpOcQdjlkyg7vUk90eG8";							//ショートニュースAPI

    ///=============================
    /// ポスト定数
    public static final String POST_USER_AGENT = "User-Agent";			//ユーザーエージェント
    public static final String POST_USER_AGENT_VAL = "Liplis Android";	//ユーザーエージェントバリュー
    public static final String POST_LANGAGE = "Accept-Language";			//ランゲージ
    public static final String POST_LANGAGE_VAL = "ja";					//ランゲージバリュー
    public static final String POST_RETURN_CHARCODE = "UTF-8";			//ポスト受信文字コード

    ///=============================
    /// メッセージ定義
    public static final String MSSAGE_BACK_CANCEL = "キャンセルの場合は、一度配置してから終了して下さい。";
    public static final String MSSAGE_CHAR_NOT_FOUND = "キャラクターがインストールされていません。";

    ///=============================
    /// せりふ定義
    public static final String SAY_ZZZ = "Zzz";

    ///=============================
    /// チャット送信コード
    public static final String CHAT_TALK_SENDMESSAGE = "CHAT_TALK_SENDMESSAGE";

    ///=============================
    /// プリファレンス定義名
    public static final String PREFS_SET_FLG 					= "liplisSetFlg";
    public static final String PREFS_NOW_SIZE					= "LiplisNowSize";
    public static final String PREFS_UID 						= "LiplisUID";
    public static final String PREFS_LANGUAGE 				= "LiplisLanguage";
    public static final String PREFS_MODE 					= "LiplisMode";
    public static final String PREFS_SPEED 					= "LiplisSpeed";
    public static final String PREFS_WINDOW_CLICK 			= "LiplisWindowClick";
    public static final String PREFS_BODY_CLICK 				= "LiplisBodyClick";
    public static final String PREFS_AUTO_SLEEP  				= "LiplisAutoSleep";
    public static final String PREFS_AUTO_WAIKUP  			= "LiplisAutoWaikup";
    public static final String PREFS_DISPLAY_ICON 			= "LiplisDisplayIcon";
    public static final String PREFS_WINDOW_CODE 				= "LiplisWindowCode";
    public static final String PREFS_WINDOW_DIS 				= "LiplisWindowDis";
    public static final String PREFS_VOICE		 			= "LiplisVoice";		//2013/05/01 ver3.4.0 ボイスの有効無効
    public static final String PREFS_VOICE_RATE				= "LiplisVoiceRate";	//2013/05/01 ver3.4.0 ボイスのレート
    public static final String PREFS_VOICE_PITCH 				= "LiplisVoicePitch";	//2013/05/01 ver3.4.0 ボイスのピッチ
    public static final String PREFS_HELTH 					= "lpsHealth";			//2013/05/01 ver3.6.0 健康状態

    public static final String PREFS_TOPIC_NEWS  				= "LiplisTopicNews";
    public static final String PREFS_TOPIC_2CH 				= "LiplisTopic2ch";
    public static final String PREFS_TOPIC_NICO 				= "LiplisTopicNico";
    public static final String PREFS_TOPIC_RSS 				= "LiplisTopicRss";
    public static final String PREFS_TOPIC_TWITTER 			= "LiplisTopicTw";
    public static final String PREFS_TOPIC_TWITTER_PUBLIC 	= "LiplisTopicTwPb";	//2013/09/21 ver3.5.0 ツイッターパブリックス
    public static final String PREFS_TOPIC_TWITTER_MY 		= "LiplisTopicTwMy";	//2013/09/21 ver3.5.0 ツイッターマイタイムライン
    public static final String PREFS_TOPIC_CHAR_MESSAGE 		= "LiplisTopicCharMsg"; //2013/09/21 ver3.5.0 ツイッターパブリックス

    public static final String PREFS_NEWS_RANGE				= "LiplisNewsRange";
    public static final String PREFS_NEWS_ALREADY_READ		= "LiplisNewsAlreadyRead";
    public static final String PREFS_TWITTER_MODE  			= "LiplisTwitterMode";
    public static final String PREFS_ROM_OUT 					= "LiplisRunOut"; 		//2013/10/07 ver3.5.0 話題が尽きた場合の振る舞い定義

    public static final String PREFS_TWITTER_TOKEN  			= "LiplisTwitterToken";
    public static final String PREFS_TWITTER_SECRET  			= "LiplisTwitterSecret";

    public static final String PREFS_LPS_STATUS 				= "LiplisStatus";
}
