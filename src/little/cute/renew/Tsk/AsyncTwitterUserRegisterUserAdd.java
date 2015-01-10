//=======================================================================
//  ClassName : AsyncTwitterUserRegisterUserAdd
//  概要      : ツイッターユーザー登録処理を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationTwitterUserRegist;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncTwitterUserRegisterUserAdd extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTwitterUserRegist act;

	///=====================================
    /// 結果
	String res;
	String name;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTwitterUserRegisterUserAdd(LiplisWidgetConfigurationTwitterUserRegist act)
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
			res = LiplisApi.addTwitterUser(params[0],params[1], params[2], params[3]);
			name = params[3];
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
