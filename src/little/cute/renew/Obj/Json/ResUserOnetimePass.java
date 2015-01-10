//=======================================================================
//  ClassName : ResUserOnetimePass
//  概要      : ResUserOnetimePassオブジェクト
//              Clalis互換クラス
//
//2013/04/28 ver3.3.7
//
//
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

public class ResUserOnetimePass {
	///=============================
    ///プロパティ
    public String oneTimePass;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResUserOnetimePass()
    {
        this.oneTimePass = "";
    }
    public ResUserOnetimePass(String oneTimePass)
    {
        this.oneTimePass = oneTimePass;
    }
}
