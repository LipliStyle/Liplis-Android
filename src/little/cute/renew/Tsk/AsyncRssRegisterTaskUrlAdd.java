//=======================================================================
//  ClassName : AsyncRssRegisterTaskUrlAdd
//  概要      : RSSURL登録処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationRssUrlRegist;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncRssRegisterTaskUrlAdd extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationRssUrlRegist act;

	///=====================================
    /// 結果
	String res;
	String name;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncRssRegisterTaskUrlAdd(LiplisWidgetConfigurationRssUrlRegist act)
	{
		this.act = act;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		if(params.length == 2)
		{
			res = LiplisApi.addRssUrl(params[0],params[1]);
			name = params[1];
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.addUserChain(res,name);
	}
}
