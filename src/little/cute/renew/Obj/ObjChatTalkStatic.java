//=======================================================================
//  ClassName : ObjChatTalkStatic
//  概要      : スタティックチャットトークデータ
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2015 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj;


public class ObjChatTalkStatic {

	///=====================================
    /// スタティックインスタンス
	static ObjChatTalkStatic _instance = null;

	///=====================================
    /// 受け渡しメッセージ
    private String sendText ="";


    /**
     * スタティックインスタンス取得
     * @return
     */
    static public ObjChatTalkStatic getInstance(){
        if(_instance==null){
            _instance = new ObjChatTalkStatic();
        }
        return _instance;
    }


    public String getSendText() {
		return sendText;
	}
	public void setSendText(String sendText) {
		this.sendText = sendText;
	}


}
