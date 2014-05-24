package little.cute.Obj;

public class ObjLiplisVersion {
	///=============================
    /// バージョン 追加
	public String skinVersion;
	public String liplisMinVersion;
	public String url;
	public String apkUrl;

	///=============================
    /// バージョンチェック有無 Noralisでこのフラグを操作する。
	private boolean flgCheckOn = false;

    /// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisVersion()
	{
		liplisMinVersion= "";
		skinVersion= "";
		url = "";
		apkUrl = "";
	}

    /// <summary>
    /// チェックオンを取得する
    /// </summary>
	public boolean getFlgCheckOn()
	{
		return this.flgCheckOn;
	}





}
