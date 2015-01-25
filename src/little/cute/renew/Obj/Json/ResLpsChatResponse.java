//=======================================================================
//  ClassName : ResLpsChatResponse
//  概要      : Liplisチャットトーク応答クラス
//
//
//  Liplisシステム
//  Copyright(c) 2010-2015 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

import java.util.ArrayList;
import java.util.List;

public class ResLpsChatResponse {
    ///=============================
    /// プロパティ
    public String title;
    public String url;
    public String jpgUrl ;
    public List<String> descriptionList ;
    public List<String> opList;
    public boolean already ;

    public ResLpsChatResponse()
    {
        descriptionList = new ArrayList<String>();
        opList = new ArrayList<String>();
    }

    public ResLpsChatResponse(int idx, String title, String url, String jpgUrl, List<String> descriptionList, List<String> opList, boolean already)
    {
        this.title = title;
        this.url = url;
        this.jpgUrl = jpgUrl;
        this.descriptionList = descriptionList;
        this.opList = opList;
        this.already = already;
    }

}
