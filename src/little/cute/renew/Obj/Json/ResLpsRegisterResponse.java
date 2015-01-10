//=======================================================================
//  ClassName : ResLpsRegisterResponse
//  概要      : LpsResponseJsonオブジェクト
//              Clalis互換クラス
//
//2013/03/23 ver3.3.6
//
//
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

public class ResLpsRegisterResponse {
	///=============================
    ///プロパティ
    public String responseCode;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResLpsRegisterResponse()
    {
        this.responseCode = "";
    }
    public ResLpsRegisterResponse(String responseCode)
    {
        this.responseCode = responseCode;
    }
}
