//=======================================================================
//  ClassName : AsyncSearchSettingGetList
//  概要      : 検索設定登録処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Tsk;

import little.cute.Activity.LiplisWidgetConfigurationSearchSettingRegist;
import little.cute.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncSearchSettingAdd extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationSearchSettingRegist act;

	///=====================================
    /// 結果
	String res;
	String topicId;
	String word;
	String flgEnable;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncSearchSettingAdd(LiplisWidgetConfigurationSearchSettingRegist act)
	{
		this.act = act;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		if(params.length == 4)
		{
			res = LiplisApi.addSearchWord(params[0],params[1],params[2],params[3]);
			topicId = params[1];
			word = params[2];
			flgEnable = params[3];
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.addUserChain(res,topicId,word,flgEnable);
	}
}
