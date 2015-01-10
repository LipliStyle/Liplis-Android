//=======================================================================
//  ClassName : ObjLpsBean
//  概要      : リプリスビーン
//
//	extends   : Application
//	impliments:
//
//2013/03/27 ver3.3.6 共通ビーンオブジェクト作成。まずはツイッターオブジェクトを個々に持たせる
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Application;

public class ObjLpsBean  extends Application {
	 //このファクトリインスタンスは再利用可能でスレッドセーフです
	 private Twitter twitter;
	 private RequestToken requestToken;
	 private AccessToken accessToken;

	public final Twitter getTwitter() {
		return twitter;
	}
	public final void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	public final RequestToken getRequestToken() {
		return requestToken;
	}
	public final void setRequestToken(RequestToken requestToken) {
		this.requestToken = requestToken;
	}
	public final AccessToken getAccessToken() {
		return accessToken;
	}
	public final void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

}
