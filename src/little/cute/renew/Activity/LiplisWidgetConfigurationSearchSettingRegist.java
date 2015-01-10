//=======================================================================
//  ClassName : LiplisWidgetConfigurationRssUrlRegist
//  概要      : RSSの登録画面
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : configurationrssurlregiste.xml
//
//  プリファレンスには変換値ではなく、設定値をのものを入力する!
//
//2013/03/27 ver3.3.7 RSS対応
//2013/09/14 ver3.4.3 APIレベル11以上で「android.os.NetworkOnMainThreadException」が発生する問題対応。APIアクセス非同期化
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import java.util.ArrayList;
import java.util.List;

import little.cute.renew.R;
import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Fct.FctGUIDCreator;
import little.cute.renew.Obj.Json.ResLpsTopicSearchWord;
import little.cute.renew.Obj.Json.ResLpsTopicSearchWordList;
import little.cute.renew.Tsk.AsyncSearchSettingAdd;
import little.cute.renew.Tsk.AsyncSearchSettingDel;
import little.cute.renew.Tsk.AsyncSearchSettingGetList;
import little.cute.renew.View.LiplisSearchSettingListView.LiplisSearchSettingListArrayAdapter;
import little.cute.renew.View.LiplisSearchSettingListView.LiplisSearchSettingListItem;
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
import android.widget.Spinner;
import android.widget.Toast;

public class LiplisWidgetConfigurationSearchSettingRegist  extends Activity {

	//アダプター
	protected static ArrayAdapter<LiplisSearchSettingListItem> adapter;

	//選択ポジション
	protected int pos;

	//選択ポジション
	protected int nowSelectTopicId;

	//表示データ
	protected List<LiplisSearchSettingListItem> data;

	//保持データ
	protected ResLpsTopicSearchWordList rlts;

	//ファーストステップ完了フラグ
	protected boolean flgStartUpEnd = false;

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
        setContentView(R.layout.configurationsearchsettingregiste);

        //クローズボタンの設定
        setCloseButton();

        //ユーザー追加
        setRssUrlAddButton();

        //更新
        setRssUrlUpdButton();

        //トピックスピナー
        setSpSearchSettingTopic();

        //プリファレンスを読み出し、設定する
        requestSearchSetting();

        //ファーストステップ完了
        flgStartUpEnd = true;
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
    	nowSelectTopicId = 0;
    	rlts = new 	ResLpsTopicSearchWordList();
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void setCloseButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnSearchSettingRegistClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setRssUrlAddButton
    /// RSSURLアッドボタンのハンドらセット
    /// </summary>
    protected void setRssUrlAddButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnSearchSettingAdd);

        //クリックリスナーの作成
        button.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				addUser();
		    }
		});
    }

    /// <summary>
    /// setRssUrlUpdButton
    /// 更新ボタンのハンドらセット
    /// </summary>
    protected void setRssUrlUpdButton()
    {
        //ボタンの配置
        Button button = (Button) findViewById(R.id.btnSearchSettingUpd);

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
    /// setSpSearchSettingTopic
    /// トピックスピナーのハンドラセット
    /// </summary>
    protected void setSpSearchSettingTopic()
    {
        //すぴなーの設定
        Spinner spinner = (Spinner) findViewById(R.id.spSearchSettingTopic);

        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // アイテムが選択された時の動作
            public void onItemSelected(AdapterView parent,View view, int position,long id) {
              // Spinner を取得
              Spinner spinner = (Spinner) parent;

              //0:すべて、1:ニュース、2:2ch、3:ニコニコ、4:RSS、5:ツイッターPB、6:ツイッターMy、7:ツイッター設定
              nowSelectTopicId = spinner.getSelectedItemPosition();

              //リストの再読み込み
              if(flgStartUpEnd)
              {
            	  setListView();
              }
            }

            // 何も選択されなかった時の動作
            public void onNothingSelected(AdapterView parent) {
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
	protected void requestSearchSetting(){
		try
		{
			//☆非同期処理化 RSS情報を取得する
			AsyncSearchSettingGetList thread = new AsyncSearchSettingGetList(this);
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
	public void setListViewChain(ResLpsTopicSearchWordList rlts){
		try
		{
			//データ受信
			this.rlts = rlts;

			//リストビューの再構築
			setListView();
		}
		catch(Exception e)
		{
			Log.d("Liplis setListViewChain",e.getMessage());
		}
	}


	/// <summary>
	/// setListView
	/// セットアダプター
	/// </summary>
	protected void setListView(){
		try
		{
			//ListViewオブジェクトの取得
		    ListView listView = (ListView)findViewById(R.id.lvSearchSettingRegistList);

		    //セットデータ
		    this.data = new ArrayList<LiplisSearchSettingListItem>();

		    //RSS情報を回してセットデータを作成する
		    for(ResLpsTopicSearchWord info : rlts.wordList)
		    {
		    	//現在選択中の要素なら表示
		    	if(info.topicId == nowSelectTopicId)
		    	{
		    		data.add(new LiplisSearchSettingListItem(info.topicId,info.word,info.flgEnable));
		    	}
		    }

		    //ArrayAdapterオブジェクト生成
		    adapter = new LiplisSearchSettingListArrayAdapter(this, R.layout.cuslayoutviewrow_topicset, data);

		    //リストビューのクリックイベント
		    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	          // 項目が長押しクリックされた時のハンドラ
	          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	  onClickListView(parent, view, position, id);
	          }
	        });

		    //リストビューセット
		    listView.setAdapter(adapter);

		}
		catch(Exception e)
		{
			Log.d("Liplis setListView",e.getMessage());
		}
	}

	/// <summary>
	/// onClickListView
	/// リストビューのクリックイベント
	/// </summary>
	protected void onClickListView(AdapterView<?> parent, View view, int position, long id){
		//ポジションの取得
		pos = position;

		//選択項目を準備する。
	    String[] str_items = {
	            "削除",
	            "キャンセル"};

	    new AlertDialog.Builder(this)
	    .setTitle(getTargetRowWord() + "選択")
	    .setItems(str_items, new DialogInterface.OnClickListener(){
	        public void onClick(DialogInterface dialog, int which) {
	            switch(which)
	            {
	            case 0:
	            	// 削除
	            	try
	            	{
	            		delSearchSettingAsync();
	            	}
	            	catch(Exception e)
	            	{
	            		viewResult("削除に失敗しました。");
	            	}
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
	public void delSearchSettingAsync(){
		try
		{
			//☆非同期処理化 APIで削除
			LiplisSearchSettingListItem lssli = getTargetRowData();
			AsyncSearchSettingDel thread = new AsyncSearchSettingDel(this);
			thread.execute(getUserCd(),String.valueOf(lssli.getTopicId()),lssli.getWord());
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
	public void delSearchSettingChain(String res, String topicId, String dsc){
		try
		{
			if(res.equals("0"))
			{
				requestSearchSetting();

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
	        .setTitle("検索ワード追加")
	        //setViewにてビューを設定します。
	        .setView(editView)
	        .setPositiveButton("追加", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	if(!editView.getText().toString().equals(""))
	            	{
	            		addSearchSettingAsync(getUserCd(),String.valueOf(nowSelectTopicId), editView.getText().toString().trim().replace("\n", ""),"1");
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
	 protected void addSearchSettingAsync(String userCd, String topicId, String word, String flgEnabel)
	 {
		try
		{
			//☆非同期処理化 APIで追加
			AsyncSearchSettingAdd thread = new AsyncSearchSettingAdd(this);
			thread.execute(getUserCd(),topicId,word,flgEnabel);
		}
		catch(Exception e)
		{
			Log.d("Liplis delUserChain",e.getMessage());
		}
	 }

	 /// <summary>
	 /// 2013/09/14 ver3.4.3
	 /// addUserChain
	 /// ユーザーを追加のチェイン処理
	 /// ☆非同期クラスから呼び出す
 	 /// name → editView.getText()
	 /// </summary>
	 public void addUserChain(String res, String topicId, String word, String flgEnable)
	 {
		 try
		 {
			if(res.equals("0"))
			{
				//追加データ
				rlts.wordList.add(new ResLpsTopicSearchWord(Integer.valueOf(topicId), word, Integer.valueOf(flgEnable)));

				//再表示
				setListView();

				viewResult(word + "を追加しました。");
			}
			else if(res.equals("1"))
			{

			}
			else
			{
				//失敗
				viewResult(word + "の追加に失敗しました。");
			}
		 }
		 catch(Exception e)
		 {

		 }
	 }

	 /// <summary>
	 /// 2013/09/14 ver3.5.0
	 /// checkOn
	 /// </summary>
	 public void checkOn(boolean checked)
	 {
		 //元データ取得
		 ResLpsTopicSearchWord d = getTargetRowData(getTargetRowWord());

		 //フラグの設定
		 if(d != null)
		 {
			 d.flgEnable = LiplisUtil.boolToBit(checked);
	     }

		 addSearchSettingAsync(getUserCd(),String.valueOf(nowSelectTopicId), d.word, String.valueOf(d.flgEnable));
	 }

    /// <summary>
	/// getTargetRowData
	/// 選択行のデータを取得する
	/// </summary>
	protected LiplisSearchSettingListItem getTargetRowData()
	{
		return  (LiplisSearchSettingListItem)(data.get(pos));
	}

    /// <summary>
	/// getTargetRowData
	/// 選択行の元データを取得する
	/// </summary>
	protected ResLpsTopicSearchWord getTargetRowData(String target)
	{
	    for(ResLpsTopicSearchWord info : rlts.wordList)
	    {
	    	//現在選択中の要素なら表示
	    	if(info.topicId == nowSelectTopicId && info.word.equals(target))
	    	{
	    		return info;
	    	}
	    }
	    return null;
	}

    /// <summary>
	/// getTargetRowWord
	/// 選択行のワードを取得する
	/// </summary>
	protected String getTargetRowWord()
	{
		LiplisSearchSettingListItem r = getTargetRowData();
		return r.getWord();
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
