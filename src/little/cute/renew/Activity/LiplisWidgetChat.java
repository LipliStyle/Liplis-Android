//=======================================================================
//  ClassName : LiplisWidgetChat
//  概要      : リプリスの会話ウインドウ。
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : liplislog.xml
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2015 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import java.util.List;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Obj.ObjChatTalkStatic;
import little.cute.renew.Obj.ObjLiplisLog;
import little.cute.renew.Obj.ObjLiplisLogList;
import little.cute.renew.Widget.LiplisWidget;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LiplisWidgetChat  extends Activity{

	protected InputMethodManager inputMethodManager;
	protected static ObjLiplisLogList ol;
	protected static List<ObjLiplisLog> loglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //タイトルバー非表示
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //レイアウトのセット
        setContentView(R.layout.liplischat);

        //クローズボタンのセット
        seSendButton();

        //エディットボタンのイベントセット
        setEditBox();

        //画面クローズセット
        setClose();
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void seSendButton()
    {
        //ボタンの配置
        Button sendButton = (Button) findViewById(R.id.btnChatSend);

        //クリックリスナーの作成
        sendButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				//話しかけ処理
				chatTalk();
		    }
		});
    }

    /// <summary>
    /// setEditBox
    /// エディットボックスのイベントハンドラセット
    /// </summary>
    protected void setEditBox()
    {
    	//テキストインスタンス取得
		EditText txtChatSendText = (EditText) findViewById(R.id.txtChatSendText);

		//キーボードマネージャーの有効化
		inputMethodManager =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);


		txtChatSendText.setOnKeyListener(new OnKeyListener() {
			//コールバックとしてonKey()メソッドを定義
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				//イベントを取得するタイミングには、ボタンが押されてなおかつエンターキーだったときを指定
				if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
					chatTalk();
					return true;
				}
				return false;
			}
		});

    }

    /// <summary>
    /// setText
    /// テキストをグローバルにセット
    /// </summary>
    protected void setText()
    {
		//テキストインスタンス取得
		EditText txtChatSendText = (EditText) findViewById(R.id.txtChatSendText);

    	//スタティックグローバルインスタンス取得
    	ObjChatTalkStatic octs = ObjChatTalkStatic.getInstance();

    	//スタティックグローバルに値を渡す
    	octs.setSendText(txtChatSendText.getText().toString());

    	//テキストを空にする
    	txtChatSendText.setText("");
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void setClose()
    {
        //ボタンの配置
    	LinearLayout ll = (LinearLayout) findViewById(R.id.llChat);

        //クリックリスナーの作成
        ll.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// chatTalk
    /// 話しかけ処理
    /// </summary>
    protected void chatTalk()
    {
    	//テキストをグローバルにセット
		setText();

		//インテントのインスタンス取得
		Intent intentWidget = new Intent(getApplicationContext(), LiplisWidget.class);

		//アクション定義
    	intentWidget.setAction(LiplisDefine.LIPLIS_CLICK_ACTION_CHAT_TALK);

    	//ブロキャス
    	sendBroadcast(intentWidget);
    }
}
