//=======================================================================
//  ClassName : AsyncTwitterInputPinCode
//  概要      : ツイッターピンコード入力を非同期で実行する
//
//	extends   :AsyncTask<String, Integer, Long>
//	impliments:
//
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationTwitterRegist;
import little.cute.renew.Web.LiplisApi;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;

public class AsyncTwitterInputPinCode extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTwitterRegist act;
	String pinCode;
	String userCode;
	Twitter twitter;
	AccessToken accessToken;
	RequestToken requestToken;


	///=====================================
    /// 結果リスト
	boolean res;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTwitterInputPinCode(LiplisWidgetConfigurationTwitterRegist act, Twitter twitter, RequestToken requestToken,String userCode,String pinCode)
	{
		this.act = act;
		this.twitter = twitter;
		this.requestToken = requestToken;
		this.userCode = userCode;
		this.pinCode = pinCode;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	protected Long doInBackground(String... params) {
		try{
			accessToken = twitter.getOAuthAccessToken(requestToken, pinCode);

			//レジストする
			res = LiplisApi.registerUser(userCode,accessToken.getToken(),accessToken.getTokenSecret());

			return null;
		} catch (TwitterException te) {

		}
		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	protected void onPostExecute(Long result) {

		act.inputPinCodeChain(res,accessToken);
	}
}