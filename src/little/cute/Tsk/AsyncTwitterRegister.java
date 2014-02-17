//=======================================================================
//  ClassName : AsyncTwitterRegister
//  概要      : ツイッター登録を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Tsk;

import little.cute.Activity.LiplisWidgetConfigurationTwitterRegist;
import little.cute.Fct.FctTwitterObject;
import little.cute.Obj.ObjLpsBean;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;

public class AsyncTwitterRegister extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTwitterRegist act;
	Twitter twitter;
	RequestToken requestToken;
	ObjLpsBean bean;
;
	///=====================================
    /// 結果リスト
	boolean res;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTwitterRegister(LiplisWidgetConfigurationTwitterRegist act,ObjLpsBean bean)
	{
		this.act = act;
		this.bean = bean;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	public Long doInBackground(String... params) {

    	FctTwitterObject.initTwitterObject(bean);
    	twitter = bean.getTwitter();
    	requestToken = bean.getRequestToken();

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	public void onPostExecute(Long result) {
		act.initTwitterObjectCain(this.twitter,this.requestToken);
	}
}
