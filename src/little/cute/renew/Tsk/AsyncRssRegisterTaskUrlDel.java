//=======================================================================
//  ClassName : AsyncRssRegisterTaskUrlDel
//  概要      : RSSURL削除処理を非同期で実行する
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

public class AsyncRssRegisterTaskUrlDel extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationRssUrlRegist act;

	///=====================================
    /// 結果
	String res;
	String dsc;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncRssRegisterTaskUrlDel(LiplisWidgetConfigurationRssUrlRegist act)
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
			res = LiplisApi.delRssUrl(params[0],params[1]);
			dsc = params[1];
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.delUserChain(res,dsc);
	}
}
