//=======================================================================
//  ClassName : LiplisResponse
//  概要      : LiplisレスポンスJSON用
//
//
//2013/04/06 ver3.3.6 LiplisNewsの非同期取得メソッドを変更
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Xml.LiplisResponse;

import java.io.IOException;
import java.io.InputStream;

import little.cute.renew.Common.LiplisUtil;
import little.cute.renew.Obj.Json.ResLpsRegisterResponse;

import com.google.gson.Gson;

public class LiplisResponse {

    /// <summary>
    /// コンストラクター
	/// XMLよりデータを取得しておく
    /// </summary>
	public LiplisResponse(){	}

    /// <summary>
    /// ショートニュースの取得
    /// </summary>
	public static String getResponse(InputStream is)
	{
		try
		{
			if(is == null)
			{
				return "";
			}

			//取得したインプットストリームをストリングに変換してメソッドに通す
			return new Gson().fromJson(LiplisUtil.inputStreemToString(is), ResLpsRegisterResponse.class).responseCode ;
		}
		catch(IOException ex)
		{
			return "";
		}

	}
}
