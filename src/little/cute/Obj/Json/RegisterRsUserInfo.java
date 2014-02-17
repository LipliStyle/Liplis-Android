//=======================================================================
//  ClassName : ResLpsLoginRegisterInfoRss
//  概要      : RSS登録情報レコード
//
//
//2013/04/06 ver3.3.6 LiplisNewsの非同期取得メソッドを変更
//
//  Liplisシステム
//  Copyright(c) 2010-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Obj.Json;

public class RegisterRsUserInfo {

    public String url;
    public String title;
    public String cat;

    public RegisterRsUserInfo()
    {
    }
    public RegisterRsUserInfo(String url,String title, String cat)
    {
        this.url = url;
        this.title = title;
        this.cat = cat;
    }
}
