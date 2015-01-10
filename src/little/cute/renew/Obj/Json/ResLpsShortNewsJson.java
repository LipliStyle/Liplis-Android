//=======================================================================
//  ClassName : ResLpsShortNewsJson
//  概要      : LpsShortNewsJsonオブジェクト
//              Clalis互換クラス
//
//2013/03/23 ver3.3.5 API変更に伴い、インターフェースも変更
//    「public List<ObjLeafAndEmotion> laeList;」→ 「String result;」
//
//
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

public class ResLpsShortNewsJson {
	///=============================
    ///プロパティ
    public String url;
    public String result;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResLpsShortNewsJson()
    {
        this.url = "";
        this.result = "";
    }
    public ResLpsShortNewsJson(String url, String result)
    {
        this.url = url;
        this.result = result;
    }
}
