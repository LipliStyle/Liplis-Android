//=======================================================================
//  ClassName : AsyncTopicSetting
//  概要      : 話題設定取得処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Tsk;

import little.cute.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncTopicSetting extends AsyncTask<String, Integer, Long>  {

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTopicSetting()
	{

	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		if(params.length == 12)
		{
			LiplisApi.saveTopicSetting(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7],params[8],params[9],params[10],params[11]);
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {

	}

}
