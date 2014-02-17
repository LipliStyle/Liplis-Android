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
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Activity;

import little.cute.R;
import little.cute.Common.LiplisDefine;
import little.cute.Fct.FctGUIDCreator;
import little.cute.Widget.LiplisWidgetLarge;
import little.cute.Widget.LiplisWidgetNormal;
import android.app.Activity;
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

public class LiplisWidgetConfiguration extends Activity {



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
        setContentView(R.layout.configuration);

        //クローズボタンの設定
        setCloseButton();

        //セーブボタンの設定
        setSaveButton();

        //ウインドウスピナーのハンドラセット
        setWindowSpinner();

        //ランゲージすぴなーのハンドラセット
        setLanguageSpinner();

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
        Button closeButton = (Button) findViewById(R.id.btnLiplisConfigSave);

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
        Button closeButton = (Button) findViewById(R.id.btnLiplisConfigClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
				sendBroadcastSettingEnd();
		    }
		});
    }

    /// <summary>
    /// setWindowSpinner
    /// ウインドウスピナーのハンドラセット
    /// </summary>
    protected void setWindowSpinner()
    {
        //すぴなーの設定
        Spinner spinner = (Spinner) findViewById(R.id.spWindow);

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

    /// <summary>
    /// setLanguageSpinner
    /// ランゲージスピナーのハンドラセット
    /// </summary>
    protected void setLanguageSpinner()
    {
//        //すぴなーの設定
//        Spinner spinner = (Spinner) findViewById(R.id.spLanguage);
//
//        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // アイテムが選択された時の動作
//            public void onItemSelected(AdapterView parent,View view, int position,long id) {
//              // Spinner を取得
//              Spinner spinner = (Spinner) parent;
//
//              // 選択されたアイテムのテキストを取得
//              String str = spinner.getSelectedItem().toString();
//            }
//
//            // 何も選択されなかった時の動作
//            public void onNothingSelected(AdapterView parent) {
//            }
//        });
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

        //今回設定した値をプリファレンスにプット
        configEditor.putString(LiplisDefine.PREFS_UID, config.getString(LiplisDefine.PREFS_UID, FctGUIDCreator.createGuid()));
        configEditor.putInt(LiplisDefine.PREFS_SET_FLG, 1);
        configEditor.putInt(LiplisDefine.PREFS_LANGUAGE		, getScDataLanguage());
        configEditor.putInt(LiplisDefine.PREFS_MODE			, getScDataMode());
        configEditor.putInt(LiplisDefine.PREFS_SPEED		, getScDataSpeed());
        configEditor.putInt(LiplisDefine.PREFS_WINDOW_CLICK	, getScDataLc());
        configEditor.putInt(LiplisDefine.PREFS_AUTO_SLEEP	, getScDataAutoSleep());
        configEditor.putInt(LiplisDefine.PREFS_AUTO_WAIKUP	, getScDataAutoWaikup());
        configEditor.putInt(LiplisDefine.PREFS_DISPLAY_ICON	, getScDataDisplayIcon());
        configEditor.putInt(LiplisDefine.PREFS_WINDOW_CODE	, getScDataWindowColor());
        configEditor.putInt(LiplisDefine.PREFS_WINDOW_DIS	, getScDataWindowDis());
        configEditor.putInt(LiplisDefine.PREFS_HELTH		, getScDataHelth());

        //変更をコミット
        configEditor.commit();
    }

    protected void getPriference()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        setPrfLanguage(config.getInt(LiplisDefine.PREFS_LANGUAGE, 0));
        setPrfMode(config.getInt(LiplisDefine.PREFS_MODE, 0));
        setPrfSpeed(config.getInt(LiplisDefine.PREFS_SPEED, 0));
        setPrfLc(config.getInt(LiplisDefine.PREFS_WINDOW_CLICK, 0));
        setPrfAutoSleep(config.getInt(LiplisDefine.PREFS_AUTO_SLEEP, 1));
        setPrfAutoWaikup(config.getInt(LiplisDefine.PREFS_AUTO_WAIKUP, 0));
        setPrfDisplayIcon(config.getInt(LiplisDefine.PREFS_DISPLAY_ICON, 0));
        setPrfWindowColor(config.getInt(LiplisDefine.PREFS_WINDOW_CODE, 0));
        setPrfWindowDis(config.getInt(LiplisDefine.PREFS_WINDOW_DIS, 0));
        setPrfHelth(config.getInt(LiplisDefine.PREFS_HELTH, 1));
    }

    protected void setDefault()
    {
    	setPrfLanguage(0);
        setPrfMode(0);
        setPrfSpeed(0);
        setPrfLc(0);
        setPrfAutoSleep(1);
        setPrfAutoWaikup(0);
        setPrfDisplayIcon(0);
        setPrfWindowColor(0);
        setPrfWindowDis(0);
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
    /// getScDataLanguage
    /// ランゲージの取得
    ///
    /// webサービス側の参照
    /// 名前空間 : LiplisSatellite.Clalis.v30.Lib.Api
    /// メソッド : convertLnCode
    ///
    /// </summary>
    protected int getScDataLanguage()
    {
    	try
    	{
//    		final Spinner spinner = (Spinner) findViewById(R.id.spLanguage);
//
//    		if(spinner.getSelectedItem().toString().equals("日本語"))			{return 0;}
//    		else if(spinner.getSelectedItem().toString().equals("英語"))		{return 1;}
//    		else if(spinner.getSelectedItem().toString().equals("中国語"))		{return 2;}
//			else if(spinner.getSelectedItem().toString().equals("ポルトガル語")){return 3;}
//			else if(spinner.getSelectedItem().toString().equals("ドイツ語"))	{return 4;}
//			else if(spinner.getSelectedItem().toString().equals("アラビア語"))	{return 5;}
//			else if(spinner.getSelectedItem().toString().equals("フランス語"))	{return 6;}
//			else if(spinner.getSelectedItem().toString().equals("ロシア語"))	{return 7;}
//			else if(spinner.getSelectedItem().toString().equals("スペイン語"))	{return 8;}
//			else if(spinner.getSelectedItem().toString().equals("韓国語"))		{return 9;}
//			else{return 0;}
    		return 0;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfLanguage
    /// ランゲージ
    /// </summary>
    protected void setPrfLanguage(int idx)
    {
//    	final Spinner spinner = (Spinner) findViewById(R.id.spLanguage);
//    	try
//    	{
//    		spinner.setSelection(idx);
//    	}
//    	catch(Exception e)
//    	{
//    		spinner.setSelection(0);
//    	}
    }

    /// <summary>
    /// getRate
    /// おしゃべり頻度の取得
    /// </summary>
    protected int getScDataMode()
    {
    	int result = 1;
    	try
    	{
    		//画面データの取得
            final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rbRate1);
            final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rbRate2);
            final RadioButton rbRate3 = (RadioButton)findViewById(R.id.rbRate3);
            final RadioButton rbRate4 = (RadioButton)findViewById(R.id.rbRate4);
            final RadioButton rbRate5 = (RadioButton)findViewById(R.id.rbRate5);

            //チェックの判定
            if(rbRate1.isChecked())			{result = 0;}
            else if(rbRate2.isChecked())	{result = 1;}
            else if(rbRate3.isChecked())	{result = 2;}
            else if(rbRate4.isChecked())	{result = 3;}
            else if(rbRate5.isChecked())	{result = 4;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setPrfMode
    /// おしゃべり頻度の取得
    /// </summary>
    protected void setPrfMode(int mode)
    {
        final RadioButton rbRate1 = (RadioButton)findViewById(R.id.rbRate1);
        final RadioButton rbRate2 = (RadioButton)findViewById(R.id.rbRate2);
        final RadioButton rbRate3 = (RadioButton)findViewById(R.id.rbRate3);
        final RadioButton rbRate4 = (RadioButton)findViewById(R.id.rbRate4);
        final RadioButton rbRate5 = (RadioButton)findViewById(R.id.rbRate5);

    	try
    	{
    		if(mode == 0){rbRate1.setChecked(true);	}
    		else if(mode == 1){	rbRate2.setChecked(true);}
    		else if(mode == 2){	rbRate3.setChecked(true);}
    		else if(mode == 3){	rbRate4.setChecked(true);}
    		else if(mode == 4){	rbRate5.setChecked(true);}


            return;
    	}
    	catch(Exception e)
    	{
    		rbRate1.setChecked(true);
    		return;
    	}
    }

    /// <summary>
    /// getActive
    /// おしゃべり速度の取得
    /// </summary>
    protected int getScDataSpeed()
    {
    	int result = 1;
    	try
    	{
            final RadioButton rbSpeed1 = (RadioButton)findViewById(R.id.rbSpeed1);
            final RadioButton rbSpeed2 = (RadioButton)findViewById(R.id.rbSpeed2);
            final RadioButton rbSpeed3 = (RadioButton)findViewById(R.id.rbSpeed3);
            final RadioButton rbSpeed4 = (RadioButton)findViewById(R.id.rbSpeed4);

            if(rbSpeed1.isChecked()){result = 0;}
            else if(rbSpeed2.isChecked()){result = 1;}
            else if(rbSpeed3.isChecked()){result = 2;}
            else if(rbSpeed4.isChecked()){result = 3;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 1;
    	}
    }

    /// <summary>
    /// getActive
    /// おしゃべり速度の取得
    /// </summary>
    protected void setPrfSpeed(int speed)
    {
        final RadioButton rbSpeed1 = (RadioButton)findViewById(R.id.rbSpeed1);
        final RadioButton rbSpeed2 = (RadioButton)findViewById(R.id.rbSpeed2);
        final RadioButton rbSpeed3 = (RadioButton)findViewById(R.id.rbSpeed3);
        final RadioButton rbSpeed4 = (RadioButton)findViewById(R.id.rbSpeed4);
    	try
    	{
            if(speed == 0)		{rbSpeed1.setChecked(true);}
            else if(speed == 1){rbSpeed2.setChecked(true);}
            else if(speed == 2){rbSpeed3.setChecked(true);}
            else				{rbSpeed4.setChecked(true);}


            return;
    	}
    	catch(Exception e)
    	{
    		rbSpeed1.setChecked(true);
    		return;
    	}
    }

    /// <summary>
    /// getLc
    /// リンククリック取得
    /// </summary>
    protected int getScDataLc()
    {
    	int result = 0;
    	try
    	{
            final RadioButton rbWindowClick1 = (RadioButton)findViewById(R.id.rbWindowClick1);
            final RadioButton rbWindowClick2 = (RadioButton)findViewById(R.id.rbWindowClick2);
            final RadioButton rbWindowClick3 = (RadioButton)findViewById(R.id.rbWindowClick3);

            if(rbWindowClick1.isChecked()){result = 0;}
            else if(rbWindowClick2.isChecked()){result = 1;}
            else if(rbWindowClick3.isChecked()){result = 2;}

            return result;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// getLc
    /// リンククリック取得
    /// </summary>
    protected void setPrfLc(int lc)
    {
        final RadioButton rbWindowClick1 = (RadioButton)findViewById(R.id.rbWindowClick1);
        final RadioButton rbWindowClick2 = (RadioButton)findViewById(R.id.rbWindowClick2);
        final RadioButton rbWindowClick3 = (RadioButton)findViewById(R.id.rbWindowClick3);

    	try
    	{
            if(lc == 0)		{rbWindowClick1.setChecked(true);}
            else if(lc == 1){rbWindowClick2.setChecked(true);}
            else if(lc == 2){rbWindowClick3.setChecked(true);}
            else 			{rbWindowClick1.setChecked(true);}
            return;
    	}
    	catch(Exception e)
    	{
    		rbWindowClick1.setChecked(true);
    		return;
    	}

    }

    /// <summary>
    /// getScDataAutoSleep
    /// オートスリープを取得する
    /// </summary>
    protected int getScDataAutoSleep()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkAutoSleep);

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
    /// setPrfAutoSleep
    /// オートスリープを設定する
    /// </summary>
    protected void setPrfAutoSleep(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkAutoSleep);
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
    /// getAutoSleep
    /// オートウェイクアップを取得する
    /// </summary>
    protected int getScDataAutoWaikup()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkAutoWaikup);

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
    /// setPrfAutoWaikup
    /// オートウェイクアップをセットする
    /// </summary>
    protected void setPrfAutoWaikup(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkAutoWaikup);
    	try
    	{
           if(auto == 1){chk.setChecked(true);}
           else			{chk.setChecked(false); }
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getAutoSleep
    /// アイコン表示の有無を取得する
    /// </summary>
    protected int getScDataDisplayIcon()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkDisplayIcon);

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
    /// setPrfDisplayIcon
    /// アイコン表示の有無をセットする
    /// </summary>
    protected void setPrfDisplayIcon(int auto)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkDisplayIcon);
    	try
    	{
           if(auto == 1){chk.setChecked(true);}
           else			{chk.setChecked(false); }
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// <summary>
    /// getLc
    /// ウインドウカラー
    /// </summary>
    protected int getScDataWindowColor()
    {
    	try
    	{
    		final Spinner spinner = (Spinner) findViewById(R.id.spWindow);

    		if(spinner.getSelectedItem().toString().equals("カラー1")){return 0;}
    		else if(spinner.getSelectedItem().toString().equals("カラー2")){return 1;}
    		else if(spinner.getSelectedItem().toString().equals("カラー3")){return 2;}
			else if(spinner.getSelectedItem().toString().equals("カラー4")){return 3;}
			else if(spinner.getSelectedItem().toString().equals("カラー5")){return 4;}
			else if(spinner.getSelectedItem().toString().equals("カラー6")){return 5;}
			else if(spinner.getSelectedItem().toString().equals("カラー7")){return 6;}
    		else{return 0;}
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// setWindowColor
    /// ウインドウカラー
    /// </summary>
    protected void setPrfWindowColor(int idx)
    {
    	final Spinner spinner = (Spinner) findViewById(R.id.spWindow);
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
    /// getScDataWindowDis
    /// ウインドウ自動消去の有無をゲットする
    /// </summary>
    protected int getScDataWindowDis()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkWindowDis);

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
    /// setPrfDisplayIcon
    /// ウインドウ自動消去の有無をセットする
    /// </summary>
    protected void setPrfWindowDis(int windowDis)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkWindowDis);
    	try
    	{
           if(windowDis == 1){chk.setChecked(true);}
           else			{chk.setChecked(false); }
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

    /// getScDataWindowDis
    /// バッテリーシンクロの有無をゲットする
    /// </summary>
    protected int getScDataHelth()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkHelth);

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
    /// setPrfHelth
    /// バッテリーシンクロの有無をセットする
    /// </summary>
    protected void setPrfHelth(int helth)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkHelth);
    	try
    	{
           if(helth == 1){chk.setChecked(true);}
           else			{chk.setChecked(false); }
            return;
    	}
    	catch(Exception e)
    	{
    		chk.setChecked(false);
    	}
    }

}