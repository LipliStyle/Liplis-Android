//=======================================================================
//  ClassName : AsyncSearchSettingGetList
//  概要      : 検索設定リスト取得処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationSearchSettingRegist;
import little.cute.renew.Obj.Json.ResLpsTopicSearchWordList;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncSearchSettingGetList extends AsyncTask<String, Integer, Long>  {

	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationSearchSettingRegist act;

	///=====================================
    /// 結果リスト
	ResLpsTopicSearchWordList wordList;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncSearchSettingGetList(LiplisWidgetConfigurationSearchSettingRegist act)
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
			wordList = LiplisApi.getSearchSettingList(params[0]);
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.setListViewChain(wordList);
	}
}
