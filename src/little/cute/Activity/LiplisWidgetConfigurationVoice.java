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
package little.cute.Activity;

import little.cute.R;
import little.cute.Common.LiplisDefine;
import little.cute.Widget.LiplisWidgetLarge;
import little.cute.Widget.LiplisWidgetNormal;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class LiplisWidgetConfigurationVoice extends Activity {

    protected int pitch = 30;
    protected int rate = 30;

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
        setContentView(R.layout.configurationvoice);

        //シークバーレート
        setSeekBarRate();

        //シークバーピッチ
        setSeekBarPich();

        //クローズボタンの設定
        setCloseButton();

        //セーブボタンの設定
        setSaveButton();

        //チェックボックスチェックイベント
        setChkVoice();

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
        Button closeButton = (Button) findViewById(R.id.btnConrigVoiceSave);

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
        Button closeButton = (Button) findViewById(R.id.btnConrigVoiceClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setSeekBarRate
    /// シークバーレート
    /// </summary>
    protected void setSeekBarRate()
    {
    	final TextView textView = (TextView) findViewById(R.id.txtConrigVoiceRate);

    	 SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarConrigVoiceRate);
         seekBar.setMax(100);
         seekBar.setProgress(30);
         seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
             // トラッキング開始時に呼び出されます
             public void onStartTrackingTouch(SeekBar seekBar) {

             }
             // トラッキング中に呼び出されます
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

             }
             // トラッキング終了時に呼び出されます
             public void onStopTrackingTouch(SeekBar seekBar) {
            	 textView.setText(String.valueOf(seekBar.getProgress()));
            	 rate = seekBar.getProgress();
             }
         });

    }

    /// <summary>
    /// setSeekBarPich
    /// シークバーピッチ
    /// </summary>
    protected void setSeekBarPich()
    {
    	final TextView textView = (TextView) findViewById(R.id.txtConrigVoicePitch);

    	 SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarllConrigVoicePitch);
         seekBar.setMax(100);
         seekBar.setProgress(30);
         seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
             // トラッキング開始時に呼び出されます
             public void onStartTrackingTouch(SeekBar seekBar) {

             }
             // トラッキング中に呼び出されます
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

             }
             // トラッキング終了時に呼び出されます
             public void onStopTrackingTouch(SeekBar seekBar) {
            	 textView.setText(String.valueOf(seekBar.getProgress()));
            	 pitch = seekBar.getProgress();
             }
         });
    }


    /// <summary>
    /// setChkVoice
    /// ボイス有効チェック本クスイベント
    /// </summary>
    protected void setChkVoice()
    {
        //ボタンの配置
    	CheckBox chk = (CheckBox) findViewById(R.id.chkVoice);

    	// チェックボックスがクリックされた時に呼び出されるコールバックリスナーを登録します
    	chk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//チェック状態を取得
                CheckBox checkBox = (CheckBox) v;
                if(checkBox.isChecked())
                {
                	checkBox.setChecked(false);
                	//チェックが音された場合に、ダイアログを出す。
                	showConsentScreen();
                }
            }
        });
    }

    /// <summary>
    /// showConsentScreen
    /// 同意画面を表示する
    /// </summary>
    protected void showConsentScreen()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("音声出力機能はベータ版です！");
        alertDialogBuilder.setMessage("下記注意をよくお読みになって頂き、同意して頂けた場合に使用下さい。\r\n" +
        		"1. 本機能はベータ版です\r\n" +
        		"本機能はベータ版であり、不具合を含んでいる可能性があります。\r\n" +
        		"本ソフトをご使用になった結果生じた損害について、作者は一切責任を負いません。\r\n" +
        		"もし、不具合を発見されたら、ご連絡頂けると幸いです。\r\n" +
        		"2. 本機能により、音声が出ます！\r\n" +
        		"ONにした時から、音声出力が開始されます。使用するときと場合に気をつけて下さい。\r\n" +
        		"3. 音声再生エンジンが必要です\r\n" +
        		"本機能を使用するには音声エンジンのインストールと設定が必要です。\r\n" +
        		"無料のもの、有料のものが存在します。詳しくはヘルプを御覧ください。\r\n" +
        		"4. 改善点がありましたら、ご連絡下さい\r\n" +
        		"アプリの改善を行いたいとおもいますので、お気づきの点がありましたらご連絡下さい。\r\n" +
        		"");
        // 同意する
        alertDialogBuilder.setPositiveButton("同意", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
        	CheckBox chk = (CheckBox) findViewById(R.id.chkVoice);
        	chk.setChecked(true);
        }});
        // 拒否
        alertDialogBuilder.setNegativeButton("拒否", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
        	CheckBox chk = (CheckBox) findViewById(R.id.chkVoice);
        	chk.setChecked(false);
        }});
        // アラートダイアログのキャンセルが可能かどうかを設定します
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        // アラートダイアログを表示します
        alertDialog.show();
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

        configEditor.putInt(LiplisDefine.PREFS_VOICE		, getScDataVoice());
        configEditor.putInt(LiplisDefine.PREFS_VOICE_RATE	, getScDataVoiceRate());
        configEditor.putInt(LiplisDefine.PREFS_VOICE_PITCH	, getScDataVoicePitch());

        //変更をコミット
        configEditor.commit();
    }

    protected void getPriference()
    {
        //プリファレンスの取得
        final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

        setPrfVoice(config.getInt(LiplisDefine.PREFS_VOICE, 0));
        setPrfVoice_Rate(config.getInt(LiplisDefine.PREFS_VOICE_RATE, 50));
        setPrfVoice_Pitch(config.getInt(LiplisDefine.PREFS_VOICE_PITCH, 50));
    }

    protected void setDefault()
    {
        setPrfVoice(0);
        setPrfVoice_Rate(50);
        setPrfVoice_Pitch(50);

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
    /// getScDataVoice
    /// 音声おしゃべりの可否
    /// </summary>
    protected int getScDataVoice()
    {
    	int result = 1;
    	try
    	{
            final CheckBox chk = (CheckBox)findViewById(R.id.chkVoice);

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
    /// setScDataVoice
    /// 音声のおしゃべり可否を設定する
    /// </summary>
    protected void setPrfVoice(int windowDis)
    {
    	final CheckBox chk = (CheckBox)findViewById(R.id.chkVoice);
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

    /// <summary>
    /// getScDataVoiceRate
    /// 現在の設定レートを返す
    /// </summary>
    protected int getScDataVoiceRate()
    {
    	return this.rate;
    }

    /// <summary>
    /// setSeekBarRate
    /// シークバーレート
    /// </summary>
    protected void setPrfVoice_Rate(int rate)
    {
		final TextView textView = (TextView) findViewById(R.id.txtConrigVoiceRate);
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarConrigVoiceRate);
		seekBar.setProgress(rate);
		textView.setText(String.valueOf(rate));
		this.rate = rate;
    }

    /// <summary>
    /// getScDataVoicePitch
    /// 現在の設定ピッチを返す
    /// </summary>
    protected int getScDataVoicePitch()
    {
    	return this.pitch;
    }

    /// <summary>
    /// setSeekBarPich
    /// シークバーピッチ
    /// </summary>
    protected void setPrfVoice_Pitch(int pitch)
    {
    	final TextView textView = (TextView) findViewById(R.id.txtConrigVoicePitch);
    	SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarllConrigVoicePitch);
        seekBar.setProgress(pitch);
 		textView.setText(String.valueOf(pitch));
 		this.pitch = pitch;
    }

    ///====================================================================
    ///
    ///                              一般処理
    ///
    ///====================================================================


}