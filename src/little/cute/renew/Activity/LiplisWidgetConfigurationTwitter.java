//=======================================================================
//  ClassName : LiplisWidgetTwitterConfiguration
//  概要      : ツイッターの設定画面
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : twitterconfiguration.xml
//
//  プリファレンスには変換値ではなく、設定値をのものを入力する!
//
//2013/03/27 ver3.3.6 Twitter対応
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Fct.FctGUIDCreator;
import little.cute.renew.Tsk.AsyncTopicSetting;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("WorldWriteableFiles")
public class LiplisWidgetConfigurationTwitter extends Activity {

	 //トークンとシークレットトークん
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

        //レイアウトのセット
        setContentView(R.layout.configurationtwitter);

        //ツイッターユーザー登録ボタン
        setTwitterUserButton();

        //クローズボタンの設定
        setCloseButton();

        //セーブボタンの設定
        setSaveButton();

        //ツイッター登録ボタン
        setTwitterPassCodeButton();

        //値の取得
        getPriference();

    }


    ///====================================================================
    ///
    ///                               初期化
    ///
    ///====================================================================

    /// <summary>
    /// onDestroy
    /// 終了時処理
    /// </summary>
    @Override
    public void onDestroy()
	{
    	super.onDestroy();
	}

    ///====================================================================
    ///
    ///                           イベントハンドラ
    ///
    ///====================================================================


    /// <summary>
    /// setSaveButton
    /// セーブボタンのハンドらセット
    /// </summary>
    protected void setSaveButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnTwitterConfigSave);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				setPriference();
				finish();
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
        Button closeButton = (Button) findViewById(R.id.btnTwitterConfigClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }


    /// <summary>
    /// setTwitterPassCodeButton
    /// ツイッター登録ボタンのハンドらセット
    /// </summary>
    protected void setTwitterPassCodeButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnSetTwitterPassCode);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationTwitterRegist.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setTwitterUserButton
    /// ツイッターユーザー登録画面
    /// </summary>
    protected void setTwitterUserButton()
    {
    	//ボタンの配置
        Button button = (Button) findViewById(R.id.btnConfigTopicTwitterUser);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				if(requestToken.equals("") || accessToken.equals(""))
				{
					viewResult("ツイッターの認証登録を行なってからボタンを押してください。");
					return;
				}

				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationTwitterUserRegist.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
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
        configEditor.putInt(LiplisDefine.PREFS_TWITTER_MODE			, getTwitterMode());

        //変更をコミット
        configEditor.commit();

        //Clalisに転送
        AsyncTopicSetting thread = new AsyncTopicSetting();
		thread.execute(getUserCd(),String.valueOf(config.getInt(LiplisDefine.PREFS_NEWS_RANGE, 1)),String.valueOf(config.getInt(LiplisDefine.PREFS_NEWS_ALREADY_READ, 0)),String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_NEWS, 1)),
				String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_2CH, 1)),String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_NICO, 1)),String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_RSS, 0)),
				String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_PUBLIC, 0)),String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER_MY, 0)),String.valueOf(config.getInt(LiplisDefine.PREFS_TOPIC_TWITTER, 0)),
				String.valueOf(getTwitterMode()),String.valueOf(config.getInt(LiplisDefine.PREFS_ROM_OUT, 0)));
    }

    protected void getPriference()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        setTwitterMode(config.getInt(LiplisDefine.PREFS_TWITTER_MODE, 0));

        //トークンとシークレット取得
		this.requestToken = config.getString(LiplisDefine.PREFS_TWITTER_TOKEN, "");
		this.accessToken = config.getString(LiplisDefine.PREFS_TWITTER_SECRET, "");

        setTwitterMode(config.getInt(LiplisDefine.PREFS_TWITTER_MODE, 0));
        setTwitterStatusText(requestToken,accessToken);


    }

    /// <summary>
    /// setTwitterStatusText
    /// ツイッター状態のテキストをセットする
    /// </summary>
    protected void setTwitterStatusText(String token, String secret)
    {
    	//ボタンの配置
        TextView txt = (TextView) findViewById(R.id.txtTwitterStatus);

    	if(token.equals("") || secret.equals(""))
    	{
    		txt.setText("未認証");
    		txt.setTextColor(Color.rgb(200, 200, 200));
    	}
    	else
    	{
            txt.setText("認証OK");
            txt.setTextColor(Color.rgb(0, 0, 255));
    	}
    }

    /// <summary>
    /// getTwitterMode
    /// ツイッターモードの取得
    /// </summary>
    protected int getTwitterMode()
    {
    	int result = 1;
    	try
    	{
    		//画面データの取得
            final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rboTwitterModeRandom);
            final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rdoTwitterModeReal);

            //チェックの判定
            if(rbRate1.isChecked())			{result = 0;}
            else if(rbRate2.isChecked())	{result = 1;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setTwitterMode
    /// ツイッターモードの取得
    /// </summary>
    protected void setTwitterMode(int mode)
    {
        final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rboTwitterModeRandom);
        final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rdoTwitterModeReal);

    	try
    	{
    		if(mode == 0){rbRate1.setChecked(true);	}
    		else if(mode == 1){	rbRate2.setChecked(true);}

            return;
    	}
    	catch(Exception e)
    	{
    		rbRate1.setChecked(true);
    		return;
    	}
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
