//=======================================================================
//  ClassName : AsyncTwitterGetUserList
//  概要      : ツイッターユーザーリストを非同期で実行する
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
import little.cute.renew.Obj.Json.ResLpsLoginRegisterInfoTw;
import little.cute.renew.Web.LiplisApi;
import android.os.AsyncTask;

public class AsyncTwitterUserRegisterGetUserList extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTwitterUserRegist act;

	///=====================================
    /// 結果リスト
	ResLpsLoginRegisterInfoTw twUserList;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTwitterUserRegisterGetUserList(LiplisWidgetConfigurationTwitterUserRegist act)
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
			twUserList = LiplisApi.getTwitterUserList(params[0]);
		}

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {
		act.setListViewChain(twUserList);
	}
}
