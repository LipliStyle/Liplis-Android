//=======================================================================
//  ClassName : LiplisWidgetProvider
//  概要      	  : リプリスウィジェットプロバイダ
//
//	extends   : AppWidgetProvider
//	impliments:
//
//　設定仕様
//	おしゃべり頻度
//　　おしゃべり　発言後 10s後
//　　ふつう　　　発言後 30s後
//　　ひかえめ　　発言後 60s後
//
//	アクティブ頻度
//　　おてんば　フル動作。1s置きのスキャン　口パク 1s置き、まばたき5s～10s
//　　ひかえめ　ハーフ動作2s置きのスキャン　口パク 2s置き、まばたき16秒おき固定
//　　エコ　　　まばたき、口パクを一切しない。感情総合ポイントで最終エモーション算出して、たち絵を変える
//
//2013/03/01 ver3.0.1 LiplisStartを廃止
//2013/03/01 ver3.1.0 ニュースソースをLiplisNewsで管理する方式に変更
//2013/03/01 ver3.1.1 ニュースソースをJsonタイプに変更
//2013/03/01 ver3.2.0 LiplisNewsに非同期でデータを取得するように変更
//2013/03/03 ver3.3.0 LiplisNewsに非同期データ収集のAPI変更
//2013/03/03 ver3.3.1 一定期間経過後、ジャンル変更時、キューリフレッシュ
//2013/03/03 ver3.3.2 小バグ修正
//2013/03/03 ver3.3.3 停止バグ修正
//2013/03/17 ver3.3.4（仮) setNextAlarm(680行あたり)の処理を試験的に修正
//                   →スタートアップの処理も試験するべき。
//                   →フリーズ対策
//2013/03/23 ver3.3.5 Clalis3.1対応 通信効率化
//2013/03/27 ver3.3.6 Twitter対応
//2013/04/29 ver3.3.7 RSS対応
//2013/05/01 ver3.4.0 おしゃべり対応
//2013/05/01 ver3.4.1 おすわり時に、再度喋り始める問題に対応
//2013/06/28 ver3.4.2 再配置時にろーでぃんぐなうで止まる問題への対応
//2013/09/14 ver3.4.3 APIレベル11以上のSDKで開発した場合、メインスレッドからの
//                    WEBアクセスで「android.os.NetworkOnMainThreadException」が発生する。
//                    このため、以下の画面のAPIアクセスを非同期化する。
//                    LiplisWidgetConfigurationTopic
//                    LiplisWidgetConfigurationRssUrlRegist
//                    LiplisWidgetConfigurationTwitterUserRegist
//                    LiplisWidgetConfigurationTwitterRegist
//
//2013/09/14 ver3.4.4 chat.xmlにバージョン追加
//2013/09/14 ver3.5.0 話題設定画面に3話題追加
//                    話題検索設定画面追加
//
//2013/10/27 ver3.6.0 小破、中破、大破追加
//                    ACTION_USER_PRESENTリスナー追加(停止対策)
//
//
//2014/04/28 ver4.0.0 Clalis4.0対応
//2014/04/28 ver4.0.1 バージョンアップ機能
//2014/05/22 ver4.0.2 時報機能
//2014/07/13 ver4.0.3 ツイッター登録のバグ修正
//
//2014/07/13 ver4.2.0 ニュートラル状態戻し追加
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Widget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import little.cute.renew.R;
import little.cute.renew.Activity.LiplisWidgeSelecter;
import little.cute.renew.Activity.LiplisWidgetConfiguration;
import little.cute.renew.Activity.LiplisWidgetLog;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Fct.FctLiplisMsg;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Obj.ObjBattery;
import little.cute.renew.Obj.ObjBody;
import little.cute.renew.Obj.ObjClock;
import little.cute.renew.Obj.ObjLiplisBody;
import little.cute.renew.Obj.ObjLiplisChat;
import little.cute.renew.Obj.ObjLiplisLogList;
import little.cute.renew.Obj.ObjLiplisVersion;
import little.cute.renew.Obj.ObjPreference;
import little.cute.renew.Obj.ObjR;
import little.cute.renew.Ser.SerialLiplisNews;
import little.cute.renew.Web.LiplisNews;
import little.cute.renew.Xml.LiplisChatSetting;
import little.cute.renew.Xml.LiplisSkinVersion;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

public class LiplisWidget extends AppWidgetProvider implements TextToSpeech.OnInitListener{

	///=====================================
    /// 更新用のインテントとコンテキスト
	protected static int appWidgetId;						//ウィジェットID

	///=====================================
    /// クラス
	//protected static LiplisApi lpsApi;						//リプリスAPI
	protected static LiplisNews lpsNews;						//リプリスニュース
	protected static ObjR or;									//アールオブジェクト
	protected static ObjLiplisBody olb;						//リプリスボディオブジェクト
	protected static ObjBody ob;								//現在表示ボディオブジェクト
	protected static ObjBattery obt;							//バッテリーオブジェクト
	protected static ObjLiplisChat olc;						//チャットオブジェクト
	protected static ObjLiplisVersion olv;					//バージョンオブジェクト ver4.0.1
	protected static ObjPreference op;						//プリファレンスオブジェクト
	protected static ObjLiplisLogList ol;						//ログオブジェクト
	protected static ObjClock oc;								//時間オブジェクト
	protected static TextToSpeech tts;						//TTSオブジェクト  2013/05/01 ver3.4.0 おしゃべり対応

	///=====================================
    /// ダウンロード用スレッド
	protected Thread thread;

	///=====================================
    /// 設定値
	//NOTE : liplisRefreshRate * liplisRate = 更新間隔 (updateCntに関連)
	protected static int liplisInterval = 10;				//インターバル
	protected static int liplisRefresh	= 10;				//リフレッシュレート
	protected static int liplisWindowCode = 0;				//ウインドウコード

	///=====================================
    /// チャット制御カウント
	protected static int cntUpdate = 10;

	///=====================================
    /// 制御フラグ
	//protected static int		flgSize = 0;					//サイズ
	protected static int 	 flgAlarm = 99;					//アラームフラグ
	protected static boolean flgInit 			= false;	//初期化フラグ
	protected static boolean flgThinking     	= false;	//考え中
	protected static boolean flgClockMode 		= false;	//クロックモード
	protected static boolean flgChatting 		= false;	//チャット中
	protected static boolean flgSkip 			= false;	//スキップ中
	protected static boolean flgSitdown 			= false;	//おやすみステータス
	protected static boolean flgAutoSleepOn 		= false;	//自動スリープがONになったときON
	protected static boolean flgIconOn 			= true;		//アイコンオン
	protected static boolean flgWindowOn 		= true;		//ウインドウオン	ver3.0
	protected static boolean flgGettingTopic 	= false;	//トピックの取得中

    ///=====================================
    /// 表示制御カウンタ
	protected static int cntBlink 	= 0;       				//1回/5～10s
	protected static int cntMouth 	= 0;       				//1回/1s

    ///=====================================
    /// 制御プロパティ
    protected static MsgShortNews liplisNowTopic;
    protected static String liplisNowWord 	= "";			//現在読み込みの単語(cntLnwでカウント)
    protected static String liplisChatText 	= "";			//現在読み込みの文字(cntLctでカウント)
    protected static int cntLct				= 0;			//リプリスチャットテキストカウント
    protected static int cntLnw				= 0;			//リプリスナウワードカウント
    protected static int nowPoint 			= 0;			//現在感情ポイント
    protected static int nowEmotion 			= 0;			//感情現在値
    protected static int prvEmotion 			= 0;			//感情前回値
    protected static String prvNewsFlg 		= "";			//前回ニュースフラグ

    ///=====================================
    /// 音声制御プロパティ  2013/05/01 ver3.4.0 おしゃべり対応
    protected float pitch = 1.0f;								//音声制御ピッチ
    protected float rate = 1.0f;								//音声制御レート

    ///=====================================
    /// アイコン制御
    protected static int cntIconClose = 0;					//アイコン消去カウント
	protected static int batteryNowId = 0;					//バッテリーID

    ///=====================================
    /// 時刻制御
    protected static int prvHour = 0;						//前回時間

	///=====================================
    /// エラー表示用
	protected static final String errorClassName ="AppWidgetProvider:";


    ///====================================================================
    ///
    ///                           イベントハンドラ
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : base
    /// MethodName : onEnabled
    /// 最初にウィジェットが作成されるときに呼び出される
    /// </summary>
    @Override
    public void onEnabled(Context context) {
		//アールオブジェクト
    	or = new ObjR(getFlgSize(context));

    	//親クラスメソッド実行
    	super.onEnabled(context);

        //アラーム生成
        flgAlarm = 99;
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onUpdate
    /// 配置時に1回呼び出される。
    /// 初期処理はここで行えば良い
    ///	(アラームマネージャーを廃止したため、ここでのアップデートは行われない)
    /// </summary>
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    	super.onUpdate(context, appWidgetManager, appWidgetIds);

    	//
    	if(flgAlarm == 12)
    	{
    		//チャットフェーズ
    		setNextAlarm(context);
    		updateLiplis(appWidgetManager,context);
    	}
    	else if(flgAlarm == 10)
    	{
    		//カウントダウンフェーズ
    		onCountDown(context);
    		setNextAlarm(context);
    	}
    	else if(flgAlarm == 11)
    	{
    		//話題取得フェーズ
    		nextLiplis(appWidgetManager,context);
    		onCheckNewsQueue(context);
    		setNextAlarm(context);
    	}
    	else if(flgAlarm == 99)
    	{
    		//初期化
    		updateFirstStep(context, appWidgetIds[0]);
    		setNextAlarm(context);
    	}
    	else if(flgAlarm == 999)
    	{
    		//2013/11/17 ver3.6.0
    		//何もしない
    	}
    	else
    	{
    		setNextAlarm(context);
    	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onDeleted
    /// ウィジェットのインスタンスが削除されたときに呼び出される
    /// </summary>
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        stopAlarm(context);
        stopAlarm(context);
        stopAlarm(context);

        //2013/11/17 ver3.6.0 999設定に変更
        flgAlarm =999;

        //2013/05/01 ver3.4.0 tts追加対応
        shudDonwnTts();
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onDeleted
    /// ウィジェットのすべてのインスタンスが削除されたときに呼び出される
    /// </summary>
	@Override
	public void onDisabled(Context context) {

		super.onDisabled(context);

        stopAlarm(context);
        stopAlarm(context);
        stopAlarm(context);

        //2013/11/17 ver3.6.0 999設定に変更
        flgAlarm =999;

        //2013/05/01 ver3.4.0 tts追加対応
        shudDonwnTts();
	}

    /// <summary>
    //  MethodType : base
    /// MethodName : onReceive
    /// メッセージを受信したときに発生
    /// </summary>
    @Override
    public void onReceive(Context context, Intent intent) {
    	try
    	{
    		//2013/11/17 ver3.6.0 終了済み(999)なら何もしない
    		if(flgAlarm == 999){return ;}

    		//親のレシーブを実行しておく
    		super.onReceive(context, intent);

        	//引数のインテントからアクションを取得
            final String action = intent.getAction();

            //アクションがACTION_APPWIDGET_DELETEDとなっているか
            if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {
            	//ウィジェットＩＤの取得
                final int appWidgetId = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
                //アプリケーションＩＤが不正ＩＤかどうかチェック
                if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                	//正しいアプリケーションＩＤなら削除する
                    this.onDeleted(context, new int[] { appWidgetId });
                }
            }
            else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
            {
            	return;
            }
            //バッテリーチェンジリスナー
            else if(Intent.ACTION_BATTERY_CHANGED.equals(action))
            {
            	onChangeBattery(intent, context);
            }
            //クリックリスナー
            else if(LiplisDefine.LIPLIS_CLICK_ACTION.equals(action))
            {
            	onClickBody(context);
            }
            //スリープリスナー
            else if(LiplisDefine.LIPLIS_CLICK_ACTION_SLEEP.equals(action))
            {
            	onClickIconSleep(context);
            }
            //スクリーンオンリスナー
            else if(Intent.ACTION_SCREEN_ON.equals(action))
    		{
            	onWaikup(context);
    		}
            //スクリーンオフリスナー
            else if(Intent.ACTION_SCREEN_OFF.equals(action))
            {
            	onSleep(context);
            }
            //バッテリークリックリスナー
            else if(LiplisDefine.LIPLIS_CLICK_ACTION_BATTERY.equals(action))
            {
            	onClickBattery(context);
            }
            //クロッククリックリスナー
            else if(LiplisDefine.LIPLIS_CLICK_ACTION_CLOCK.equals(action))
            {
            	onClickClock(context);
            }
            //設定クリックリスナー
            else if(LiplisDefine.LIPLIS_SETTING_START.equals(action))
            {
            	onSettingStart(context);
            }
            //設定完了リスナー
            else if(LiplisDefine.LIPLIS_SETTING_FINISH.equals(action))
            {
            	onReciveSettingChange(context);
            }
            //システム情報が変更された
            else if(Intent.ACTION_CONFIGURATION_CHANGED.equals(action))
            {
            	onConfigurationChenge(context);
            }
            //デバイス起動時 2013/11/17 ver3.6.0
            else if(Intent.ACTION_USER_PRESENT.equals(action))
            {
            	refleshOnly(context);
            }
            else if(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE.equals(action))
            {
            }
            else if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action))
            {
            }
            else
            {

            }
    	}
    	catch(Exception e)
    	{
    	}
    }

    ///====================================================================
    ///
    ///                       非オーバーライドリスナー
    ///
    ///====================================================================

    /// <summary>
    /// onCountDown
    /// カウントダウンイベント
    /// </summary>
    protected void onCountDown(Context context)
    {
        try
        {
    		//カウントダウン
    		cntUpdate--;

    		//5秒前にニュートラルに戻す
    		if(cntUpdate == 5 && !flgSitdown)
    		{
    			setObjectBodyNeutral(context);
    		}

    		//チャットフェーズに移行
    		if(cntUpdate <= 0)
    		{
    			flgAlarm = 11;
    		}

    		//ウインドウオフチェック
    		// ウインドウ消去がON かつ ウインドウフラグがON かつ LPSMODEが4以外の場合、10秒以上経過しているかチェック
    		if(op.getLpsWindowDis() == 1 && flgWindowOn && op.getLpsMode() != 4)
    		{
    			//10秒経過していたらウインドウを閉じる
    			if(liplisInterval - cntUpdate > 10)
        		{
        			windowOff(context);
        		}
    		}


    	}
    	catch(Exception e)
    	{
    	}

    }

    /// <summary>
    /// onCheckNewsQueue
    /// ニュースキューチェック
    //2013/03/01 ver3.2.0 LiplisNewsに非同期でデータ収集
    /// </summary>
    protected void onCheckNewsQueue(Context context)
    {
        try
        {
        	//時計モード時は実行しない
        	if(op.getLpsMode() == 4)
        	{
        		return;
        	}

        	//2013/03/03 ver3.3.1 ジャンルの更新チェック
        	if(prvNewsFlg.equals(op.getTopicFlg()))
        	{
        		//キューの補充チェック
        		lpsNews.checkNewsQueue(context,getNameValuePairForLiplisNewsList());
        	}
        	else
        	{

        		//前回ニュースフラグの更新
        		prvNewsFlg = op.getTopicFlg();

        		//キューのリフレッシュ
        		lpsNews.refleshNewsQueue(context,getNameValuePairForLiplisNewsList());
        	}


    	}
    	catch(Exception e)
    	{
    	}

    }

    /// <summary>
    /// onClickBody
    /// クリックイベント
    /// </summary>
    protected void onClickBody(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4)
        	{
        		oc.updateClockObject();
        		iconOn(context);
        		nextClick(context);
        		return;
        	}

        	//チャット中チェック
        	if(!flgChatting)
        	{
        		onCheckNewsQueue(context);
        		iconOn(context);
        		nextClick(context);
        	}
        	else
        	{
        		flgSkip = true;
        	}
    	}
    	catch(Exception e)
    	{
    	}

    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onChangeBattery
    /// バッテリー変更イベント
    /// 2011/07/25 var1.2.0
    /// </summary>
    protected void onChangeBattery(Intent intent, Context context)
    {
    	updateBatteryInfo(intent, context);
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onWaikup
    /// 起床
    /// 2011/07/31 var1.2.0
    /// </summary>
    protected void onWaikup(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4)
        	{
        		runClock(context);
        		return;
        	}

        	if(op.getLpsAutoWaikup() == 1)
        	{
        		standUp(context);
        	}
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onSleep
    /// おやすみ
    /// 2011/07/31 var1.2.0
    /// </summary>
    protected void onSleep(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4){return;}

        	if(op.getLpsAutoSleep() == 1)
        	{
        		flgAutoSleepOn = true;
        		sitDown(context);
        	}

			if (tts.isSpeaking()) {
				// 読み上げ中なら止める
				tts.stop();
			}
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onChangeBattery
    /// スリープアイコンクリック
    /// 2011/08/25 var2.0.0
    /// </summary>
    protected void onClickIconSleep(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4){return;}

        	if(flgSitdown)
        	{
        		standUp(context);
        	}
        	else
        	{
        		sitDown(context);
        	}
    	}
    	catch(Exception e)
    	{
    	}


    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onClickBattery
    /// バッテリーアイコンクリック
    /// 2011/08/25 var2.0.0
    /// </summary>
    protected void onClickBattery(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4){return;}

        	batteryInfo(context);

    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onClickClock
    /// 時計アイコンクリック
    /// 2011/08/25 var2.0.0
    /// </summary>
    protected void onClickClock(Context context)
    {
        try
    	{
        	//時計モード時無効
        	if(op.getLpsMode() == 4){return;}

        	clockInfo(context);
    	}
    	catch(Exception e)
    	{
    	}
    }

  /// <summary>
    //  MethodType : base
    /// MethodName : onSettingStart
    /// 設定開始
    /// 2011/08/25 var2.0.0
    /// </summary
    protected void onSettingStart(Context context)
    {
    	try
     	{
    		chatStop();
    		flgAlarm = 0;
     	}
     	catch(Exception e)
     	{
     	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onReciveSettingChange
    /// 設定変更通知
    /// 2011/08/25 var2.0.0
    /// </summary
    protected void onReciveSettingChange(Context context)
    {
    	try
     	{
    		//設定の読み込み
    		loadSetting(context);

    		//スイッチする
    		switchTalkClock(op.getLpsMode() == 4, context);

    		//フラグを
    		flgAlarm = 11;
     	}
     	catch(Exception e)
     	{
     	}
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onConfigurationChenge
    /// システム情報変更イベント
    /// 2011/08/25 var2.0.0
    /// </summary
    protected void onConfigurationChenge(Context context)
    {
		//アラームを初期に設定
		flgAlarm = 99;

    	try
     	{
    		switch (context.getResources().getConfiguration().orientation){
    	    case Configuration.ORIENTATION_LANDSCAPE:;
    	     break;
    	    case Configuration.ORIENTATION_PORTRAIT:
    	     break;
    	    case Configuration.ORIENTATION_SQUARE:
    	     break;
    	    default:
    	     break;
    	   }
     	}
     	catch(Exception e)
     	{
     	}
    }



    ///====================================================================
    ///
    ///                           アラーム
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : setNextAlarm
    /// アラームのスタート
    /// </summary>
    private void setNextAlarm(Context context)
    {
    	stopAlarm(context);
    	//2013/03/17 Liplis
    	//if(flgAlarm < 99){setAlarm(context);}
    	setAlarm(context);
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : setAlarm
    /// アラームのスタート
    /// </summary>
    protected boolean setAlarm(Context context)
    {
    	try
    	{
    		AlarmManager updateAlarm = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);											// AlramManager取得
    		updateAlarm.set(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime() + LiplisDefine.LIPLIS_UPDATE_RATE, getPendingAlarmIntent(context)); 	// AlramManagerにPendingIntentを登録

    		return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : stopAlarm
    /// アラームストップ
    /// </summary>
    protected boolean stopAlarm(Context context)
    {
    	try
    	{
    		AlarmManager updateAlarm = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
    		updateAlarm.cancel(getPendingAlarmIntent(context));
    		return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : getPendingAlarmIntent
    /// アラームのペンディングインテントを作成する
    /// </summary>
    protected PendingIntent getPendingAlarmIntent(Context context) {
		Intent widgetUpdate = new Intent();
		widgetUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		widgetUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] { appWidgetId });
		widgetUpdate.setType("Liplus");
		widgetUpdate.setData(Uri.withAppendedPath(Uri.parse(LiplisDefine.URI_SCHEME + "://widget/id/"), String.valueOf(appWidgetId)));
		return PendingIntent.getBroadcast(context, appWidgetId, widgetUpdate, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    ///====================================================================
    ///
    ///                           副イベント
    ///
    ///====================================================================


    /// <summary>
    //  MethodType : child
    /// MethodName : sidDown
    /// すわり
    /// </summary>
    protected boolean sitDown(Context context)
    {
    	try
    	{
    		//すわり有効、おしゃべり中ならおしゃべりメソッド内で処理
    		flgSitdown = true;

    		//おしゃべり中でなければ座りモーション
    		//おしゃべり中はれフレッシュメソッド内で処理
    		if(!flgChatting)
    		{
    			//座りモーション
    			updateBodySitDown(context);
    		}

    		//アイコン変更
    		updateSleepIcon(context);

    		//2013/05/02 ver3.4.1 お休み時自動復帰防止対応
    		//シットダウン状態に移行する
    		op.updateLpsStuts(context,1);

    		return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : standUp
    /// 立ち上がり
    /// </summary>
    protected boolean standUp(Context context)
    {
    	try
    	{
    		//2013/05/02 ver3.4.1 お休み時自動復帰防止対応
    		//シットダウン状態に移行する
    		op.updateLpsStuts(context,0);

    		flgSitdown = false;

    		//アイコン変更
    		updateSleepIcon(context);

    		//あいさつ
    		greet(context);

    		return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : iconOn
    /// アイコンオン
    /// </summary>
    protected boolean iconOn(Context context)
    {
        try
    	{
        	flgIconOn = true;
        	cntIconClose = op.getLpsIconCloseCnt();

        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            remoteView.setViewVisibility(or.liplisSleep, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisLog, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisSetting, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisThinking, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisAngClock, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisBattery, View.VISIBLE);

    		//アイコンクリックリスナー
    		setClickListenerIcon(remoteView, context);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

        	return true;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : iconOff
    /// アイコンオフ
    /// </summary>
    protected boolean iconOff(Context context)
    {
        try
    	{
        	flgIconOn = false;
        	cntIconClose = -1;

        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            remoteView.setViewVisibility(or.liplisSleep, View.GONE);
            remoteView.setViewVisibility(or.liplisLog, View.GONE);
            remoteView.setViewVisibility(or.liplisSetting, View.GONE);
            remoteView.setViewVisibility(or.liplisThinking, View.GONE);
            remoteView.setViewVisibility(or.liplisAngClock, View.GONE);
            remoteView.setViewVisibility(or.liplisBattery, View.GONE);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

        	return true;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : windowOn
    /// ウインドウオン
    /// ver3.0 2012/03/31
    /// </summary>
    protected boolean windowOn(Context context)
    {
        try
    	{
        	flgWindowOn = true;

        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //ウインドウを出現させる
            remoteView.setViewVisibility(or.liplisWindow, View.VISIBLE);
            remoteView.setViewVisibility(or.liplisTalkText, View.VISIBLE);

            //実ウインドウ
            remoteView.setImageViewResource(or.liplisWindow, LiplisUtil.getWindowCode(liplisWindowCode));
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

        	return true;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : windowOff
    /// ウインドウオン
    /// ver3.0 2012/03/31
    /// </summary>
    protected boolean windowOff(Context context)
    {
        try
    	{
        	flgWindowOn = false;

        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //ウインドウ消去
            remoteView.setTextViewText(or.liplisTalkText, "");
            remoteView.setViewVisibility(or.liplisWindow, View.GONE);
            remoteView.setViewVisibility(or.liplisTalkText, View.GONE);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

        	return true;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}
    }

    ///====================================================================
    ///
    ///                         イベントリスナー
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : setClickListener
    /// クリックリスナーの設定
    /// </summary>
    protected boolean setClickListener(RemoteViews remoteView, Context context)
	{
    	//2013/03/03 ver3.3.3 test
    	//return setClickListener(remoteView, context, or.cls);
    	return setClickListener(remoteView, context, this.getClass());
	}
    protected boolean setClickListener(RemoteViews remoteView, Context context, Class<?> cls)
	{
        try
        {
        	//ボディのクリックリスナー定義
        	Intent intentBody = new Intent(context, cls);
        	intentBody.setAction(LiplisDefine.LIPLIS_CLICK_ACTION);
        	PendingIntent pendingIntentBody = PendingIntent.getBroadcast(context, appWidgetId, intentBody, 0);
        	remoteView.setOnClickPendingIntent(or.liplisImage, pendingIntentBody);

        	if(op.getLpsWindowClick() == 2)
        	{
            	//ウインドウのクリックリスナー定義
                //呼び出したいActivityをセット
        		Intent intentWeb = new Intent("android.intent.action.VIEW",Uri.parse(liplisNowTopic.url));
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentWeb, 0);

                //widgetのボタンクリックイベントに呼び出したいIntentを設定する。
                remoteView.setOnClickPendingIntent(or.liplisWindow, pendingIntent);
        	}
        	else if(op.getLpsWindowClick() == 1)
        	{
        		Intent intentSetting = new Intent(context, LiplisWidgetConfiguration.class);
            	PendingIntent pdSetting = PendingIntent.getActivity(context, 0, intentSetting, 0);
            	remoteView.setOnClickPendingIntent(or.liplisWindow, pdSetting);
        	}

        	return true;
        }
        catch(Exception e)
        {
        	return false;
        }
	}

    /// <summary>
    //  MethodType : child
    /// MethodName : setClickListenerIcon
    /// アイコンクリックリスナーの設定
    /// </summary>
    protected boolean setClickListenerIcon(RemoteViews remoteView, Context context)
	{
    	//2013/03/03 ver3.3.3 test
    	//return setClickListenerIcon(remoteView, context, or.cls);
    	return setClickListenerIcon(remoteView, context, this.getClass());
	}
    protected boolean setClickListenerIcon(RemoteViews remoteView, Context context, Class<?> cls)
	{
        try
        {
        	//スリープアイコンクリックリスナー定義
        	Intent intentIconSleep = new Intent(context, cls);
        	intentIconSleep.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_SLEEP);
        	PendingIntent piIconSleep = PendingIntent.getBroadcast(context, appWidgetId, intentIconSleep, 0);
        	remoteView.setOnClickPendingIntent(or.llLpsSleep, piIconSleep);

        	//設定のクリックリスナー定義
        	//Intent intentSetting = new Intent(context, LiplisWidgetConfiguration.class);
        	Intent intentSetting = new Intent(context, LiplisWidgeSelecter.class);
        	PendingIntent pdSetting = PendingIntent.getActivity(context, 0, intentSetting, 0);
        	remoteView.setOnClickPendingIntent(or.llLpsSetting, pdSetting);

        	//ログのクリックリスナー定義
        	//ペンディングインテントにエキストラをセットする場合はコンストラクターの第四引数に「PendingIntent.FLAG_UPDATE_CURRENT」を指定
        	Intent intentLog = new Intent(context, LiplisWidgetLog.class);
        	intentLog.putExtra("LOGLIST", ol);
        	PendingIntent pdLog = PendingIntent.getActivity(context, 0, intentLog, PendingIntent.FLAG_UPDATE_CURRENT);
        	remoteView.setOnClickPendingIntent(or.llLpsLog, pdLog);

        	//バッテリーアイコンクリックリスナー定義
        	Intent intentIconBattery = new Intent(context, cls);
        	intentIconBattery.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_BATTERY);
        	PendingIntent piIconBattery = PendingIntent.getBroadcast(context, appWidgetId, intentIconBattery, 0);
        	remoteView.setOnClickPendingIntent(or.llLpsbattery, piIconBattery);

        	//時計アイコンクリックリスナー定義
        	Intent intentIconClock = new Intent(context, cls);
        	intentIconClock.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_CLOCK);
        	PendingIntent piIconClock = PendingIntent.getBroadcast(context, appWidgetId, intentIconClock, 0);
        	remoteView.setOnClickPendingIntent(or.llLpsAngClock, piIconClock);

        	return true;
        }
        catch(Exception e)
        {
        	return false;
        }
	}

    /// <summary>
    //  MethodType : child
    /// MethodName : setPhoneStateListner
    /// 着信時イベントの実装
    /// 以下のパーミッションが必要
    /// <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    ///
    /// </summary>
    protected boolean setPhoneStateListner(RemoteViews remoteView, Context context, Class<?> cls)
	{
        try
        {
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            // リスナーの作成
            PhoneStateListener listener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE: //待ち受け状態

                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK: //通話開始時
                        //stateString = "通話開始 "+ incomingNumber;
                        break;
                    case TelephonyManager.CALL_STATE_RINGING: //着信時
                        //stateString = "着信中"+ incomingNumber;
                        break;
                    }

                }
            };

            // リスナーの登録
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

        	return true;
        }
        catch(Exception e)
        {
        	return false;
        }
	}



    ///====================================================================
    ///
    ///                           実装メソッド
    ///
    ///====================================================================

    protected RemoteViews getRemoteViews(String packageName)
    {
    	return new RemoteViews(packageName, or.layout);
    }


    ///====================================================================
    ///
    ///                           初期化処理
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : initLiplis
    /// 初期化
    /// </summary>
    protected void initLiplis(Context context)
    {
    	try
    	{
    		//アールオブジェクト
        	or = new ObjR(getFlgSize(context));

    		if(!flgInit)
    		{
    			//2013/03/01 ver3.1.0 LiplisApiの呼び出しを廃止
    			//LiplisAPIの初期化
            	//lpsApi = new LiplisApi();

    			//2013/03/01 ver3.1.0 LiplisNewsの初期化
    			lpsNews = SerialLiplisNews.loadObject(context);

            	//プリファレンスオブジェクト
            	op = new ObjPreference(context);

            	//2012/08/20 OLBの読み込みを外部リソースにする。

            	//OLBの初期化
            	olb = new ObjLiplisBody();

            	//バッテリーインフォの初期化
            	obt = new ObjBattery();

            	//OLBよりデフォルトのたち絵データをロードしておく
            	ob = olb.getDefaultBody();

            	//ログオブジェクトの初期化
            	ol = new ObjLiplisLogList();

            	//時間オブジェクトの初期化
            	oc = new ObjClock();

            	//チャット設定の取得
            	LiplisChatSetting lc = new LiplisChatSetting();

        		olc = lc.getChatSetting(context.getResources().getXml(R.xml.chat));

        		//バージョン定義の取得 ver4.0.1
        		LiplisSkinVersion lv = new LiplisSkinVersion();

        		olv = lv.getVersion(context.getResources().getXml(R.xml.version));

            	//チャット中フラグ
            	flgChatting = false;

            	//クロックモード
            	flgClockMode = false;

            	//リスナーの追加
            	createIntentFilter(Intent.ACTION_BATTERY_CHANGED, context);
            	createIntentFilter(Intent.ACTION_SCREEN_ON, context);
            	createIntentFilter(Intent.ACTION_SCREEN_OFF, context);
            	createIntentFilter(Intent.ACTION_CONFIGURATION_CHANGED, context);

            	flgInit = true;
    		}

    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : createIntentFilter
    /// インテントフィルタークリエイター
    /// </summary>
	public void createIntentFilter (String Code, Context context)
	{
		try
		{
			IntentFilter filterBattery = new IntentFilter();
			filterBattery.addAction(Code);
			context.getApplicationContext().registerReceiver(this, filterBattery);
		}
		catch(Exception e)
		{
		}
	}

	/// <summary>
    //  MethodType : child
    /// MethodName : getFlgSize
    /// flgSizeの取得
    /// </summary>
	protected int getFlgSize(Context context)
	{
		try
		{
			return  context.getSharedPreferences(LiplisDefine.PREFS_NAME, 1).getInt(LiplisDefine.PREFS_NOW_SIZE, 1);
		}
		catch(Exception e)
		{
			return 1;
		}
	}

	protected void updateFirstStep(Context context, int appWidgetId)
	{
    	//まずアラームフラグを0に退避
    	flgAlarm = 0;

    	//初期化
    	initLiplis(context);

    	//2013/03/01 ver3.0.1 LiplisStartの廃止
    	//UID送信
    	//LiplisApi.liplisStart(op.getLpsUid());

    	//アプリウィジェットIDの取得
    	this.appWidgetId = appWidgetId;

    	//おしゃべり初期化
    	flgChatting = false;

    	//
    	//起こしておく		2012/10/29 ver3.0
    	flgSitdown = false;

    	//設定のよみこみ
    	loadSetting(context);

    	//音声オブジェクトの初期化 2013/05/01 ver3.4.0 おしゃべり対応
        initTts(context);

    	//2013/03/03 ver3.3.1 ニュース設定変更時にキューをリフレッシュするように考慮
    	prvNewsFlg = op.getNewsFlg();

    	//バッテリー表示
    	updateBatteryInfo(null,context);

    	//チャットインフォの初期化
    	initChatInfo();

    	//2013/05/02 ver3.4.1 お休み時自動復帰防止対応 処理変更のため廃止
    	//挨拶
    	//greet(context);

    	//2014/05/22 ver4.0.2 時報機能追加
    	prvHour = getHour();

    	//2013/05/02 ver3.4.1 お休み時自動復帰防止対応 standUpでグリーとさせる
    	//2013/06/28 ver3.4.2 再配置時にろーでぃんぐなうで止まる問題への対応(refleshOnlyメソッド追加)
    	if(op.getLpsStatus() == 0)
    	{
    		refleshOnly(context);
    		standUp(context);
    	}
    	else
    	{
    		sitDown(context);
    		refleshOnly(context);
    		updateBodySitDown(context);
    	}
	}

    /// <summary>
    //  MethodType : child
    /// MethodName : reSetUpdateCount
    /// チャットの開始
    /// </summary>
	protected void reSetUpdateCount()
	{
        try
    	{
    		cntUpdate = liplisInterval;
        	flgAlarm = 10;
    	}
    	catch(Exception e)
    	{
    	}
	}

    /// <summary>
    //  MethodType : child
    /// MethodName : createTimer
    /// チャットの開始
    /// </summary>
	protected void chatStart(Context context)
	{
        try
    	{
    		//フラグ
    		flgChatting = true;

    		//即表示判定
    		if(op.getLpsSpeed() == 3)
    		{
    			immediatelyReflesh(context);
    		}else
    		{
    			/// 2013/05/01 ver3.4.0 おしゃべり対応
    			if(op.getLpsVoice() == 1)
    			{
    				try
    				{
    					speechText(context);
    				}
    				catch(Exception ex)
    				{
    					Log.d("Liplis chatStart",ex.toString());
    				}
    			}
    			//おしゃべりフェーズに移行
    			flgAlarm = 12;
    		}
    	}
    	catch(Exception e)
    	{
    	}
	}

	/// <summary>
    //  MethodType : child
    /// MethodName : chatStop
    /// チャットストップ
    /// </summary>
	protected void chatStop()
	{
        try
    	{
        	//スルーカウントに移行
        	flgAlarm = 0;

        	//カウントの初期化
        	reSetUpdateCount();
    	}
    	catch(Exception e)
    	{
    	}
    	finally
    	{
    		flgChatting = false;
    		flgSkip = false;
    	}
	}

    ///====================================================================
    ///
    ///                            音声制御関連
    ///
    ///====================================================================


    /// <summary>
    //  MethodType : base
    /// MethodName : onInit
    /// tts用オーバーライドメソッド
    /// 2013/05/01 ver3.4.0 tts追加対応
    /// </summary>
	public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
        	//#TEST
        	//Locale locale = Locale.JAPANESE;
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }
	}

    /// <summary>
    /// MethodType : child
    /// MethodName : initTts
	/// 2013/05/01 ver3.4.0 おしゃべり対応
    /// ttsを初期化する
    /// </summary>
	public void initTts(Context context)
	{
		try
		{
	    	//音声オブジェクトの初期化
	        //ここで渡すコンテキストは、「getApplicationContext」で取得しないとエラーとなる。
	    	tts = new TextToSpeech(context.getApplicationContext(), this);

	    	//ピッチ設定
	    	onTtsSettingChange();
		}
		catch(Exception e)
		{
		}
	}

	/// <summary>
    //  MethodType : child
    /// MethodName : speechText
    /// 現在の話題を音声でおしゃべりする
	/// 2013/05/01 ver3.4.0 おしゃべり対応
    /// </summary>
	protected void speechText(Context context)
	{
		//ヌルチェック
		if(tts == null){initTts(context);}

		//現在の読み込みテキストを取得する
		String speechStr = getShortNewsText();

		//スピーチストリングの長さをチェック
		if (0 < speechStr.length()) {
			//読み上げ中チェック
			if (tts.isSpeaking()) {
				// 読み上げ中なら止める
				tts.stop();
			}

			// 読み上げ開始
			tts.speak(speechStr, TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	/// <summary>
    //  MethodType : child
    /// MethodName : shudDonwnTts
    /// ttsをシャットダウンする
	/// 2013/05/01 ver3.4.0 おしゃべり対応
    /// </summary>
	public void shudDonwnTts()
	{
        if (null != tts) {
            // TextToSpeechのリソースを解放する

			if (tts.isSpeaking()) {
				// 読み上げ中なら止める
				tts.stop();
			}

            tts.shutdown();
        }
	}

    /// <summary>
    //  MethodType : base
    /// MethodName : onTtsSettingChange
    /// ttsの音声設定変更時
    /// 2013/05/01 ver3.4.0 おしゃべり対応
    /// </summary
    protected void onTtsSettingChange()
    {
		this.rate = op.getLpsVoiceRate() / 50.0f;
		if(this.rate < 0.1f){this.rate = 0.1f;}

		if(tts.setSpeechRate(this.rate) == TextToSpeech.ERROR) {
			Log.e("Liplis onTtsSettingChange","tts rate set Error : " + this.rate);
		}

		this.pitch = op.getLpsVoicePitch() / 50.0f;
		if(this.pitch < 0.1f){this.pitch = 0.1f;}

		if(tts.setPitch(this.pitch) == TextToSpeech.ERROR) {
			Log.e("Liplis onTtsSettingChange","tts pitch set Error" + this.pitch);
		}

    }

    ///====================================================================
    ///
    ///                            設定取得
    ///
    ///====================================================================



    /// <summary>
    //  MethodType : child
    /// MethodName : loadSetting
    /// 設定のロード(初期化の強制ロード)
    /// </summary>
    protected void loadSetting(Context context)
    {
    	try
    	{
    		op.getPreferenceData(context);
    		liplisInterval = op.getLpsInterval();
    		liplisRefresh	= op.getLpsReftesh();
        	chengeWindow(context);
        	onTtsSettingChange();


        	//時計モードへの対応
        	switchTalkClock(op.getLpsMode() == 4, context);
    	}
    	catch(Exception e)
    	{
    	}

    	//このメソッドの後にcreateTimerしているので、ここでは何もしない
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : chengeWindowCheck
    /// ウインドウが変更されていたら更新する
    /// </summary>
    protected void chengeWindowCheck(Context context)
    {
        try
    	{
        	//ウインドウコードが現在値と同じであれば抜ける
        	if(liplisWindowCode == op.getLpsWindowColor()){return;}
        	chengeWindow(context);
    	}
    	catch(Exception e)
    	{
    	}

    }


    /// <summary>
    //  MethodType : child
    /// MethodName : ウインドウを変更する
    /// </summary>
    protected void chengeWindow(Context context)
    {
        try
    	{
        	//ウインドウコード更新
        	liplisWindowCode = op.getLpsWindowColor();

        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //ウインドウ変更
            remoteView.setImageViewResource(or.liplisWindow, LiplisUtil.getWindowCode(liplisWindowCode));

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : changeModeCheck
    /// モードが変更されているかチェックする
    /// </summary>
    protected void changeModeCheck(Context context)
    {
        try
    	{
        	//ウインドウコードが現在値と同じであれば抜ける
        	if(liplisInterval == op.getLpsInterval()){return;}

        	//時計モードへの対応
        	if(op.getLpsMode() == 4)
        	{
        		//強制起床
        		standUp(context);

        		//クロックモードオン
        		switchTalkClockCheck(true, context);
        	}
        	else
        	{
        		switchTalkClockCheck(false, context);
        	}

        	//インターバルの変更を取得
        	liplisInterval = op.getLpsInterval();

        	//インターバルが変更されているのでタイマーの生成しなおし
        	//createTimer(context);
    	}
    	catch(Exception e)
    	{
    	}
    }



    /// <summary>
    //  MethodType : child
    /// MethodName : updateBodySitDown
    /// 座り更新
    /// </summary>
    protected boolean updateBodySitDown(Context context)
    {
        try
    	{
        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            remoteView.setImageViewResource(or.liplisImage, R.drawable.sleep);
            remoteView.setTextViewText(or.liplisTalkText, LiplisDefine.SAY_ZZZ);

    		//クリックリスナーの更新
    		setClickListener(remoteView, context);

    		//アイコンクリックリスナー
    		setClickListenerIcon(remoteView, context);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

    		return true;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}


    }

    ///====================================================================
    ///
    ///                            データ取得
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : getShortNews
    /// ニュースの取得
    /// </summary>
    protected void getShortNews(Context context){
        try
    	{
        	//2013/03/01 ver3.1.0 ニュースソースをLiplisNewsで管理する方式に変更。ランゲージコードも0を固定とした
        	//liplisNowTopic = LiplisApi.getShortNews(nameValuePair, op.getLpsLanguage());
        	liplisNowTopic = lpsNews.getShortNews(context, getNameValuePairForLiplisNews(), 0);

        	if(liplisNowTopic == null || liplisNowTopic.nameList.size() == 0)
        	{
        		liplisNowTopic = FctLiplisMsg.createMsgMassageDlFaild();
        	}
    	}
    	catch(Exception e)
    	{
    		Log.d("error",e.toString());
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : getNameValuePair
    /// 2013/03/01 ver3.1.0 POSTメッセージを非同期メソッドからも取得できるようにする
    /// POST用のメッセージを作成する
    /// </summary>
    protected List<NameValuePair> getNameValuePairForLiplisNews()
    {
    	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
    	nameValuePair.add(new BasicNameValuePair("tone", LiplisDefine.API_SHORT_NEWS_TONE));
    	nameValuePair.add(new BasicNameValuePair("newsFlg", op.getNewsFlg()));
    	//nameValuePair.add(new BasicNameValuePair("lnCode", LiplisUtil.convValIntToStr(op.getLpsLanguage())));

    	return nameValuePair;
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : getNameValuePairForLiplisNewsList
    /// 2013/03/01 ver3.3.0 一括取得対応のため新設
    /// 2013/04/29 ver3.3.7 ニュースレンジを設定するように追加。ユーザーIDを追加
    /// POST用のメッセージを作成する
    /// </summary>
    protected List<NameValuePair> getNameValuePairForLiplisNewsList()
    {
    	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
    	nameValuePair.add(new BasicNameValuePair("userid", op.getLpsUid()));
    	nameValuePair.add(new BasicNameValuePair("tone", LiplisDefine.API_SHORT_NEWS_TONE));
    	nameValuePair.add(new BasicNameValuePair("newsFlg", op.getNewsFlg()));
    	nameValuePair.add(new BasicNameValuePair("num", String.valueOf(LiplisDefine.LPS_NEWS_QUEUE_GET_CNT)));
    	nameValuePair.add(new BasicNameValuePair("hour", op.getLpsNewsRangeStr()));
    	nameValuePair.add(new BasicNameValuePair("already", String.valueOf(op.getNewsAlready())));
    	nameValuePair.add(new BasicNameValuePair("twitterMode", String.valueOf(op.getTopicTwitterMode())));
    	nameValuePair.add(new BasicNameValuePair("runout", String.valueOf(op.getRunOut())));

    	return nameValuePair;
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : getShortNewsText
    /// ニュースのテキストを取得する
    /// 2013/05/01 ver3.4.0 おしゃべり対応
    /// </summary>
    protected String getShortNewsText()
    {
    	if(liplisNowTopic != null && liplisNowTopic.nameList.size() != 0)
    	{
    		//ストリングバッファ
    		StringBuffer result = new StringBuffer(100);

    		//ネームを回して文章を復元する
    		for(String str : liplisNowTopic.nameList)
    		{
    			result.append(str);
    		}

    		//文字列を返す
    		return result.toString();
    	}
    	else
    	{
    		return "";
    	}
    }

    ///====================================================================
    ///
    ///                           アイコンの更新
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : updateBatteryInfo
    /// バッテリー情報の更新
    /// </summary>
    protected boolean updateBatteryInfo(Intent intent, Context context)
    {
    	try
    	{
    		if(flgIconOn)
    		{
    			//コンテキストの初期化チェック
    			if(context == null){return false;}

    	    	//ウィジェットマネージャーの取得
    	    	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        		//リモートビューの取得
                RemoteViews remoteView = getRemoteViews(context.getPackageName());

    			//バッテリーイメージの取得
				obt.getBatteryImage(intent);

				batteryNowId =  obt.getBatteryImageId();

				//バッテリー表示を削除したためコメント化
				remoteView.setImageViewResource(or.liplisBattery, batteryNowId);

				//ウィジェットの更新
    	        appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    		}

    		return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName :  updateSleepIcon
    /// スリープアイコンの更新
    /// </summary>
    protected boolean updateSleepIcon(Context context)
    {
    	try
    	{
        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            if(flgSitdown)
            {
            	remoteView.setImageViewResource(or.liplisSleep, R.drawable.ico_waikup);
            }
            else
            {
            	remoteView.setImageViewResource(or.liplisSleep, R.drawable.ico_zzz);
            }

    		//ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

            return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : updateSleepIcon
    /// 考え中アイコンの更新
    /// </summary>
    protected boolean updateThinkingIcon(Context context)
    {
    	try
    	{
        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            if(flgThinking)
            {
            	remoteView.setImageViewResource(or.liplisThinking, R.drawable.ico_thinking);
            }
            else
            {
            	remoteView.setImageViewResource(or.liplisThinking, R.drawable.ico_thinking_not);
            }

    		//ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

            return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    ///====================================================================
    ///
    ///                          チャット処理
    ///
    ///====================================================================

    /// <summary>
    //  MethodType : child
    /// MethodName : updateLiplis
    /// リプリスの更新
    /// 次の表示
    /// </summary>
    protected void updateLiplis(AppWidgetManager appWidgetManager, Context context)
    {
    	try
    	{
    		long firstTime = SystemClock.elapsedRealtime();
    		int waitTime = 1000/liplisRefresh;

	    	for(int idx = 0; idx < liplisRefresh - 1; idx++)
	    	{
	    		if(flgAlarm != 12){return;}

	    		refleshLiplis(appWidgetManager, context);

	    		while(SystemClock.elapsedRealtime() <= (firstTime + (idx + 1) * waitTime))
	    		{
	    			Thread.sleep(10);
	    		}
	    	}
    	}
    	catch(Exception e)
    	{
    	}


    }

    /// <summary>
    //  MethodType : child
    /// MethodName : nextLiplis
    /// 次の表示
    /// </summary>
    protected void nextLiplis(AppWidgetManager appWidgetManager, Context context)
    {
        try
    	{
	    	//話題取得フェーズ終了まで0に設定
	    	flgAlarm = 0;

	    	//おしゃべり/ふつう/ひかえめ
	    	if(op.getLpsMode() == 0 || op.getLpsMode() == 1 || op.getLpsMode() == 2)
	    	{
	    		//2014/05/22 ver4.0.2時報チェック
	    		if(onTimeSignal(context))
	    		{
	    			return;
	    		}

		    	//2013/10/05 ver3.5.0 話題が尽きた時の動作
		    	if(runoutCheck(context))
		    	{
		    		reSetUpdateCount();
		    		return;
		    	}

	    		runLiplis(appWidgetManager, context);
	    	}
	    	//無口
	    	else if(op.getLpsMode() == 3)
	    	{
	    		//何もしない
	    		reSetUpdateCount();
	    	}
	    	//時計＆バッテリー
	    	else if(op.getLpsMode() == 4)
	    	{
	    		runClock(context);
	    	}
    	}
    	catch(Exception e)
    	{
    		flgAlarm = 11;
    	}
    }
    protected void nextClick(Context context)
    {
        try
    	{
	    	//2013/10/05 ver3.5.0 話題が尽きた時の動作
	    	if(runoutCheck(context))
	    	{
	    		reSetUpdateCount();
	    		return;
	    	}

        	//時計
        	if(op.getLpsMode() == 4)
        	{
        		runClock(context);
        	}
        	//発言
        	else
        	{
        		runLiplis(AppWidgetManager.getInstance(context), context);
        	}

    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    /// MethodType : child
    /// MethodName : runoutCheck
    /// 2013/10/05 ver3.5.0 話題が尽きた時の動作
    /// 話題の残量チェック
    /// </summary>
    protected boolean runoutCheck(Context context)
    {
    	return op.getRunOut() == 1 && !lpsNews.checkNewsQueueCount(context,getNameValuePairForLiplisNewsList());
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : runLiplis
    /// 行動する
    /// </summary>
    protected void runLiplis(AppWidgetManager appWidgetManager, Context context)
    {
        try
    	{
	    	//チャット中なら回避
	    	if(flgChatting){chatStop(); return;}

	    	//座り中なら回避
	    	if(flgSitdown){chatStop(); return;}

	    	//ウインドウチェック ver3.0 2012/03/31
	    	windowOn(context);

        	//クロックチェック
        	switchTalkClockCheck(false, context);

        	//アイコンカウント
        	iconCloseCheck(context);

        	//たち絵をデフォルトに戻す？
        	//returnDefaultBody();
        	setObjectBodyNeutral(context);

        	//トピックを取得する
        	getTopic(context);

        	//チャット情報の初期化
        	initChatInfo();

        	//ニュースを取得しておしゃべり
        	chatStart(context);
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : initChatInfo
    /// チャット情報の初期化
    /// 行動する
    /// </summary>
    protected void initChatInfo()
    {
        try
    	{
        	//チャットテキストの初期化
        	liplisChatText = "";

        	//ナウワードの初期化
        	liplisNowWord = "";

        	//ナウ文字インデックスの初期化
        	cntLct = 0;

        	//ナウワードインデックスの初期化
        	cntLnw = 0;
    	}
    	catch(Exception e)
    	{
    	}

    }

    /// <summary>
    //  MethodType : child
    /// MethodName : iconCloseCheck
    /// アイコン閉じのチェック
    /// </summary>
    protected void iconCloseCheck(Context context)
    {
        try
    	{
        	//アイコン消去が有効な場合
        	if(op.getLpsIconCloseCnt() >= 0)
        	{
        		if(cntIconClose > 0)
            	{
            		cntIconClose--;
            	}
        		else if(cntIconClose == 0)
        		{
        			iconOff(context);
        			cntIconClose = -1;
        		}
        	}
        	else
        	{
        		//アイコンを常に表示設定で、フラグがオフのときは表示
        		if(!flgIconOn)
        		{
        			iconOn(context);
        		}
        	}
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : getTopic
    /// トピックを取得する
    /// </summary>
    protected void getTopic(Context context)
    {
        try
    	{
        	flgThinking = true;
        	updateThinkingIcon(context);

            getShortNews(context);

        	//flgGettingTopic = false;

        	flgThinking = false;
        	updateThinkingIcon(context);
    	}
    	catch(Exception e)
    	{
    		flgThinking = false;
    		updateThinkingIcon(context);
    	}
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : switchTalkClockCheck
    /// トークウインドウとクロックウインドウをスイッチする必要があるか調べ、必要であればスイッチ
    ///	これから変更するべきフラグを指定する
    /// </summary>
    protected void switchTalkClockCheck(boolean targetBool, Context context)
    {
    	if(targetBool != flgClockMode)
    	{
    		switchTalkClock(targetBool, context);
    	}
    }
    protected void switchTalkClock(boolean target,Context context)
    {
    	//ウィジェットマネージャーの取得
    	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

		//リモートビューの取得
        RemoteViews remoteView = getRemoteViews(context.getPackageName());

        flgClockMode = target;

        if(target)
        {
        	//時計を更新しておく
        	oc.updateClockObject();

        	remoteView.setViewVisibility(or.llTalkText, View.GONE);
        	remoteView.setViewVisibility(or.llClockAndBattery, View.VISIBLE);

        	updateClock(remoteView);
        }
        else
        {
        	remoteView.setViewVisibility(or.llTalkText, View.VISIBLE);
        	remoteView.setViewVisibility(or.llClockAndBattery, View.GONE);
        }

		//ウィジェットの更新
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : refleshLiplis
    /// リフレッシュメソッド
    /// </summary>
    protected void refleshLiplis(AppWidgetManager appWidgetManager, Context context)
    {
        try
    	{
            //--- キャンセルフェーズ --------------------
            if(checkMsg(context)){return;}

            //すわりチェック
            if(checkSitdown(context)){return;}

        	//--- 描画じゅんびフェーズ --------------------
        	//ウィジェットマネージャーの取得
        	appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //スキップチェック
            if(checkSkip())
            {
    			updateText(remoteView);
    			appWidgetManager.updateAppWidget(appWidgetId, remoteView);
            }

            //--- ナウワード取得・ナウテキスト設定フェーズ --------------------
            if(setText(context)){return;}

            //--- 描画フェーズ --------------------
        	updateText(remoteView);

        	//ボディの更新
    		updateBody(remoteView);

    		//クリックリスナーの更新
    		setClickListener(remoteView, context);

    		//アイコンクリックリスナー
    		setClickListenerIcon(remoteView, context);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : immediatelyChat
    /// 即座の行動
    /// </summary>
    protected void immediatelyReflesh(Context context)
    {
        try
    	{
            //--- キャンセルフェーズ --------------------
            if(checkMsg(context)){return;}

            //すわりチェック
            if(checkSitdown(context)){return;}

        	//--- 描画じゅんびフェーズ --------------------
        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //--- 即表示フェーズ --------------------
            //スキップ
            skipLiplis();

            //--- 描画フェーズ --------------------
        	updateText(remoteView);

        	//ボディの更新
    		updateBody(remoteView);

    		//クリックリスナーの更新
    		setClickListener(remoteView, context);

    		//アイコンクリックリスナー
    		setClickListenerIcon(remoteView, context);

            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

            //終了
            checkEnd(context);
    	}
    	catch(Exception e)
    	{
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : checkMsg
    /// ver3.4.2 再配置時にろーでぃんぐなうで止まる問題への対応
    /// </summary>
    protected void refleshOnly(Context context)
    {
    	//ウィジェットマネージャーの取得
    	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

		//リモートビューの取得
        RemoteViews remoteView = getRemoteViews(context.getPackageName());

		//クリックリスナーの更新
		setClickListener(remoteView, context);

		//アイコンクリックリスナー
		setClickListenerIcon(remoteView, context);

        //ウィジェットの更新
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : checkMsg
    /// メッセージチェック
    ///	１バッチ終わったときにメッセージをヌルにしている。
    ///	次が読み込まれるまではヌルでアイドル状態なので、抜ける。
    /// </summary>
    protected boolean checkMsg(Context context)
    {
        try
    	{
        	 if(liplisNowTopic == null)
             {
             	reSetUpdateCount();
             	return true;
             }
             return false;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}

    }

    /// <summary>
    //  MethodType : child
    /// MethodName : checkSkip
    /// スキップチェック
    /// </summary>
    protected boolean checkSkip()
    {
        try
    	{
        	if(flgSkip)
            {
            	return skipLiplis();
            }
            return false;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}

    }

    /// <summary>
    //  MethodType : child
    /// MethodName : checkSkip
    /// スキップチェック
    /// </summary>
    protected boolean skipLiplis()
    {
        try
    	{
        	liplisChatText = "";

        	for(int idx = 0; idx < liplisNowTopic.nameList.size(); idx++)
        	{
                //--- ワードセット、感情チェックフェーズ --------------------
                //送りワード文字数チェック
        	    if (idx != 0) {
                    //なうワードの初期化
        	    	liplisNowWord = liplisNowTopic.nameList.get(idx);

                    //プレブエモーションセット
                    prvEmotion = nowEmotion;

                    //なうエモーションの取得
                    nowEmotion = liplisNowTopic.emotionList.get(idx);

                    //なうポイントの取得
                    nowPoint = liplisNowTopic.pointList.get(idx);
        	    }
        	    //初回ワードチェック
        	    else if (idx == 0) {

        	    	liplisNowWord = liplisNowTopic.nameList.get(idx);

        	        //空だったら、空じゃなくなるまで繰り返す
        	        if (liplisNowWord.equals("")) {
        	            do {
        	                //次ワード遷移
        	                idx++;

        	                //終了チェック
        	                if (idx > liplisNowTopic.nameList.get(idx).length()) {break;}

        	                //ナウワードの初期化
        	                liplisNowWord = liplisNowTopic.nameList.get(idx);

        	            } while (liplisNowWord.equals(""));
        	        }
        	    }
        	    //おしゃべり中は何もしない
        	    else {

        	    }

        	    for(int kdx = 0; kdx < liplisNowWord.length(); kdx++)
        	    {
                    //おしゃべり
                    liplisChatText = liplisChatText + liplisNowWord.substring(kdx, kdx + 1);
        	    }

        	    //終端設定
        	    cntLnw = liplisNowTopic.nameList.size();
        	    cntLct = liplisNowWord.length();

        	}
        	return true;
    	}
    	catch(Exception e)
    	{
    		//終端設定
    	    cntLnw = liplisNowTopic.nameList.size();
    	    cntLct = liplisNowWord.length();

    		return false;
    	}


    }

    /// <summary>
    //  MethodType : child
    /// MethodName : checkSitdown
    /// すわりチェック
    /// </summary>
    protected boolean checkSitdown(Context context)
    {
        try
    	{
        	if(flgSitdown)
        	{
        		liplisNowTopic = null;
        		updateBodySitDown(context);
        		return true;
        	}
        	return false;
    	}
    	catch(Exception e)
    	{
    		 return false;
    	}

    }

    /// <summary>
    //  MethodType : child
    /// MethodName : checkEnd
    /// 終了チェック
    /// </summary>
    protected boolean checkEnd(Context context)
    {
        try
    	{
        	if(cntLnw >= liplisNowTopic.nameList.size())
        	{
        		ol.append(liplisChatText,liplisNowTopic.url);
        		chatStop();
        		herfEyeCheck(context);
        		liplisNowTopic = null;
        		return true;
        	}
        	return false;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }


    /// <summary>
    //  MethodType : child
    /// MethodName : getSetText
    /// ナウワード設定とテキスト設定を行う
    /// </summary>
    protected boolean setText(Context context)
    {
        try
    	{
        	//配列チェック



            //送りワード文字数チェック
    	    if (cntLnw != 0) {
    	    	if(cntLct >= liplisNowWord.length())
    	    	{
    	    		//終了チェック
    	    		if(checkEnd(context)){return true;}

    		    	//チャットテキストカウントの初期化
    		    	cntLct = 0;

    	            //なうワードの初期化
    		    	liplisNowWord = liplisNowTopic.nameList.get(cntLnw);

    	            //プレブエモーションセット
    	            prvEmotion = nowEmotion;

    	            //なうエモーションの取得
    	            nowEmotion = liplisNowTopic.emotionList.get(cntLnw);

    	            //なうポイントの取得
    	            nowPoint = liplisNowTopic.pointList.get(cntLnw);

    		    	//インデックスインクリメント
    		    	cntLnw++;
    	    	}
    	    }
    	    //初回ワードチェック
    	    else if (cntLnw == 0) {

    	    	//チャットテキストカウントの初期化
    	    	cntLct = 0;

    	    	//なうワードの初期化
    	    	liplisNowWord = liplisNowTopic.nameList.get(cntLnw);

    	    	//次ワード遷移
            	cntLnw++;

    	        //空だったら、空じゃなくなるまで繰り返す
    	        if (liplisNowWord.equals("")) {
    	            do {
    	            	//チェックエンド
    	            	checkEnd(context);

    	                //終了チェック
    	                if (cntLnw > liplisNowTopic.nameList.get(cntLnw).length()) {break;}

    	                //ナウワードの初期化
    	                liplisNowWord = liplisNowTopic.nameList.get(cntLnw);

    	                //次ワード遷移
    	            	cntLnw++;

    	            } while (liplisNowWord.equals(""));
    	        }
    	    }
    	    //おしゃべり中は何もしない
    	    else {

    	    }

    	    //おしゃべり
    	    liplisChatText = liplisChatText + liplisNowWord.substring(cntLct, cntLct+1);
    	    cntLct++;

    	    return false;
    	}
    	catch(Exception e)
    	{
    		//終端設定
    	    cntLnw = liplisNowTopic.nameList.size();
    	    cntLct = liplisNowWord.length();

    	    //チャットを中断する
    	    checkEnd(context);

    		return false;
    	}

    }



    //  MethodType : child
    /// MethodName : updateBody
    /// おてんば(フル動作)
    /// </summary>
    protected boolean updateBody(RemoteViews remoteView)
    {
    	try
    	{
    		//感情変化(OBをセットする)
    		setObjectBody();

	        //--- 口パク --------------------
	        //口パクカウント
    		if(flgChatting)
    		{
    	        if(cntMouth == 1)	{cntMouth=2;}
    	        else				{cntMouth=1;}
    		}
    		else
    		{
    			cntMouth=1;
    		}

	        //--- 目パチ --------------------
	        //目パチカウント
	        if(cntBlink == 0){cntBlink = getBlinkCnt();}
	        else{cntBlink--;}

	        //--- 描画 --------------------
	        //画像はすべてできているので、感情変化を意識する必要はない
	        remoteView.setImageViewResource(or.liplisImage, ob.getLiplisBodyId(getBlinkState(), cntMouth));
	        return true;
		}
		catch(Exception e)
		{
			olb = new ObjLiplisBody();
			return false;
		}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : setObjectBody
    /// 2013/10/27 ver3.6.0 バッテリー状態を反映する
    /// </summary>
    protected boolean setObjectBody()
    {
    	try
    	{
    		//感情変化
    		if(nowEmotion != prvEmotion && flgChatting){
        		if(op.getLpsHealth() == 1 && obt.getBatteryNowLevel() < 75 )
        		{
        			//ヘルス設定ONでバッテリー残量75%以下なら、小破以下の画像取得
        			ob = olb.getLiplisBodyHelth(obt.getBatteryNowLevel(),nowEmotion,nowPoint);
        		}
        		else
        		{
        			ob = olb.getLiplisBody(nowEmotion,nowPoint);
        		}
    		}



    		return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    /// <summary>
    //  MethodType : child
    /// MethodName : setObjectBody
    /// 2014/12/01 ver4.2.0 ボディを初期状態に戻す
    /// </summary>
    protected boolean setObjectBodyNeutral(Context context)
    {
    	try
    	{
    		//感情初期化
			cntMouth = 1;
			cntBlink = 0;
			nowEmotion = 0;
			nowPoint = 0 ;

    		//感情変化
    		if(op.getLpsHealth() == 1 && obt.getBatteryNowLevel() < 75 )
    		{
    			//ヘルス設定ONでバッテリー残量75%以下なら、小破以下の画像取得
    			ob = olb.getLiplisBodyHelth(obt.getBatteryNowLevel(),nowEmotion,nowPoint);
    		}
    		else
    		{
    			ob = olb.getLiplisBody(nowEmotion,nowPoint);
    		}

    		//リフレッシュ
    		reqReflesh(context);

    		return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    //  MethodType : child
    /// MethodName : updateBodyOtenba
    /// おてんば(フル動作)
    /// </summary>
    protected boolean updateText(RemoteViews remoteView)
    {
    	try
    	{
	        remoteView.setTextViewText(or.liplisTalkText, liplisChatText);
	        return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }

    //  MethodType : child
    /// MethodName : runClock
    /// 時計、バッテリー表示
    /// </summary>
    protected void runClock(Context context)
    {
        try
    	{
        	//ウインドウチェック
        	if(!flgWindowOn){windowOn(context);};

        	//クロックチェック
        	switchTalkClockCheck(true, context);

        	//時計バッテリー表示の更新
        	refleshClockAndBattery(context);

        	//アイコンカウント
        	iconCloseCheck(context);

        	//フラグ設定
        	reSetUpdateCount();
    	}
    	catch(Exception e)
    	{
    	}
    }

    //  MethodType : child
    /// MethodName : runClockTolk
    /// 時計、バッテリーの発言
    /// </summary>
    protected void runClockTolk()
    {
        try
    	{

    	}
    	catch(Exception e)
    	{
    	}
    }

    //  MethodType : child
    /// MethodName : updateClockAndBattery
    /// 時計、バッテリー表示の更新
    /// </summary>
    protected void refleshClockAndBattery(Context context)
    {
        try
    	{
        	//--- 描画じゅんびフェーズ --------------------
        	//ウィジェットマネージャーの取得
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

    		//リモートビューの取得
            RemoteViews remoteView = getRemoteViews(context.getPackageName());

            //時刻オブジェクトの更新
            oc.updateClockObject();

            //時計＆バッテリー表示の更新
            updateClock(remoteView);

        	//ボディの更新
            cntBlink = 10;		//まばたきさせない
    		updateBody(remoteView);

    		//クリックリスナーの更新
    		setClickListener(remoteView, context);

    		//アイコンクリックリスナー
    		setClickListenerIcon(remoteView, context);


            //ウィジェットの更新
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);

    	}
    	catch(Exception e)
    	{

    	}
    }

    /// <summary>
    /// MethodType : child
    /// MethodName : updateClock
    /// 時計＆バッテリーの表示更新
    /// </summary>
    protected boolean updateClock(RemoteViews remoteView)
    {
    	int batteryRate = 0;
    	try
    	{
    		remoteView.setImageViewResource(or.imYear1, oc.getYear1());
    		remoteView.setImageViewResource(or.imYear2, oc.getYear2());
    		remoteView.setImageViewResource(or.imYear3, oc.getYear3());
    		remoteView.setImageViewResource(or.imYear4, oc.getYear4());
    		remoteView.setImageViewResource(or.imMonth1, oc.getMonth1());
    		remoteView.setImageViewResource(or.imMonth2, oc.getMonth2());
    		remoteView.setImageViewResource(or.imDay1, oc.getDay1());
    		remoteView.setImageViewResource(or.imDay2, oc.getDay2());

    		remoteView.setImageViewResource(or.imHour1, oc.getHour1());
    		remoteView.setImageViewResource(or.imHour2, oc.getHour2());
    		remoteView.setImageViewResource(or.imMin1, oc.getMin1());
    		remoteView.setImageViewResource(or.imMin2, oc.getMin2());

    		batteryRate = obt.getBatteryNowLevel();


    		if(batteryRate < 5)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 10)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 20)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 30)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 40)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 50)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 60)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 70)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 80)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.battery_non);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate < 90)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.battery_non);
    		}
    		else if(batteryRate > 90)
    		{
    			remoteView.setImageViewResource(or.imBattery01, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery02, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery03, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery04, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery05, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery06, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery07, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery08, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery09, R.drawable.ico_batterygage);
    			remoteView.setImageViewResource(or.imBattery10, R.drawable.ico_batterygage);
    		}

    		//--- 描画 --------------------
	        //感情値とポイントをランダムで取得
    		Random rnd = new Random();

            int ran = rnd.nextInt(9) + 1;
            int point = rnd.nextInt(3) - 1 ;

    		ob = olb.getLiplisBody(ran,point);

    		//描画
	        remoteView.setImageViewResource(or.liplisImage, ob.getLiplisBodyId(1, 0));

	        return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }

    /// <summary>
    /// MethodType : child
    /// MethodName : reqReflesh
    /// リフレッシュ
    /// </summary>
    private void reqReflesh(Context context)
    {
    	//ウィジェットマネージャーの取得
    	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

		//リモートビューの取得
        RemoteViews remoteView = getRemoteViews(context.getPackageName());

        //ボディの更新
        updateBody(remoteView);

        //ウィジェットの更新
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);

    }

    ///====================================================================
    ///
    ///                           定型おしゃべり
    ///
    ///====================================================================

    //  MethodType : child
    /// MethodName : greet
    /// 挨拶する
    /// </summary>
    protected void greet(Context context)
    {
        try
    	{
        	//時計モードの場合は発言させない
        	//if(op.getLpsMode() == 4){chatStart(context);return;}

        	//挨拶の選定
        	liplisNowTopic = olc.getGreet();

        	//空だったらろーでぃんぐなう♪
        	if(liplisNowTopic.getMessage().equals(""))
        	{
        		liplisNowTopic = new MsgShortNews("Lading Now",0,0);
        	}

        	//チャット情報の初期化
        	initChatInfo();

        	//おしゃべりスレッドスタート
        	chatStart(context);
    	}
    	catch(Exception e)
    	{
    	}

    }

    //  MethodType : child
    /// MethodName : batteryInfo
    /// バッテリー情報のお知らせ
    /// </summary>
    protected void batteryInfo(Context context)
    {
        try
    	{
        	//座り中なら回避
        	if(flgSitdown){return;}

        	//挨拶の選定
        	liplisNowTopic = olc.getBatteryInfo(obt.getBatteryNowLevel());

        	//空だったらろーでぃんぐなう♪
        	if(liplisNowTopic.getMessage().equals(""))
        	{
        		liplisNowTopic = new MsgShortNews(obt.getBatteryNowLevel() + "%",0,0);
        	}

        	//チャット情報の初期化
        	initChatInfo();

        	//おしゃべりスレッドスタート
        	chatStart(context);
    	}
    	catch(Exception e)
    	{
    	}
    }

    //  MethodType : child
    /// MethodName : batteryInfo
    /// 時刻情報のお知らせ
    /// </summary>
    protected void clockInfo(Context context)
    {
        try
    	{
        	//座り中なら回避
        	if(flgSitdown){return;}

        	//挨拶の選定
        	liplisNowTopic = olc.getClockInfo();

        	//空だったら現時時刻のみを返す
        	if(liplisNowTopic.getMessage().equals(""))
        	{
        		liplisNowTopic = new MsgShortNews(LiplisUtil.getNowTime(Calendar.MINUTE),0,0);
        	}

        	//チャット情報の初期化
        	initChatInfo();

        	//おしゃべりスレッドスタート
        	chatStart(context);
    	}
    	catch(Exception e)
    	{
    	}
    }


    /// <summary>
    /// 時報チェック
    /// </summary>
	protected boolean onTimeSignal(Context context)
    {
        try
    	{
        	boolean result = false;
        	int nowHour = getHour();

            //時報チェック
            if (nowHour != prvHour)
            {
            	//座り中なら回避
            	if(flgSitdown){return false;}

                liplisNowTopic = this.olc.getTimeSignal(nowHour);

            	//チャット情報の初期化
            	initChatInfo();

            	//おしゃべりスレッドスタート
            	chatStart(context);

            	result = true;
            }

            //前回時刻のセット
            prvHour = getHour();

            return result;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    /// <summary>
    /// 現在の時を取得する
    /// </summary>
    @SuppressLint("SimpleDateFormat")
    protected int getHour()
    {
    	Date date = new Date();
    	SimpleDateFormat sdfHour = new SimpleDateFormat("HH");

    	return Integer.parseInt(sdfHour.format(date));
    }


    ///====================================================================
    ///
    ///                       ボディ更新詳細処理
    ///
    ///====================================================================

    /// <summary>
    ///  MethodType : child
    /// MethodName : getBlinkCnt
    /// まばたきカウントの取得
    /// </summary>
    protected int getBlinkCnt()
    {
    	try
    	{
			Random rnd = new Random();
			return rnd.nextInt(17) + 17;
    	}
    	catch(Exception e)
    	{
    		return 10;
    	}
    }

    /// <summary>
    /// getBlinkCnt
    /// まばたきカウントの取得
    /// </summary>
    protected int getBlinkState()
    {
        try
    	{
        	switch(cntBlink)
        	{
        	case 0:
        		return 1;
        	case 1:
        		return 2;
        	case 2:
        		return 3;
        	case 3:
        		return 2;
        	default:
        		return 1;
        	}
    	}
    	catch(Exception e)
    	{
    		return 1;
    	}
    }

    /// <summary>
    /// getBlinkCnt
    /// まばたきカウントの取得
    /// </summary>
    protected void herfEyeCheck(Context context)
    {
    	if(cntBlink == 1 || cntBlink == 3)
    	{
    		cntBlink = 0;
        	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteView = getRemoteViews(context.getPackageName());
			updateBody(remoteView);
            appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    	}
    }


}
