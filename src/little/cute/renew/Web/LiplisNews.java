//=======================================================================
//  ClassName : LiplisNews
//  概要      : リプリスニュース管理クラス
//
//	extends   : LiplisHttpPost
//	impliments:
//
//2013/03/01 ver3.1.0 ニュースソースをLiplisNewsで管理する方式に変更
//2013/10/09 ver3.5.0 話題がなくなった時の振る舞い追加
//
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Web;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Msg.MsgShortNews;
import little.cute.renew.Ser.SerialLiplisNews;
import little.cute.renew.Tsk.AsyncGetNewsRpAllTask;
import little.cute.renew.Tsk.AsyncGetNewsTask;
import little.cute.renew.Tsk.AsyncGetNewsTaskOne;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;


public class LiplisNews implements Serializable {

	///=============================
    ///シリアライズドID
	private static final long serialVersionUID = 1L;

	///=============================
    ///定数
	private static final long UPDATE_INTERVAL =    60000;
	private static final long REFLESH_INTERVAL = 1200000;

	///=============================
    ///プロパティ
	public Queue<MsgShortNews> newsQueue;
	private long prevTime;

    /// <summary>
    /// LiplisNews
    /// コンストラクター
    /// </summary>
	public LiplisNews(Context context)
	{
		//キューの初期化
		newsQueue = new ArrayBlockingQueue<MsgShortNews>(LiplisDefine.LPS_NEWS_QUEUE);
		init();
	}

    /// <summary>
    /// init
    /// 初期化
    /// </summary>
	public void init()
	{
		prevTime = 0;
	}

    /// <summary>
    /// getShortNews
    /// </summary>
	public MsgShortNews getShortNews(Context context, List<NameValuePair> nameValuePair, int lngCode) {

		if(newsQueue.size() > 0)
		{
			Log.d("LiplisNews", "getShortNews" + this.newsQueue.size());
			return createShortNews(nameValuePair);
		}
		else
		{
			Log.d("LiplisNews", "getShortNews キューが枯渇 " + this.newsQueue.size());
			MsgShortNews result = new MsgShortNews();

			try
			{
				result = LiplisApi.getShortNews(nameValuePair, lngCode);

				if(result == null){
					return zaorikuLiplisNews(context);
				}
			}
			catch(Exception e)
			{
				return zaorikuLiplisNews(context);
			}


			return result;
		}
	}

    /// <summary>
    /// createShortNews
    /// </summary>
	public MsgShortNews createShortNews(List<NameValuePair> nameValuePair){
		try
		{
			//キューからプル
			return newsQueue.poll();
		}
		catch(Exception e)
		{
			return LiplisApi.getShortNews(nameValuePair, 0);
		}
	}

	/// <summary>
    /// zaorikuLiplisNews
	/// newsQueueを復活させて返す
    /// </summary>
	public MsgShortNews zaorikuLiplisNews(Context context){
		try
		{
			//ニュースオブジェクトを読み込む
			LiplisNews ln = SerialLiplisNews.loadObject(context);

			//ニュースキューを復活させる
			this.newsQueue = ln.newsQueue;

			//ザオリクチェック
			Log.d("LiplisNews", "zaorikuLiplisNews" + this.newsQueue.size());

			//1個プルして返す
			return newsQueue.poll();
		}
		catch(Exception e)
		{
			return null;
		}
	}

    /// <summary>
    /// loadLiplisNews
	/// newsQueueをロードする
    /// </summary>
	public void loadLiplisNews(Context context){
		//ニュースオブジェクトを読み込む
		LiplisNews ln = SerialLiplisNews.loadObject(context);

		//ニュースキューを復活させる
		this.newsQueue = ln.newsQueue;
	}


    /// <summary>
    /// saveLiplisNews
	/// LiplisNewsをセーブする
    /// </summary>
	public void saveLiplisNews(Context context){
		SerialLiplisNews.saveObject(context, this);
	}

	/// <summary>
    /// checkNewsQueue
	/// ニュースキューをチェックする
    /// </summary>
	public void checkNewsQueue(Context context,List<NameValuePair> nameValuePair)
	{
		//れフレッシュインターバルを超えたらキューをすべて入れ替える
		if(System.currentTimeMillis() - prevTime > REFLESH_INTERVAL)
		{
			refleshNewsQueue(context, nameValuePair);
		}

		//保持カウントを下回ったら補充する
		if(newsQueue.size() < LiplisDefine.LPS_NEWS_QUEUE_HOLD_CNT)
		{
			if(System.currentTimeMillis() - prevTime > UPDATE_INTERVAL)
			{
				//前回実行時刻の設定
				prevTime = System.currentTimeMillis();

				//非同期データ収集
				new AsyncGetNewsTask(this, context, nameValuePair).execute("");
			}
		}
	}

	/// <summary>
    /// refleshNewsQueue
	/// ニュースキューをリフレッシュする
    /// </summary>
	public void refleshNewsQueue(Context context,List<NameValuePair> nameValuePair)
	{
		 Log.d("LiplisNews", "AsyncGetNewsRpAllTask_リフレッシュ");

		//保持カウントを下回ったら補充する
		//前回実行時刻の設定
		prevTime = System.currentTimeMillis();

		//非同期データ収集
		new AsyncGetNewsRpAllTask(this, context, nameValuePair).execute("");
	}

	/// <summary>
    /// checkNewsQueueCount
	/// ニュースキューのカウントチェック
	/// キューに残量があればture,無ければfalseを返す。
    /// </summary>
	public boolean checkNewsQueueCount(Context context,List<NameValuePair> nameValuePair)
	{
		//ニュースキューチェック
		checkNewsQueue(context,nameValuePair);

		return this.newsQueue.size() > 0;
	}

	/// <summary>
    /// setOneNews
	/// 1個のニュースをセットする
    /// </summary>
	public boolean setOneNews(Context context,List<NameValuePair> nameValuePair)
	{
		new AsyncGetNewsTaskOne(this, context, nameValuePair).execute("");

		return true;
	}
}
