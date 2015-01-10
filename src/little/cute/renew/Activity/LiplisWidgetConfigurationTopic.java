//=======================================================================
//  ClassName : LiplisWidgetConfiguration
//  概要      	  : ウィジェットの設定画面
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : configuration.xml
//
//  プリファレンスには変換値ではなく、設定値をのものを入力する!
//
//2013/04/29 ver3.3.7 ニュースレンジの追加
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Fct.FctGUIDCreator;
import little.cute.renew.Tsk.AsyncGetOnetimePasswordTask;
import little.cute.renew.Tsk.AsyncTopicSetting;
import little.cute.renew.Widget.LiplisWidgetLarge;
import little.cute.renew.Widget.LiplisWidgetNormal;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class LiplisWidgetConfigurationTopic extends Activity {

	 protected String requestToken;
	 protected String accessToken;

    ///====================================================================
    ///
    ///                             初期化処理
    ///
    ///====================================================================

    /// <summary>
    /// onCreate
    /// 作成時処理
    /// </summary>
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //設定開始の通知
        sendBroadcastStart();

        //レイアウトのセット
        setContentView(R.layout.configurationtopic);

        //ワンタイムパスワードを取得する
        setGetOneTimePassButton();

        //ニュースレンジ
        setNewsRangeSpnner();


        //クローズボタンの設定
        setCloseButton();

        //セーブボタンの設定
        setSaveButton();

        //値の取得
        getPriference();
    }

    /// <summary>
    /// onCreateOptionsMenu
    /// メニューボタンの初期化
    /// </summary>
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_setting, menu);
      return true;
    }

    /// <summary>
    /// onOptionsItemSelected
    /// オプションアイテムメニューの選択
    /// </summary>
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(item.getItemId() == R.id.menu_setting)
		{
	        setDefault();
		}

		return false;
	}


    /// <summary>
    /// setSaveButton
    /// セーブボタンのハンドらセット
    /// </summary>
    protected void setSaveButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnConfigTopicSave);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				setPriference();
				finish();
				sendBroadcastSettingEnd();
		    }
		});
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void setCloseButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnConfigTopicClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setGetOneTimePassButton
    /// ワンタイムパスワード取得ボタン
    /// </summary>
    protected void setGetOneTimePassButton()
    {
    	//ボタンの配置
        Button button = (Button) findViewById(R.id.btnConfigWebRegister);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				getOnetimePasswordAsync();
		    }
		});
    }

    /// <summary>
    /// setNewsRangeSpnner
    /// ニュースレンジスピナーのハンドラセット
    /// </summary>
    protected void setNewsRangeSpnner()
    {
        //すぴなーの設定
        Spinner spinner = (Spinner) findViewById(R.id.spNewsRange);

        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // アイテムが選択された時の動作
            public void onItemSelected(AdapterView parent,View view, int position,long id) {
              // Spinner を取得
              Spinner spinner = (Spinner) parent;

              // 選択されたアイテムのテキストを取得
              @SuppressWarnings("unused")
              String str = spinner.getSelectedItem().toString();
            }

            // 何も選択されなかった時の動作
            public void onNothingSelected(AdapterView parent) {
            }
        });
    }


    ///====================================================================
    ///
    ///                         プリファレンス
    ///
    ///====================================================================

    protected void setPriference()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        //プリファレンスの呼び出し
        SharedPreferences.Editor configEditor = config.edit();

        configEditor.putInt(LiplisDefine.PREFS_TOPIC_NEWS		, getScDataTopicNews());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_2CH		, getScDataTopic2ch());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_NICO		, getScDataTopicNico());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_RSS		, getScDataTopicRss());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_TWITTER	, getScDataTopicTw());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_TWITTER_PUBLIC	, getScDataTopicTwPb());
        configEditor.putInt(LiplisDefine.PREFS_TOPIC_TWITTER_MY		, getScDataTopicTwMy());
        //configEditor.putInt(LiplisDefine.PREFS_TOPIC_CHAR_MESSAGE	, getScDataTopicChar());
        configEditor.putInt(LiplisDefine.PREFS_NEWS_RANGE			, getNewsRange());
        configEditor.putInt(LiplisDefine.PREFS_NEWS_ALREADY_READ	, getNewsAlreadyRead());
        configEditor.putInt(LiplisDefine.PREFS_ROM_OUT				, getRunOut());

        //変更をコミット
        configEditor.commit();

        //Clalisに転送
        AsyncTopicSetting thread = new AsyncTopicSetting();
		thread.execute(getUserCd(),String.valueOf(getNewsRange()),String.valueOf(getNewsAlreadyRead()),String.valueOf(getScDataTopicNews()),String.valueOf(getScDataTopic2ch()),String.valueOf(getScDataTopicNico()),String.valueOf(getScDataTopicRss()),String.valueOf(getScDataTopicTwPb()),String.valueOf(getScDataTopicTwMy()),String.valueOf(getScDataTopicTw()),String.valueOf(config.getInt(LiplisDefine.PREFS_TWITTER_MODE, 0)),String.valueOf(getRunOut()));
    }

    protected void getPriference()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        setPrfTopicNews(config.getInt(LiplisDefine.PREFS_TOPIC_NEWS, 1));
        setPrfTopic2ch(config.getInt(LiplisDefine.PREFS_TOPIC_2CH, 1));
        setPrfTopicNico(config.getInt(LiplisDefine.PREFS_TOPIC_NICO, 1));
        setPrfTopicRss(config.getInt(LiplisDefine.PREFS_TOPIC_RSS, 0));
        setPrfTopicTw(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER, 0));
        setPrfTopicTwPb(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_PUBLIC, 0));
        setPrfTopicTwMy(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_MY, 0));
        //setPrfTopicChar(config.getInt(LiplisDefine.PREFS_TOPIC_CHAR_MESSAGE, 0));
        setNewsRange(config.getInt(LiplisDefine.PREFS_NEWS_RANGE, 1));
        setNewsAlreadyRead(config.getInt(LiplisDefine.PREFS_NEWS_ALREADY_READ, 0));
        setRunOut(config.getInt(LiplisDefine.PREFS_ROM_OUT, 0));

		this.requestToken = config.getString(LiplisDefine.PREFS_TWITTER_TOKEN, "");
		this.accessToken = config.getString(LiplisDefine.PREFS_TWITTER_SECRET, "");
    }

    protected void setDefault()
    {
        setPrfTopicNews(1);
        setPrfTopic2ch(1);
        setPrfTopicNico(1);
        setPrfTopicRss(0);
        setPrfTopicTw(0);
        setPrfTopicTwPb(0);
        setPrfTopicTwMy(0);
        setNewsRange(1);
        setNewsAlreadyRead(0);
        setRunOut(0);

        setPriference();
        Toast.makeText(this, "デフォルトに戻しました。", Toast.LENGTH_SHORT).show();
    }

    ///====================================================================
    ///
    ///                 設定反映のためのブロードキャスト
    ///
    ///====================================================================

    /// <summary>
    /// sendBroadcast
    /// ウィジェットにブロードキャスト送信
    /// </summary>
    protected void sendBroadcastSettingEnd()
    {
    	Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_SETTING_FINISH);
    	sendBroadcast(intentWidgetNormal);
    	Intent intentWidgetLarge = new Intent(getApplicationContext(), LiplisWidgetLarge.class);
    	intentWidgetLarge.setAction(LiplisDefine.LIPLIS_SETTING_FINISH);
    	sendBroadcast(intentWidgetLarge);
    }

    /// <summary>
    /// sendBroadcast
    /// ウィジェットにブロードキャスト送信
    /// </summary>
    protected void sendBroadcastStart()
    {
    	Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_SETTING_START);
    	sendBroadcast(intentWidgetNormal);
    	Intent intentWidgetLarge = new Intent(getApplicationContext(), LiplisWidgetLarge.class);
    	intentWidgetLarge.setAction(LiplisDefine.LIPLIS_SETTING_START);
    	sendBroadcast(intentWidgetLarge);
    }


    ///====================================================================
    ///
    ///                           設定値取得
    ///
    ///====================================================================

    /// <summary>
    /// getScDataTopicNews
    /// ニューストピックの有効の取得
    /// </summary>
    protected int getScDataTopicNews()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicNews);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicNews
    /// ニューストピックの有効のセット
    /// </summary>
    protected void setPrfTopicNews(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicNews);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopic2ch
    /// 2chトピックの有効の取得
    /// </summary>
    protected int getScDataTopic2ch()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopic2ch);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopic2ch
    /// 2chトピックの有効のセット
    /// </summary>
    protected void setPrfTopic2ch(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopic2ch);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopicNico
    /// 2chトピックの有効の取得
    /// </summary>
    protected int getScDataTopicNico()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicNico);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicNico
    /// 2chトピックの有効のセット
    /// </summary>
    protected void setPrfTopicNico(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicNico);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopicRss
    /// Rssトピックの有効の取得
    /// </summary>
    protected int getScDataTopicRss()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicRss);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicRss
    /// Rssトピックの有効のセット
    /// </summary>
    protected void setPrfTopicRss(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicRss);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopicTw
    /// Twitterトピックの有効の取得
    /// </summary>
    protected int getScDataTopicTw()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitter);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicTw
    /// Twitterトピックの有効のセット
    /// </summary>
    protected void setPrfTopicTw(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitter);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }


    /// <summary>
    /// getScDataTopicTwPb
    /// Twitterパブリックトピックの有効の取得
    /// </summary>
    protected int getScDataTopicTwPb()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitterPublic);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicTwPb
    /// Twitterパブリックトピックの有効のセット
    /// </summary>
    protected void setPrfTopicTwPb(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitterPublic);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopicTwMy
    /// Twitterマイタイムライントピックの有効の取得
    /// </summary>
    protected int getScDataTopicTwMy()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitterMy);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfTopicTwMy
    /// Twitterマイタイムライントピックの有効のセット
    /// </summary>
    protected void setPrfTopicTwMy(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicTwitterMy);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getScDataTopicChar
    /// キャラクターメッセージライントピックの有効の取得
    /// </summary>
//    protected int getScDataTopicChar()
//    {
//    	int result = 1;
//    	try
//    	{
//            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicCharMessage);
//
//            if(chk.isChecked())	{result = 1;}
//            else				{result = 0;}
//
//            return result;
//    	}
//    	catch(Exception e)
//    	{
//    		return 0;
//    	}
//    }

    /// <summary>
    /// setPrfTopicChar
    /// キャラクターメッセージトピックの有効のセット
    /// </summary>
//    protected void setPrfTopicChar(int auto)
//    {
//    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicCharMessage);
//    	try
//    	{
//           if(auto == 1)	{chk.setChecked(true);}
//           else				{chk.setChecked(false);}
//            return;
//    	}
//    	catch(Exception e)
//    	{
//    		chk.setChecked(false);
//    	}
//    }


    /// <summary>
    /// getNewsRange
    /// ニュースレンジ
    /// </summary>
    protected int getNewsRange()
    {
    	try
    	{
    		final Spinner spinner = (Spinner) findViewById(R.id.spNewsRange);

    		if(spinner.getSelectedItem().toString().equals("1時間")){return 0;}
    		else if(spinner.getSelectedItem().toString().equals("3時間")){return 1;}
    		else if(spinner.getSelectedItem().toString().equals("6時間")){return 2;}
			else if(spinner.getSelectedItem().toString().equals("12時間")){return 3;}
			else if(spinner.getSelectedItem().toString().equals("1日")){return 4;}
			else if(spinner.getSelectedItem().toString().equals("3日")){return 5;}
			else if(spinner.getSelectedItem().toString().equals("7日")){return 6;}
			else if(spinner.getSelectedItem().toString().equals("無制限")){return 7;}
    		else{return 0;}
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setNewsRange
    /// ニュースレンジ
    /// </summary>
    protected void setNewsRange(int idx)
    {
    	final Spinner spinner = (Spinner) findViewById(R.id.spNewsRange);
    	try
    	{
    		spinner.setSelection(idx);
    	}
    	catch(Exception e)
    	{
    		spinner.setSelection(0);
    	}
    }

  /// <summary>
    /// getNewsAlreadyRead
    /// 既読メッセージ非出力の有効の取得
    /// </summary>
    protected int getNewsAlreadyRead()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicSetAlreadyRead);

            if(chk.isChecked())	{result = 1;}
            else				{result = 0;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setNewsAlreadyRead
    /// 既読メッセージ非出力の有効のセット
    /// </summary>
    protected void setNewsAlreadyRead(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkTopicSetAlreadyRead);
    	try
    	{
           if(auto == 1)	{chk.setChecked(true);}
           else				{chk.setChecked(false);}
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }




  /// <summary>
    /// getRunOut
    /// 話題が尽きた時の振る舞いを取得
    /// </summary>
    protected int getRunOut()
    {
    	int result = 1;
    	try
    	{
    		//画面データの取得
            final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rboRunOut1);
            final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rboRunOut2);
            //final RadioButton rbRate3 = (RadioButton)findViewById(R.id.rboRunOut3);

            //チェックの判定
            if(rbRate1.isChecked())			{result = 0;}
            else if(rbRate2.isChecked())	{result = 1;}
            //else if(rbRate3.isChecked())	{result = 2;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setRunOut
    /// 話題が尽きた時の振る舞いをセット
    /// </summary>
    protected void setRunOut(int mode)
    {
        final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rboRunOut1);
        final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rboRunOut2);
        //final RadioButton rbRate3 = (RadioButton)findViewById(R.id.rboRunOut3);

    	try
    	{
    		if(mode == 0){rbRate1.setChecked(true);	}
    		else if(mode == 1){	rbRate2.setChecked(true);}
    		//else if(mode == 2){	rbRate3.setChecked(true);}

            return;
    	}
    	catch(Exception e)
    	{
    		rbRate1.setChecked(true);
    		return;
    	}
    }



    ///====================================================================
    ///
    ///                              一般処理
    ///
    ///====================================================================

    /// <summary>
	/// getOnetimePassword
	/// ワンタイムパスワードを取得し、表示する
	/// </summary>
	 protected void getOnetimePasswordAsync(){
		 //☆非同期処理化 ワンタイムパスワードを取得する
		 AsyncGetOnetimePasswordTask thread = new AsyncGetOnetimePasswordTask(this);
		 thread.execute(getUserCd());
	 }

	 /// <summary>
	 /// 2013/09/14 ver3.4.3
	 /// setListViewChain
	 /// セットアダプタのチェイン処理
	 /// ☆非同期クラスから呼び出す
	 /// </summary>
	 public void getOnetimePasswordChain(String oneTimePass){
		 //表示する
	     new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_info)
	        .setTitle("ワンタイムパスワード")
	        //setViewにてビューを設定します。
	        .setMessage(oneTimePass)
	        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {

	            }
	        })
	        .show();
	 }

    /// <summary>
    /// getUserCd
    /// ユーザーコードを取得する
    /// </summary>
    protected String getUserCd()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        //UIDを取得する
        String uid = config.getString(LiplisDefine.PREFS_UID, "");

        //空の場合作成する
        if(uid.equals(""))
        {
        	uid = FctGUIDCreator.createGuid();

        	 //プリファレンスの呼び出し
            SharedPreferences.Editor configEditor = config.edit();

            //今回設定した値をプリファレンスにプット
            configEditor.putString(LiplisDefine.PREFS_UID, uid);

            //変更をコミット
            configEditor.commit();
        }

        //UIDを返す。
        return uid;
    }

    /// <summary>
    /// viewResult
    /// 結果を表示する
    /// </summary>
    protected void viewResult(String msg)
    {
    	// 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}