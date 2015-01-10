//=======================================================================
//  ClassName : LiplisWidgetLog
//  概要      : リプリスの発言ログ
//
//	extends   : Activity
//	impliments:
//
//	レイアウトファイル : liplislog.xml
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Activity;

import java.util.ArrayList;
import java.util.List;

import little.cute.renew.R;
import little.cute.renew.Obj.ObjLiplisLog;
import little.cute.renew.Obj.ObjLiplisLogList;
import little.cute.renew.View.LiplisLogListView.LiplisArrayAdapter;
import little.cute.renew.View.LiplisLogListView.LiplisViewListItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class LiplisWidgetLog  extends Activity{

	protected static ObjLiplisLogList ol;
	protected static List<ObjLiplisLog> loglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//親クラスの
        super.onCreate(savedInstanceState);

        //レイアウトのセット
        setContentView(R.layout.liplislog);

        //クローズボタンのセット
        setCloseButton();

        //ろぐりすとの取得
        loglist = getLogObject();

        //リストビューの初期化
        setListView(loglist);
    }

  /// <summary>
    /// getLogObject
    /// 渡されたログオブジェクトを取得する
    /// </summary>
    protected List<ObjLiplisLog> getLogObject()
    {
    	ol = new ObjLiplisLogList();
    	Bundle extras=getIntent().getExtras();
    	if (extras!=null) {
    		ol = (ObjLiplisLogList)extras.getSerializable("LOGLIST");
    		return ol.getLogList();
    	}
    	else
    	{
    		return new ArrayList<ObjLiplisLog>();
    	}
    }

    /// <summary>
    /// setCloseButton
    /// クローズボタンのハンドらセット
    /// </summary>
    protected void setCloseButton()
    {
        //ボタンの配置
        Button closeButton = (Button) findViewById(R.id.btnIntroClose);

        //クリックリスナーの作成
        closeButton.setOnClickListener(new OnClickListener() {
		  	//クリックイベント
			public void onClick(View v) {
				finish();
		    }
		});
    }

    /// <summary>
    /// setListView
    /// リストビューの初期化
    /// </summary>
    protected void setListView(List<ObjLiplisLog> logList)
    {
    	ArrayList<LiplisViewListItem> data = new ArrayList<LiplisViewListItem>();
    	LiplisArrayAdapter adapter;

        for(ObjLiplisLog log:logList)
        {
        	data.add(0, new LiplisViewListItem(log.getLog()));
        }
        // アイテムを追加します
        adapter = new LiplisArrayAdapter(this, 0, data);

        ListView listView = (ListView) findViewById(R.id.lstLog);
        // アダプターを設定します
        listView.setAdapter(adapter);
        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

            	//ログの取得
            	ObjLiplisLog oll = ol.getLog(loglist.size() - position - 1);

            	//タイプの判定
            	if(oll.getType() == 1)
            	{
            		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(oll.getUrl())));
            	}

//                ListView listView = (ListView) parent;
//                // クリックされたアイテムを取得します
//                String item = (String) listView.getItemAtPosition(position);
//                Toast.makeText(LiplisWidgetLog.this, item, Toast.LENGTH_LONG).show();
            }
        });

    }
}
