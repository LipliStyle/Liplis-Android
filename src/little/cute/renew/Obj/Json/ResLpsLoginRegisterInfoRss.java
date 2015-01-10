//=======================================================================
//  ClassName : ResLpsLoginRegisterInfoRss
//  概要      : LiplisレスポンスJSON用
//
//
//2013/04/06 ver3.3.6 LiplisNewsの非同期取得メソッドを変更
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

import java.util.ArrayList;
import java.util.List;


public class ResLpsLoginRegisterInfoRss {
    ///=============================
    ///プロパティ
    public List<RegisterRsUserInfo> rsslist;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResLpsLoginRegisterInfoRss()
    {
        this.rsslist = new ArrayList<RegisterRsUserInfo>();
    }
    public ResLpsLoginRegisterInfoRss(List<RegisterRsUserInfo> rsslist)
    {
        this.rsslist = rsslist;
    }
}
