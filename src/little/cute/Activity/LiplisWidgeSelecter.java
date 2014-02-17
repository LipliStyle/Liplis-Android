//=======================================================================
//  ClassName : LiplisWidgeSelecter
//  概要      : リプリスウィジェットセレクタ
//
//	extends   : Activity
//	impliments:
//
//	Liplisの操作画面
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Activity;

import little.cute.R;
import little.cute.Common.LiplisDefine;
import little.cute.Widget.LiplisWidgetNormal;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LiplisWidgeSelecter extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //レイアウトのセット
        setContentView(R.layout.selecter);

        //クローズボタンのセット
        setCloseButton();

        //設定ボタン呼び出しイベントのセット
        setSettingButton();

        //話題設定ボタン呼び出しイベントのセット
        setSettingTopicButton();

        //話題設定ボタン呼び出しイベントのセット
        setSettingVoiceButton();

        //キャラクター選択呼び出しイベントのセット
        //setCharButton();

        //イントロボタン呼び出しイベントのセット
        setIntroButton();

        //ネクストボタン
        setNextButton();

        //スリープ
        setSleepButton();

        //ばってりー
        setBatteryButton();

        //サイトボタン
        setSiteButton();

        //ヘルプボタン
        setHelpButton();

        //マーケットボタン
        setMarketButton();

        //クロック
        setClockButton();

        //バージョンの表示
        setVersion();

        //話題検索設定登録
	    setSettingSearchSetting();

	    //RSSURL登録
	    setSettingRssButton();

	    //ツイッター登録
	    setSettingTwitterButton();
    }

    /// <summary>
    /// onCreateOptionsMenu
    /// メニューボタンの初期化
    /// </summary>
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_selecter, menu);
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
			Intent intent =new Intent(getApplicationContext(),LiplisWidgetConfiguration.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
			return true;
		}
		else if(item.getItemId() == R.id.menu_topic)
		{
			Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationTopic.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
			return true;
		}
		else if(item.getItemId() == R.id.menu_ver)
		{
			showVersion();
			return true;
		}

		return false;
	}

    /// <summary>
    /// setNextButton
    /// ネクストボタンのハンドらセット
    /// </summary>
    protected void setNextButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectNext);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
		    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_CLICK_ACTION);
		    	sendBroadcast(intentWidgetNormal);
		    	finish();
		    }
		});
    }

    /// <summary>
    /// setSleepButton
    /// ネクストボタンのハンドらセット
    /// </summary>
    protected void setSleepButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectSleep);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
		    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_SLEEP);
		    	sendBroadcast(intentWidgetNormal);
		    	finish();
		    }
		});
    }

    /// <summary>
    /// setBatteryButton
    /// 電池ボタンのハンドらセット
    /// </summary>
    protected void setBatteryButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectBattery);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
		    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_BATTERY);
		    	sendBroadcast(intentWidgetNormal);
		    	finish();
		    }
		});
    }


    /// <summary>
    /// setClockButton
    /// 時計ボタンのハンドらセット
    /// </summary>
    protected void setClockButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectClock);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intentWidgetNormal = new Intent(getApplicationContext(), LiplisWidgetNormal.class);
		    	intentWidgetNormal.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_CLOCK);
		    	sendBroadcast(intentWidgetNormal);
		    	finish();
		    }
		});
    }

    /// <summary>
    /// setSettingButton
    /// 設定ボタンのハンドらセット
    /// </summary>
    protected void setSettingButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectSetting);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfiguration.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setSettingTopicButton
    /// 話題設定ボタンのハンドらセット
    /// </summary>
    protected void setSettingTopicButton()
    {
        //ボタンの配置
        Button btn = (Button) findViewById(R.id.btnSelectTopic);

        //クリックリスナーの作成
        btn.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationTopic.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setSettingVoiceButton
    /// ボイス設定ボタンのハンドらセット
    /// </summary>
    protected void setSettingVoiceButton()
    {
        //ボタンの配置
        Button btn = (Button) findViewById(R.id.btnSelectVoice);

        //クリックリスナーの作成
        btn.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationVoice.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }


    /// <summary>
    /// setSettingTwitterButton
    /// ツイッター登録ボタンのハンドらセット
    /// </summary>
    protected void setSettingTwitterButton()
    {
        //ボタンの配置
        Button btn = (Button) findViewById(R.id.btnConfigTopicTwitter);

        //クリックリスナーの作成
        btn.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationTwitter.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }


    /// <summary>
    /// setSettingSearchSetting
    /// 検索設定登録ボタンのハンドらセット
    /// </summary>
    protected void setSettingSearchSetting()
    {
        //ボタンの配置
        Button btn = (Button) findViewById(R.id.btnNewsFilterSetting);

        //クリックリスナーの作成
        btn.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationSearchSettingRegist.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setSettingRssButton
    /// RSS_URL登録画面
    /// </summary>
    protected void setSettingRssButton()
    {
    	//ボタンの配置
        Button button = (Button) findViewById(R.id.btnRssUrlRegister);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetConfigurationRssUrlRegist.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }

//    /// <summary>
//    /// setSettingButton
//    /// 設定ボタンのハンドらセット
//    /// </summary>
//    protected void setCharButton()
//    {
//        //ボタンの配置
//        Button settingButton = (Button) findViewById(R.id.btnSelectChar);
//
//        //クリックリスナーの作成
//        settingButton.setOnClickListener(new OnClickListener() {
//		  	//クリックイベント
//			public void onClick(View v) {
//				Intent intent=new Intent(getApplicationContext(),LiplisWidgetChar.class);
//	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	            startActivity(intent);
//		    }
//		});
//    }
//
    /// <summary>
    /// setIntroButton
    /// 紹介ボタンのハンドらセット
    /// </summary>
    protected void setIntroButton()
    {
        //ボタンの配置
        Button settingButton = (Button) findViewById(R.id.btnSelectIntro);

        //クリックリスナーの作成
        settingButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LiplisWidgetIntroduction.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
		    }
		});
    }


    /// <summary>
    /// setSiteButton
    /// サイトボタンのハンドらセット
    /// </summary>
    protected void setSiteButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnCtlSite);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent("android.intent.action.VIEW", Uri.parse(LiplisDefine.SITE_MAIN));
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setHelpButton
    /// ヘルプボタンのハンドらセット
    /// </summary>
    protected void setHelpButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnCtlHelp);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent("android.intent.action.VIEW", Uri.parse(LiplisDefine.SITE_HELP));
	            startActivity(intent);
		    }
		});
    }

    /// <summary>
    /// setMarketButton
    /// マーケットボタンのハンドらセット
    /// </summary>
    protected void setMarketButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnMarket);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				Intent intent=new Intent("android.intent.action.VIEW", Uri.parse(LiplisDefine.SITE_MARKET));
	            startActivity(intent);
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
        Button closeButton = (Button) findViewById(R.id.btnSelectClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setVersion
    /// バージョンを設定する
    /// </summary>
    protected void setVersion()
    {
    	TextView txtVer = (TextView) findViewById(R.id.txtVersion);
    	PackageInfo packageInfo = null;

    	try {
    	        packageInfo = getPackageManager().getPackageInfo("little.cute", PackageManager.GET_META_DATA);
    	        txtVer.setText("バージョン:" + packageInfo.versionName);
    	} catch (NameNotFoundException e) {
    	        e.printStackTrace();
    	        txtVer.setText("バージョン:?");
    	}
    }

    /// <summary>
    /// setVersion
    /// バージョンを設定する
    /// </summary>
    protected void showVersion()
    {
    	PackageInfo packageInfo = null;
    	try {
	        packageInfo = getPackageManager().getPackageInfo("little.cute", PackageManager.GET_META_DATA);
	        Toast.makeText(this, "Liplis Android : " + packageInfo.versionName, Toast.LENGTH_SHORT).show();
		} catch (NameNotFoundException e) {
		        e.printStackTrace();
		}
    }

}
