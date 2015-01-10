//=======================================================================
//  ClassName : AsyncSearchSettingDel
//  概要      : 検索設定削除処理を非同期で実行する
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
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncSearchSettingDel extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationSearchSettingRegist act;

	///=====================================
    /// 結果
	String res;
	String topicId;
	String word;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncSearchSettingDel(LiplisWidgetConfigurationSearchSettingRegist act)
	{
		this.act = act;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		if(params.length == 3)
		{
			res = LiplisApi.delSearchWord(params[0],params[1],params[2]);
			topicId = params[1];
			word = params[2];
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.delSearchSettingChain(res,topicId,word);
	}
}
