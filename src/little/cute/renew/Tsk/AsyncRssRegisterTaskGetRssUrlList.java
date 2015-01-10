//=======================================================================
//  ClassName : AsyncRssRegisterTaskGetRssUrlList
//  概要      : RSSURLリスト取得処理を非同期で実行する
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
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoRss;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncRssRegisterTaskGetRssUrlList extends AsyncTask<String, Integer, Long>  {

	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationRssUrlRegist act;

	///=====================================
    /// 結果リスト
	ResLpsLoginRegisterInfoRss rssInfo;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncRssRegisterTaskGetRssUrlList(LiplisWidgetConfigurationRssUrlRegist act)
	{
		this.act = act;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		if(params.length == 1)
		{
			rssInfo = LiplisApi.getRssUrlList(params[0]);
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.setListViewChain(rssInfo);
	}
}
