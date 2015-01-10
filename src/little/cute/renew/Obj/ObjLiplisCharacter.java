//=======================================================================
//  ClassName : ObjLiplisCharacter
//  概要      : キャラクターオブジェクト
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2012 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj;

public class ObjLiplisCharacter {

	///=====================================
    /// クラス
	private String packageName;
	private String charName;

	/// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisCharacter(String packageName, String charName)
	{
		this.packageName = packageName;
		this.charName = charName;

	}

    public final String getPackageName() {
		return packageName;
	}

	public final String getCharName() {
		return charName;
	}
}
