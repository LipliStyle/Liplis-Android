//=======================================================================
//  ClassName : AsyncGetOnetimePasswordTaskl
//  概要      : ワンタイムパスワード取得処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationTopic;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncGetOnetimePasswordTask extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTopic act;

	///=====================================
    /// 結果リスト
	String oneTimePass;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncGetOnetimePasswordTask(LiplisWidgetConfigurationTopic act)
	{
		this.act = act;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		//ワンタイムパスワードを取得する
		if(params.length == 1)
		{
			oneTimePass = LiplisApi.getOnetimePass(params[0]);
		}
		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.getOnetimePasswordChain(oneTimePass);
	}
}
