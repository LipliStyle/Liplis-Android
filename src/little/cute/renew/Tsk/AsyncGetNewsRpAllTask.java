//=======================================================================
//  ClassName : asyncGetNewsTask
//  概要      : ニュース収集を非同期で実行する
//
//  シリアル化: LiplisNews
//	extends   :
//	impliments:
//
//2013/03/01 ver3.2.0 LiplisNewsシリアライズ保存
//2013/03/03 ver3.3.1 一定期間経過後、ジャンル変更時、キューリフレッシュ
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Tsk;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.http.NameValuePair;

import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Web.LiplisApi;
import little.cute.renew.Web.LiplisNews;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncGetNewsRpAllTask extends AsyncTask<String, Integer, Long> implements OnCancelListener{

	  ///=============================
	  /// プロパティ
	  private final String TAG = "asyncGetNewsTask";
	  private LiplisNews lpsnews;
	  private Context context;
	  private List<NameValuePair> nameValuePair;
	  private boolean flgSuccess = false;


	  /// <summary>
	  /// asyncGetNewsTask
	  /// コンストラクター
	  /// 呼び出し順序:1
	  /// </summary>
	  public AsyncGetNewsRpAllTask(LiplisNews lpsnews,Context context, List<NameValuePair> nameValuePair){
		this.context = context;
	    this.lpsnews = lpsnews;
	    this.nameValuePair = nameValuePair;
	  }

	  /// <summary>
	  /// onPreExecute
	  /// UIスレッドでの実行
	  /// 呼び出し順序:2
	  /// </summary>
	  @Override
	  protected void onPreExecute() {

	  }

	  /// <summary>
	  /// doInBackground
	  /// ワーカースレッドで呼び出させる処理
	  /// 呼び出し順序:3
	  ///
	  /// ここで渡される引数は、AsyncTaskをExtendsするときの1つ目のパラメータ
	  ///
	  /// </summary>
	  @Override
	  protected Long doInBackground(String... params) {
		//キャンセルチェック
		if(isCancelled()){
			  return -1L;
		}
		try
		{
			//新規キューの作成
			Queue<MsgShortNews> newsQueue = new ArrayBlockingQueue<MsgShortNews>(LiplisDefine.LPS_NEWS_QUEUE);

			List<MsgShortNews> lst = LiplisApi.getShortNewsList(nameValuePair, 0);

			//lstのnullチェック ver3.3.2
			if(lst != null)
			{
				//ニュースリストを取得し、キューに入れる
				newsQueue.addAll(lst);
				//キューのリプレース
				lpsnews.newsQueue = newsQueue;

				//成功
				flgSuccess = true;
				return 1L;
			}
			else
			{
				flgSuccess = false;
				return -1L;
			}

		}
		catch(Exception e)
		{
			Log.d(TAG, "doInBackground - " + e.toString());
			flgSuccess = false;
			return -1L;
		}
	  }


	  /// <summary>
	  /// onProgressUpdate
	  /// ワーカースレッドでpublishProgressが呼び出されたとき、UIスレッドから呼び出される
	  /// 呼び出し順序:4?
	  ///
	  /// ここで渡される引数は、AsyncTaskをExtendsするときの2つ目のパラメータ
	  ///
	  /// </summary>
	  @Override
	  protected void onProgressUpdate(Integer... values) {
	    Log.d(TAG, "onProgressUpdate - " + values[0]);
	  }

	  /// <summary>
	  /// onCancelled
	  ///「doInBackground」が終了していない段階でキャンセルされたことにより、抜けた場合、戻り値をパラメーターとして渡して呼ばれる。
	  /// 呼び出し順序:6'
	  ///
	  /// </summary>
	  @Override
	  protected void onCancelled() {
	    Log.d(TAG, "onCancelled");
	  }

	  /// <summary>
	  /// onPostExecute
	  /// 「doInBackground」が終了したとき、戻り値をパラメーターとして渡して呼ばれる。
	  /// 呼び出し順序:5
	  ///
	  /// ここで渡される引数は、AsyncTaskをExtendsするときの3つ目のパラメータ
	  ///
	  /// </summary>
	  @Override
	  protected void onPostExecute(Long result) {
		  if(flgSuccess)
		  {
			  Log.d(TAG, "AsyncGetNewsRpAllTask_ダウンロード完了 " + result);

			  //ダウンロード成功したらセーブする
			  lpsnews.saveLiplisNews(context);
		  }
		  else
		  {
			  Log.d(TAG, "AsyncGetNewsRpAllTask_ダウンロード失敗 " + result);
		  }


	  }

	  /// <summary>
	  /// onPostExecute
	  /// キャンセルがONになったときに呼ばれる
	  /// 呼び出し順序:5'
	  ///
	  /// </summary>
	  public void onCancel(DialogInterface dialog) {
	    Log.d(TAG, "Dialog onCancell... calling cancel(true)");
	    this.cancel(true);
	  }
}
