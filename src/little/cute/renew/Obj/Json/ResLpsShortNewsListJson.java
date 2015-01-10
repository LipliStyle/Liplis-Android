//=======================================================================
//  ClassName : ResLpsShortNewsListJson
//  概要      : LpsShortNewsJsonオブジェクト
//              Clalis互換クラス
//
//2013/03/23 ver3.3.5 API変更に伴い、インターフェースも変更
//    「public List<ResLpsShortNewsJson> newsList;」→ 「public List<ResLpsShortNewsJson> lstNews;」
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

import java.util.ArrayList;
import java.util.List;

public class ResLpsShortNewsListJson {

	///=============================
    ///プロパティ
    public List<ResLpsShortNewsJson> lstNews;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResLpsShortNewsListJson()
    {
        this.lstNews = new ArrayList<ResLpsShortNewsJson>();
    }
    public ResLpsShortNewsListJson(List<ResLpsShortNewsJson> lstNews)
    {
        this.lstNews = lstNews;
    }
}
