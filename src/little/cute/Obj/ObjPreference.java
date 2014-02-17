//=======================================================================
//  ClassName : ObjPreference
//  概要      : プリファレンスオブジェクト
//
//	extends   :
//	impliments:
//
//2013/04/29 ver3.3.7 Twitter,RSS対応
//2013/10/28 ver3.6.0 健康状態追加
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Obj;

import java.util.UUID;

import little.cute.R;
import little.cute.Common.LiplisDefine;
import android.content.Context;
import android.content.SharedPreferences;

public class ObjPreference {

	///=============================
    /// プリファレンス値定義
	private String lpsUid;
	private int lpsLanguage;
	private int lpsMode;
	private int lpsActive;
    private int lpsSpeed;
    private int lpsWindowClick;
    private int lpsDpSleep;
    private int lpsAutoSleep;
    private int lpsAutoWaikup;
    private int lpsWindowColor;
    private int lpsDisplayIcon;
    private int lpsWindowDis;
    private int lpsNewsRange;			//3.3.7対応
    private int lpsNewsAlready;		//3.5.0対応
    private int lpsRunOut;				//3.5.0対応 話題枯渇時の振る舞い
    private int lpsVoice;				//3.4.0 音声化対応
    private int lpsVoiceRate;			//3.4.0 音声化対応
    private int lpsVoicePitch;			//3.4.0 音声化対応
    private int lpsHealth;				//3.6.0 健康状態

	///=============================
    /// ニュース
    private int lpsTopicNews;
    private int lpsTopic2ch;
    private int lpsTopicNico;
    private int lpsTopicRss;			//3.3.7対応
    private int lpsTopicTwitter;		//3.3.7対応
    private int lpsTopicTwitterPu;		//3.5.0対応
    private int lpsTopicTwitterMy;		//3.5.0対応
    private int lpsTopicTwitterMode;	//3.5.0対応
    private int lpsTopicCharMsg;		//3.5.0対応

    ///=============================
    /// 対応設定値
    private int lpsInterval = 10;
    private int lpsReftesh = 100;
    private int lpsIconCloseCnt = 0;
	private int windowCode = 0;

    ///=============================
    /// Liplis状態
	/// 0:ノーマル,1:睡眠,2:時計
	private int lpsStatus = 0;			//3.4.1 対応

    ///====================================================================
    ///
    ///                             主処理
    ///
    ///====================================================================

    /// <summary>
    /// ObjPreference
    /// コンストラクター
    /// </summary>
    public ObjPreference(Context context)
    {
    	getPreferenceData(context);
    }

    /// <summary>
    /// checPreferenceData
    /// プリファレンスデータをチェックする
    /// </summary>
    public int checPreferenceData(Context context)
    {
    	//プリファレンス読み出し
    	SharedPreferences config = context.getSharedPreferences(LiplisDefine.PREFS_NAME, 1);

    	//更新フラグの取得
    	return config.getInt(LiplisDefine.PREFS_SET_FLG, 0);
    }

    /// <summary>
    /// checPreferenceData
    /// プリファレンスデータを取得するする
    /// </summary>
    public void getPreferenceData(Context context)
    {
    	//プリファレンス読み出し
    	SharedPreferences config = context.getSharedPreferences(LiplisDefine.PREFS_NAME, 1);
    	setUid(config.getString(LiplisDefine.PREFS_UID, UUID.randomUUID().toString()));
    	setLanguage(config.getInt(LiplisDefine.PREFS_LANGUAGE, 0));
    	setMode(config.getInt(LiplisDefine.PREFS_MODE, 0));
    	setSpeed(config.getInt(LiplisDefine.PREFS_SPEED, 0));
    	setLinkCl(config.getInt(LiplisDefine.PREFS_WINDOW_CLICK, 0));
    	setAutoSleep(config.getInt(LiplisDefine.PREFS_AUTO_SLEEP, 1));
    	setAutoWaikup(config.getInt(LiplisDefine.PREFS_AUTO_WAIKUP, 0));
    	setDisplayIcon(config.getInt(LiplisDefine.PREFS_DISPLAY_ICON, 0));
    	setWindowColor(config.getInt(LiplisDefine.PREFS_WINDOW_CODE, 0));
    	setWindowDis(config.getInt(LiplisDefine.PREFS_WINDOW_DIS, 0));
    	setNewsRange(config.getInt(LiplisDefine.PREFS_NEWS_RANGE, 1));
    	setNewsAlready(config.getInt(LiplisDefine.PREFS_NEWS_ALREADY_READ, 0));
    	setRunOut(config.getInt(LiplisDefine.PREFS_ROM_OUT, 0));
    	setVoice(config.getInt(LiplisDefine.PREFS_VOICE, 0));					//ver3.4.0 音声化対応
    	setVoiceRate(config.getInt(LiplisDefine.PREFS_VOICE_RATE, 50));			//ver3.4.0 音声化対応
    	setVoicePitch(config.getInt(LiplisDefine.PREFS_VOICE_PITCH, 50));		//ver3.4.0 音声化対応
    	setHelth(config.getInt(LiplisDefine.PREFS_HELTH, 1));					//ver3.6.0 健康状態対応

    	setTopicNews(config.getInt(LiplisDefine.PREFS_TOPIC_NEWS, 1));
    	setTopic2ch(config.getInt(LiplisDefine.PREFS_TOPIC_2CH, 1));
    	setTopicNico(config.getInt(LiplisDefine.PREFS_TOPIC_NICO, 1));
    	setTopicRss(config.getInt(LiplisDefine.PREFS_TOPIC_RSS, 0));
    	setTopicTwitter(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER, 0));
    	setTopicTwitterPu(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_PUBLIC, 0));
    	setTopicTwitterMy(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_MY, 0));
    	setTopicTwitterMode(config.getInt(LiplisDefine.PREFS_TWITTER_MODE, 0));
    	setTopicCharMsg(config.getInt(LiplisDefine.PREFS_TOPIC_CHAR_MESSAGE, 0));

    	setLpsStatus(config.getInt(LiplisDefine.PREFS_LPS_STATUS, 0));			//3.4.1 おやすみ状態時、復帰しないように対応

    	//読み取り済みにしておく
    	setReadFlg(context);
    }

    /// <summary>
    /// setReadFlg
    /// 更新フラグを0にする
    /// </summary>
    private void setReadFlg(Context context)
    {
        //プリファレンスの取得
        final SharedPreferences config = context.getSharedPreferences(LiplisDefine.PREFS_NAME, 1);

        //プリファレンスの呼び出し
        SharedPreferences.Editor configEditor = config.edit();

        //フラグを寝かす
        configEditor.putInt(LiplisDefine.PREFS_SET_FLG, 0);

        //変更をコミット
        configEditor.commit();
    }

    /// <summary>
    /// updateLpsStuts
    /// リプリスステータスを更新する
    /// </summary>
    public void updateLpsStuts(Context context, int status)
    {
        //プリファレンスの取得
        final SharedPreferences config = context.getSharedPreferences(LiplisDefine.PREFS_NAME, 1);

        //プリファレンスの呼び出し
        SharedPreferences.Editor configEditor = config.edit();

        //フラグを寝かす
        configEditor.putInt(LiplisDefine.PREFS_LPS_STATUS, status);

        //変更をコミット
        configEditor.commit();
    }



    ///====================================================================
    ///
    ///                     プリファレンス変換処理
    ///
    ///====================================================================

    /// <summary>
    /// setUid
	/// ユーザーIDをセットする
    /// </summary>
    private void setUid(String uid)
    {
    	this.lpsUid = uid;
    }

    /// <summary>
    /// setMode
	/// おしゃべり : 0	モードデフォルト
    /// ふつう     : 1
	/// ひかえめ   : 2	デフォルト
	/// 無口       : 3
	/// 時計＆バッテリー : 4
    /// </summary>
    private void setMode(int mode)
    {
    	this.lpsMode = mode;
    	if(mode == 0)		{this.lpsInterval = 10; this.lpsIconCloseCnt = 2;}
    	else if(mode == 1)	{this.lpsInterval = 30; this.lpsIconCloseCnt = 1;}
    	else				{this.lpsInterval = 60; this.lpsIconCloseCnt = 1;}
    }

    /// <summary>
    /// setSpeed
	///  早口     : 0
	///  ふつう   : 1
	///  ゆっくり : 2
    ///  エコ 	  : 2
    /// </summary>
    private void setSpeed(int speed)
    {
    	this.lpsSpeed = speed;
    	if(speed == 0)		{this.lpsReftesh = 15;}
    	else if(speed == 1)	{this.lpsReftesh = 10;}
    	else if(speed == 2){this.lpsReftesh = 5;}
    	else 				{this.lpsReftesh = 0;}
    }

    /// <summary>
    /// setLinkCl
	///  何もしないしない     : 0
	///  リンクジャンプする   : 1
    ///  設定画面を開く       : 2
    /// </summary>
    private void setLinkCl(int lc)
    {
    	this.lpsWindowClick = lc;
    }

    /// <summary>
    /// setAutoSleep
	///  オートスリープしない     : 0
	///  オートスリープする       : 1
    /// </summary>
    private void setAutoSleep(int autoSleep)
    {
    	this.lpsAutoSleep = autoSleep;
    }

    /// <summary>
    /// AutoWaikup
	///  オートウェイクアップしない     : 0
	///  オートウェイクアップする       : 1
    /// </summary>
    private void setAutoWaikup(int autoWaikup)
    {
    	this.lpsAutoWaikup = autoWaikup;
    }


	/// <summary>
    /// setDisplayIcon
	///  常にアイコン表示しない     : 0
	///  常にアイコン表示する       : 1
    /// </summary>
    private void setDisplayIcon(int displayIcon)
    {
    	this.lpsDisplayIcon = displayIcon;
    }


    /// <summary>
    /// setWindowColor
	///  ウインドウカラーの設定
    /// </summary>
    private void setWindowColor(int window)
    {
    	this.lpsWindowColor = window;

    	switch(window)
    	{
    	case 0:windowCode = R.drawable.window;			break;
    	case 1:windowCode = R.drawable.window_blue;		break;
    	case 2:windowCode = R.drawable.window_green;	break;
    	case 3:windowCode = R.drawable.window_pink;		break;
    	case 4:windowCode = R.drawable.window_purple;	break;
    	case 5:windowCode = R.drawable.window_red;		break;
    	case 6:windowCode = R.drawable.window_yellow;	break;
    	default:windowCode = R.drawable.window;		break;
    	}
    }

    /// <summary>
    /// setTopicNews
	///  ニュースジャンルの話題をしゃべらない     : 0
	///  ニュースジャンルの話題をしゃべる         : 1
    /// </summary>
    private void setTopicNews(int lpsTopicNews)
    {
    	this.lpsTopicNews = lpsTopicNews;
    }

    /// <summary>
    /// setTopicNews
	///  ニュースジャンルの話題をしゃべらない     : 0
	///  ニュースジャンルの話題をしゃべる         : 1
    /// </summary>
    private void setTopic2ch(int lpsTopic2ch)
    {
    	this.lpsTopic2ch = lpsTopic2ch;
    }

    /// <summary>
    /// setTopicNews
	///  ニュースジャンルの話題をしゃべらない     : 0
	///  ニュースジャンルの話題をしゃべる         : 1
    /// </summary>
    private void setTopicNico(int lpsTopicNico)
    {
    	this.lpsTopicNico = lpsTopicNico;
    }

    /// <summary>
    /// setTopicRss
	///  登録RSSジャンルの話題をしゃべらない     : 0
	///  登録RSSジャンルの話題をしゃべる         : 1
    /// </summary>
    private void setTopicRss(int lpsTopicRss)
    {
    	this.lpsTopicRss = lpsTopicRss;
    }

    /// <summary>
    /// setTopicRss
	///  登録RSSジャンルの話題をしゃべらない     : 0
	///  登録RSSジャンルの話題をしゃべる         : 1
    /// </summary>
    private void setTopicTwitter(int lpsTopicTwitter)
    {
    	this.lpsTopicTwitter = lpsTopicTwitter;
    }

    /// <summary>
    /// setTopicTwitterPu
	///  パブリックタイムラインをしゃべらない     : 0
	///  パブリックタイムラインをしゃべる         : 1
    /// </summary>
    private void setTopicTwitterPu(int lpsTopicTwitterPu)
    {
    	this.lpsTopicTwitterPu = lpsTopicTwitterPu;
    }

    /// <summary>
    /// setTopicTwitterMy
	///  マイタイムラインをしゃべらない     : 0
	///  マイタイムラインをしゃべる         : 1
    /// </summary>
    private void setTopicTwitterMy(int lpsTopicTwitterMy)
    {
    	this.lpsTopicTwitterMy = lpsTopicTwitterMy;
    }

    /// <summary>
    /// setTopicTwitterMode
	///  ランダム     : 0
	///  リアルタイム : 1
    /// </summary>
    private void setTopicTwitterMode(int lpsTopicTwitterMode)
    {
    	this.lpsTopicTwitterMode = lpsTopicTwitterMode;
    }

    /// <summary>
    /// setTopicCharMsg
	///  キャラクターメッセージをしゃべらない   : 0
	///  キャラクターメッセージをしゃべる		: 1
    /// </summary>
    private void setTopicCharMsg(int lpsTopicCharMsg)
    {
    	this.lpsTopicCharMsg = lpsTopicCharMsg;
    }

    /// <summary>
    /// setLanguage
	///  ランゲージの設定
    /// </summary>
    private void setLanguage(int language)
    {
    	this.lpsLanguage = language;
    }

    /// <summary>
    /// setWindowDis
	/// ウインドウ消去の設定
    /// </summary>
    private void setWindowDis(int windowDis)
    {
    	this.lpsWindowDis = windowDis;
    }

    /// <summary>
    /// setNewsRange
	/// ニュース範囲の設定
    /// </summary>
    private void setNewsRange(int range)
    {
    	this.lpsNewsRange = range;
    }

    /// <summary>
    /// setNewsAlready
	/// 既読ニュースを読むかどうか
    /// </summary>
    private void setNewsAlready(int already)
    {
    	this.lpsNewsAlready = already;
    }

    /// <summary>
    /// setRunOut
	/// 話題が尽きた時の動作
    /// </summary>
    private void setRunOut(int runout)
    {
    	this.lpsRunOut = runout;
    }

    /// <summary>
    /// setVoice
	/// 音声の設定
    /// </summary>
    private void setVoice(int voice)
    {
    	this.lpsVoice = voice;
    }

    /// <summary>
    /// setVoiceRate
	/// 音声レートの設定
    /// </summary>
    private void setVoiceRate(int voiceRate)
    {
    	this.lpsVoiceRate = voiceRate;
    }

    /// <summary>
    /// setVoicePitch
	/// 音声ピッチの設定
    /// </summary>
    private void setVoicePitch(int voicePitch)
    {
    	this.lpsVoicePitch = voicePitch;
    }

    /// <summary>
    /// setVoicePitch
	/// 音声ピッチの設定
    /// </summary>
    private void setLpsStatus(int lpsStatus)
    {
    	this.lpsStatus = lpsStatus;
    }

    /// <summary>
    /// setHelth
	/// 健康状態の設定
    /// </summary>
    private void setHelth(int helth)
    {
    	this.lpsHealth = helth;
    }

    ///====================================================================
    ///
    ///                          ゲッター
    ///
    ///====================================================================
    public final int getLpsMode() {
		return lpsMode;
	}

	public final int getLpsActive() {
		return lpsActive;
	}

	public final int getLpsSpeed() {
		return lpsSpeed;
	}

	public final int getLpsWindowClick() {
		return lpsWindowClick;
	}

	public final int getLpsDpSleep() {
		return lpsDpSleep;
	}

	public final int getLpsAutoSleep() {
		return lpsAutoSleep;
	}

	public final int getLpsAutoWaikup() {
		return lpsAutoWaikup;
	}

	public final int getLpsWindowColor() {
		return lpsWindowColor;
	}

	public final int getLpsDisplayIcon() {
		return lpsDisplayIcon;
	}

	public final int getLpsInterval() {
		return lpsInterval;
	}

    public final int getLpsReftesh() {
		return lpsReftesh;
	}

	public final int getWindowCode() {
		return windowCode;
	}

	public int getLpsIconCloseCnt() {
		if(lpsDisplayIcon == 1){return -1;}else{return lpsIconCloseCnt;}
	}

    public String getLpsUid() {
		return lpsUid;
	}

	public final int getLpsTopicNews() {
		return lpsTopicNews;
	}

	public final int getLpsTopic2ch() {
		return lpsTopic2ch;
	}

	public final int getLpsTopicNico() {
		return lpsTopicNico;
	}

	public final int getLpsTopicRss() {
		return lpsTopicRss;
	}

	public final int getLpsTopicTwitter() {
		return lpsTopicTwitter;
	}

	public final String getNewsFlg() {
		return lpsTopicNews + "," + lpsTopic2ch + "," + lpsTopicNico + "," + lpsTopicRss + "," + lpsTopicTwitterPu + "," + lpsTopicTwitterMy +  "," + lpsTopicTwitter;
	}

    public final int getLpsLanguage() {
		return lpsLanguage;
	}

    public final int getLpsWindowDis() {
		return this.lpsWindowDis;
	}

	public final int getLpsNewsRange() {
		return lpsNewsRange;
	}



	public final String getLpsNewsRangeStr()
	{
		switch(lpsNewsRange)
		{
			case 0:
				return "1";
			case 1:
				return "3";
			case 2:
				return "6";
			case 3:
				return "12";
			case 4:
				return "24";
			case 5:
				return "72";
			case 6:
				return "168";
			default:
				return "9999";
		}
	}

	public final int getLpsVoice() {
		return lpsVoice;
	}

	public final int getLpsVoiceRate() {
		return lpsVoiceRate;
	}

	public final int getLpsVoicePitch() {
		return lpsVoicePitch;
	}

	public final int getLpsStatus() {
		return lpsStatus;
	}

	public final int getNewsAlready() {
		return lpsNewsAlready;
	}

	public final int getRunOut() {
		return lpsRunOut;
	}

	public final int getTopicTwitterMode() {
		return lpsTopicTwitterMode;
	}

	public final int getTopicCharMsg() {
		return lpsTopicCharMsg;
	}

	//2013/10/13 設定変更の判定に使用
	public final String getTopicFlg() {
		return lpsTopicNews + "," + lpsTopic2ch + "," + lpsTopicNico + "," + lpsTopicRss + "," + lpsTopicTwitterPu + "," + lpsTopicTwitterMy +  "," + lpsTopicTwitter + "," + lpsRunOut;
	}

	public int getLpsHealth() {
		return lpsHealth;
	}
}
