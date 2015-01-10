//=======================================================================
//  ClassName : LiplisWidgetConfigurationTwitterUserRegist
//  概要      : ツイッターユーザーの登録画面
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

import java.util.ArrayList;
import java.util.List;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Fct.FctGUIDCreator;
import little.cute.renew.Obj.Json.RegisterTwUserInfo;
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoTw;
import little.cute.renew.Tsk.AsyncTwitterUserRegisterGetUserList;
import little.cute.renew.Tsk.AsyncTwitterUserRegisterUserAdd;
import little.cute.renew.Tsk.AsyncTwitterUserRegisterUserDel;
import little.cute.renew.View.LiplisSettingListView.LiplisSettingListArrayAdapter;
import little.cute.renew.View.LiplisSettingListView.LiplisSettingListItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LiplisWidgetConfigurationTwitterUserRegist  extends Activity {

	//アダプター
	protected static ArrayAdapter<LiplisSettingListItem> adapter;

	//表示データ
	protected List<LiplisSettingListItem> data;

	//選択ポジション
	protected int pos;

    /// <summary>
    /// onCreate
    /// 作成時処理
    /// </summary>
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //初期化
        init();

        //レイアウトのセット
        setContentView(R.layout.configurationtwitteruserregiste);

        //クローズボタンの設定
        setCloseButton();

        //ユーザー追加
        setTwitterUserAddButton();

        //ツイッターユーザー更新ボタン
        setTwitterUserUpdButton();

        //プリファレンスを読み出し、設定する
        setListView();
    }

    ///====================================================================
    ///
    ///                               初期化
    ///
    ///====================================================================

    /// <summary>
    /// init
    /// 変数等の初期化
    /// </summary>
    protected void init()
    {
    	pos = 0;
    }

    /// <summary>
    /// setTwitterUserAddButton
    /// ツイッターユーザーアッドボタンのハンドらセット
    /// </summary>
    protected void setTwitterUserAddButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnTwitterUserAdd);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				addUser();
		    }
		});
    }

    /// <summary>
    /// setTwitterUserUpdButton
    /// 更新ボタンのハンドらセット
    /// </summary>
    protected void setTwitterUserUpdButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnTwitterUserUpd);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				setListView();
				viewResult("設定情報をサーバーから取得しました。");
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
        Button closeButton = (Button) findViewById(R.id.btnTwitterUserRegistClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

	///====================================================================
	///
	///                         セット処理
	///
	///====================================================================


	/// <summary>
	/// setListView
	/// セットアダプター
	/// </summary>
	protected void setListView(){
		try
		{
			//☆非同期処理化 RSS情報を取得する
			AsyncTwitterUserRegisterGetUserList thread = new AsyncTwitterUserRegisterGetUserList(this);
			thread.execute(getUserCd());
		}
		catch(Exception e)
		{
			Log.d("Liplis setListView",e.getMessage());
		}
	}

	/// <summary>
	/// 2013/09/14 ver3.4.3
	/// setListViewChain
	/// セットアダプタのチェイン処理
	/// ☆非同期クラスから呼び出す
	/// </summary>
	public void setListViewChain(ResLpsLoginRegisterInfoTw twUserList){
		try
		{
		    //ListViewオブジェクトの取得
		    ListView listView = (ListView)findViewById(R.id.lvTwitterUserRegistList);

		    //セットデータ
		    this.data = new ArrayList<LiplisSettingListItem>();

		    //RSS情報を回してセットデータを作成する
		    for(RegisterTwUserInfo info : twUserList.twuserlist)
		    {
		    	if(info.url.length() > 0)
		    	{
		    		data.add(new LiplisSettingListItem(info.name,info.description));
		    	}
		    }

		    //ArrayAdapterオブジェクト生成
		    adapter = new LiplisSettingListArrayAdapter(this, R.layout.cuslayoutviewrow, data);

		    //リストビューセット
		    listView.setAdapter(adapter);


		    //リストビューのクリックイベント
		    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	          // 項目が長押しクリックされた時のハンドラ
	          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	  onClickListView(parent, view, position, id);
	          }
	        });
		}
		catch(Exception e)
		{
			Log.d("Liplis ResLpsLoginRegisterInfoTw",e.getMessage());
		}
	}

	/// <summary>
	/// onClickListView
	/// リストビューのクリクkイベント
	/// </summary>
	protected void onClickListView(AdapterView<?> parent, View view, int position, long id){
		//ポジションの取得
		pos = position;

		//選択項目を準備する。
	    String[] str_items = {
	            "削除",
	            "キャンセル"};

	    new AlertDialog.Builder(this)
	    .setTitle(getTargetRowName() + "選択")
	    .setItems(str_items, new DialogInterface.OnClickListener(){
	        public void onClick(DialogInterface dialog, int which) {
	            switch(which)
	            {
	            case 0:
                    //プリファレンスの取得
                    final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

                    //アクセストークンの取得
                    //トークンとシークレット取得
            	   	String token = config.getString(LiplisDefine.PREFS_TWITTER_TOKEN , "");
            	   	String secret = config.getString(LiplisDefine.PREFS_TWITTER_SECRET , "");

                    //空チェック
            	   	if(token.equals("") || secret.equals(""))
            	   	{
            	   		viewResult("ツイッターの認証登録を行なってから実行して下さい。");
            	   		return;
            	   	}

	            	//APIで削除
            		delUserAsync(token,secret);

	                break;
	            default:
	            // キャンセル

	                break;
	            }
	        }
	    }
	    ).show();
	}

    ///====================================================================
    ///
    ///                              一般処理
    ///
    ///====================================================================

	/// <summary>
	/// 2013/09/14 ver3.4.3
	/// delUserAsync
	/// 指定ユーザーを非同期処理で削除する
	/// </summary>
	public void delUserAsync(String token,String secret){
		try
		{
			//☆非同期処理化 APIで削除
			AsyncTwitterUserRegisterUserDel thread = new AsyncTwitterUserRegisterUserDel(this);
			thread.execute(getUserCd(),token,secret,getTargetRowName());
		}
		catch(Exception e)
		{
			Log.d("Liplis delUserAsync",e.getMessage());
		}
	}

	/// <summary>
	/// 2013/09/14 ver3.4.3
	/// delUserChain
	/// ユーザー削除ののチェイン処理
	/// ☆非同期クラスから呼び出す
	/// </summary>
	public void delUserChain(String res, String dsc){
		try
		{
			if(res.equals("0"))
			{
	    		//再表示
	    		setListView();

	    		viewResult(dsc + "を削除しました。");
			}
			else
			{
				//失敗
				viewResult(dsc + "の削除に失敗しました。");
			}
		}
		catch(Exception e)
		{
			Log.d("Liplis delUserChain",e.getMessage());
		}
	}

    /// <summary>
	/// <summary>
	/// addUser
	/// ユーザーを追加する
	/// </summary>
	 protected void addUser(){
	    //テキスト入力を受け付けるビューを作成します。
	    final EditText editView = new EditText(this);

	    new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_info)
	        .setTitle("ツイッターユーザー追加")
	        //setViewにてビューを設定します。
	        .setView(editView)
	        .setPositiveButton("追加", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	if(!editView.getText().toString().equals(""))
	            	{
	                    //プリファレンスの取得
	                    final SharedPreferences config = getSharedPreferences(LiplisDefine.PREFS_NAME, MODE_WORLD_WRITEABLE);

	                    //アクセストークンの取得
	                    //トークンとシークレット取得
	            	   	String token = config.getString(LiplisDefine.PREFS_TWITTER_TOKEN , "");
	            	   	String secret = config.getString(LiplisDefine.PREFS_TWITTER_SECRET , "");

	                    //空チェック
	            	   	if(token.equals("") || secret.equals(""))
	            	   	{
	            	   		viewResult("ツイッターの認証登録を行なってから実行して下さい。");
	            	   		return;
	            	   	}

	            	   	addUserAsync(getUserCd(),token, secret, editView.getText().toString());
	            	}
	            }
	        })
	        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            }
	        })
	        .show();
	 }

	 /// <summary>
	 /// 2013/09/14 ver3.4.3
	 /// addUserAsync
	 /// ユーザーを非同期処理で追加する
	 /// </summary>
	 protected void addUserAsync(String userCd, String token,String secret,String name)
	 {
		 try
		 {
			//☆非同期処理化 APIで追加
			 AsyncTwitterUserRegisterUserAdd thread = new AsyncTwitterUserRegisterUserAdd(this);
			 thread.execute(getUserCd(),token,secret ,name);
		 }
		 catch(Exception e)
		 {
		 	Log.d("Liplis addUserAsync",e.getMessage());
		 }
	 }

	/// <summary>
	/// 2013/09/14 ver3.4.3
	/// addUserChain
	/// ユーザーを追加のチェイン処理
	/// ☆非同期クラスから呼び出す
 	/// name → editView.getText()
	/// </summary>
	public void addUserChain(String res, String name)
	{
		try
		{
			if(res.equals("0"))
			{
				//再表示
			setListView();

			viewResult(name + "を追加しました。");
			}
			else
			{
				//失敗
			viewResult(name + "の追加に失敗しました。");
			}

			//再表示
			setListView();
		}
		catch(Exception e)
		{
			Log.d("Liplis addUserChain",e.getMessage());
		}
	}

	/// <summary>
	/// getTargetRowUrl
	/// 選択行のURLを取得する
	/// </summary>
	protected String getTargetRowName()
	{
		LiplisSettingListItem item =  (LiplisSettingListItem)(data.get(pos));
		String name =item.getName();

		return name;

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
}
