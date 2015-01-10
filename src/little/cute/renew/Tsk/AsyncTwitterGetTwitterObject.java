package little.cute.renew.Tsk;

import little.cute.renew.Activity.LiplisWidgetConfigurationTwitterRegist;
import little.cute.renew.Fct.FctTwitterObject;
import little.cute.renew.Obj.ObjLpsBean;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;

public class AsyncTwitterGetTwitterObject extends AsyncTask<String, Integer, Long>  {
	///=====================================
    /// アクティビティ
	LiplisWidgetConfigurationTwitterRegist act;
	Twitter twitter;
	RequestToken requestToken;
	ObjLpsBean bean;

	///=====================================
    /// 結果リスト
	boolean res;

	/// <summary>
	/// コンストラクター
	/// </summary>
	public AsyncTwitterGetTwitterObject(LiplisWidgetConfigurationTwitterRegist act,ObjLpsBean bean)
	{
		this.act = act;
		this.bean = bean;
	}

	/// <summary>
	/// doInBackground
	/// メインの非同期処理
	/// </summary>
	@Override
	public Long doInBackground(String... params) {

    	FctTwitterObject.initTwitterObject(bean);
    	twitter = bean.getTwitter();
    	requestToken = bean.getRequestToken();

		return null;
	}

	/// <summary>
	/// onPostExecute
	/// 終了時処理の記述
	/// </summary>
	@Override
	public void onPostExecute(Long result) {
		act.initTwitterObjectCain(this.twitter,this.requestToken);
	}
}
