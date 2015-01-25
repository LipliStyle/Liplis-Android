//=======================================================================
//  ClassName : LiplisApi_21
//  概要      : Liplisショートニュース
//
//  Liplisシステム
//  Copyright(c) 2010-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class LiplisHttpPost {
    ///====================================================================
    ///
    ///                         HTTP-POST
    ///
    ///====================================================================
	public static HttpResponse post(String pUrl, List<NameValuePair> nameValuePair)
	{
        try
        {

            HttpClient httpclient = new DefaultHttpClient();				//クライアント
            HttpParams httpParamsObj = httpclient.getParams();				//httpパラメーター
            HttpConnectionParams.setConnectionTimeout(httpParamsObj, 4500);	//タイムアウト設定
            HttpConnectionParams.setSoTimeout(httpParamsObj,4500);			//タイムアウト設定
            HttpPost httppost = new HttpPost(pUrl);							//ポスト


            //POST送信
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair, "utf-8"));
            HttpResponse response = httpclient.execute(httppost);

            //サーバーからの応答を取得
            return response;

	    } catch (MalformedURLException e) {
	        //System.err.println("Invalid URL format: " + urlString);
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisHttpPost", e.toString());
	        return null;
	    }
	}

	public static HttpResponse postNOP(String pUrl)
	{
        try
        {
            HttpClient httpclient = new DefaultHttpClient();				//クライアント
            HttpParams httpParamsObj = httpclient.getParams();				//httpパラメーター
            HttpConnectionParams.setConnectionTimeout(httpParamsObj, 4500);	//タイムアウト設定
            HttpConnectionParams.setSoTimeout(httpParamsObj,4500);			//タイムアウト設定
            HttpPost httppost = new HttpPost(pUrl);							//ポスト

            //POST送信
            HttpResponse response = httpclient.execute(httppost);

            //サーバーからの応答を取得
            return response;

	    } catch (MalformedURLException e) {
	        //System.err.println("Invalid URL format: " + urlString);
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisHttpPost", e.toString());
	        return null;
	    }
	}

	public static HttpResponse get(String pUrl)
	{
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(pUrl);

            //POST送信
            HttpResponse response = httpclient.execute(httpget);

            //サーバーからの応答を取得
            return response;

	    } catch (MalformedURLException e) {
	        //System.err.println("Invalid URL format: " + urlString);
	        return null;
	    } catch (IOException e) {
	    	Log.d("LiplisHttpPost", e.toString());
	        return null;
	    }
	}
}
