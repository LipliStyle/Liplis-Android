//=======================================================================
//  ClassName : LiplisWidgetTwitterRegist
//  概要      : ツイッターの登録画面
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : twitterconfiguration.xml
//
//  プリファレンスには変換値ではなく、設定値をのものを入力する!
//
//2013/03/27 ver3.3.6 Twitter対応
//2013/03/27 ver3.4.3 Twitter認証関連処理を非同期化
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Fct.FctGUIDCreator;
import little.cute.renew.Obj.ObjLpsBean;
import little.cute.renew.Tsk.AsyncTwitterGetTwitterObject;
import little.cute.renew.Tsk.AsyncTwitterInputPinCode;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LiplisWidgetConfigurationTwitterRegist  extends Activity {

	 //このファクトリインスタンスは再利用可能でスレッドセーフです
	 public Twitter twitter;
	 public RequestToken requestToken;
	 public AccessToken accessToken;

    /// <summary>
    /// onCreate
    /// 作成時処理
    /// </summary>
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //レイアウトのセット
        setContentView(R.layout.configurationtwitterregiste);

        //クローズボタンの設定
        setCloseButton();

        //ピンコードの登録ボタン
        setPinCodeButton();

        //ツイッターオブジェクトの初期化
        initTwitterObject();
    }

    ///====================================================================
    ///
    ///                               初期化
    ///
    ///====================================================================

    /// <summary>
    /// initTwitterObject
    /// ツイッターオブジェクトの初期化
    /// </summary>
    protected void initTwitterObject()
    {


    	ObjLpsBean bean = null;
    	try
    	{
    		bean = (ObjLpsBean)this.getApplication();
    	}
    	catch(Exception e)
    	{
    		bean = new ObjLpsBean();
    	}

    	AsyncTwitterGetTwitterObject act = new AsyncTwitterGetTwitterObject(this,bean);
    	act.execute();

    }

    /// <summary>
    /// initTwitterObject
    /// ツイッターオブジェクトの初期化
    /// </summary>
    public void initTwitterObjectCain(Twitter twitter, RequestToken requestToken)
    {
    	this.twitter = twitter;
    	this.requestToken = requestToken;
    	this.accessToken = new AccessToken("","");

        //ツイッター登録ページを表示する
        twitterRegister();
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void setCloseButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnTwitterRegistClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setStartTwitteButton
    /// ピンコード登録ボタンのハンドらセット
    /// </summary>
    protected void setPinCodeButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnInputPinCode);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				inputPinCodeAsync(getInputPinCode());
		    }
		});
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
		 twitter = null;
		 requestToken = null;
		 accessToken = null;
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
        configEditor.putString(LiplisDefine.PREFS_TWITTER_TOKEN		, accessToken.getToken());
        configEditor.putString(LiplisDefine.PREFS_TWITTER_SECRET	, accessToken.getTokenSecret());


        //変更をコミット
        configEditor.commit();
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

    ///====================================================================
    ///
    ///                         ツイッター認証
    ///
    ///====================================================================

    /// <summary>
    /// twitterRegister
    /// ツイッター登録
    /// </summary>
    protected void twitterRegister()
    {
    	try
    	{
    		//アクセストークンが取得できなければ、ピンコード入力を要請する
    	    if(accessToken.getToken().equals(""))
    	    {
    	    	//アクセストークンのリセット
    	    	accessToken = null;

    	    	Log.d("twitterRegister", requestToken.getAuthorizationURL());

    	    		    	//リクエストトークンURLを取得し、ブラウザを起動する
    	    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthorizationURL()));
    	    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	    	startActivity(intent);
    	    }
    	}
    	catch(Exception e)
    	{
    		viewResult("アクセストークンの取得に失敗しました。もう一度やり直して下さい。");
    	}
    }

    /// <summary>
    /// inputPinCode
    /// ピンコードをツイッターオブジェクトに入力する
    /// </summary>
    protected void inputPinCodeAsync(String pin)
    {
    	if(pin.length() > 0){


			//☆非同期処理化 ピンコード入力をする
			AsyncTwitterInputPinCode thread = new AsyncTwitterInputPinCode(this,this.twitter, this.requestToken,getUserCd(),pin);
			thread.execute();
		}else{
			viewResult("ピンコードを入力してください。");
		}

    }

    /// <summary>
    /// inputPinCodeChain
    /// ピンコード非同期取得処理
    /// </summary>
    public void inputPinCodeChain(boolean res,AccessToken accessToken)
    {
		//API実行。登録
		if(res)
		{
			this.accessToken = accessToken;
			setPriference();
			viewResult("正常に登録できました。一度、話題設定画面を起動し直すと、ユーザーリスト登録画面が開けます。");


//			try {
//				this.accessToken = twitter.getOAuthAccessToken(this.requestToken, "oauth_verifier");
//				setPriference();
//				viewResult("正常に登録できました。一度、話題設定画面を起動し直すと、ユーザーリスト登録画面が開けます。");
//			} catch (TwitterException e) {
//				viewResult("ツイッター認証に失敗しました。正しいコードを入力してください。");
//			}
		}
		else
		{
			viewResult("ツイッター認証に失敗しました。正しいコードを入力してください。");
		}

		//終了する
		finish();
    }

    /// <summary>
    /// getInputPinCode
    /// 入力ピンコードを取得する
    /// </summary>
    protected String getInputPinCode()
    {
    	EditText editText = (EditText) findViewById(R.id.edPinCode);
    	return editText.getText().toString();
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
