//=======================================================================
//  ClassName : FctTwitterObject
//  概要      : ツイッターオブジェクトファクトリー
//
//	extends   :
//	impliments:
//
//2013/03/27 ver3.3.6 ツイッターオブジェクトを共通ビーンに持たせる。
//                    本クラスでは共通ビーンに保持するオブジェクトを作成する。
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Fct;

import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Obj.ObjLpsBean;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class FctTwitterObject {

    /// <summary>
    /// initTwitterObject
    /// ツイッターオブジェクトの初期化
    /// </summary>
    public static void initTwitterObject(ObjLpsBean bean)
    {
    	try
    	{
       	 	// このファクトリインスタンスは再利用可能でスレッドセーフです
        	Twitter twitter = TwitterFactory.getSingleton();
        	RequestToken rt = null;

    	    //アプリケーションキーを設定する
    	    twitter.setOAuthConsumer(LiplisDefine.TWITTER_OAUTH_CONSUMERKEY, LiplisDefine.TWITTER_OAUTH_CONSUMERSECRET);

    	    //リクエストトークンを作成する
    	    try {
    	    	rt = twitter.getOAuthRequestToken();
    		} catch (TwitterException e) {
    			// TODO 自動生成された catch ブロック
    			//e.printStackTrace();
    		}

    	    bean.setTwitter(twitter);
    	    bean.setRequestToken(rt);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }

    /// <summary>
    /// setAccessToken
    /// アクセストークンのセット
    /// </summary>
    public static void setAccessToken(ObjLpsBean bean,String token, String secret)
    {
    	@SuppressWarnings("unused")
		AccessToken at = bean.getAccessToken();
    	at = new AccessToken(token,secret);
    }

}
