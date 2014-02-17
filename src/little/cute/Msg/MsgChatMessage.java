package little.cute.Msg;


public class MsgChatMessage {
	///=============================
    ///プロパティ
	public String msg;
	public int emeotion;
	
	/// <summary>
    /// コンストラクター
    /// </summary>
	public MsgChatMessage(String msg, int emotion)
	{
		this.msg = msg;
		this.emeotion = emotion;
	}
}
