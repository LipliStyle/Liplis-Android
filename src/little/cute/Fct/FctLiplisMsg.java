package little.cute.Fct;

import little.cute.Msg.MsgShortNews;

public class FctLiplisMsg {
	
	/// <summary>
    /// createMsgMassageDlFaild
	/// データ取得失敗メッセージの作成
    /// </summary>
	public static MsgShortNews createMsgMassageDlFaild(){
		MsgShortNews msg = new 	MsgShortNews();
		
//		msg.source = "データの取得に失敗しました。";
//		msg.converted = "データの取得に失敗しました。";
		
		msg.nameList.add("データ");
		msg.nameList.add("の");
		msg.nameList.add("取得");
		msg.nameList.add("に");
		msg.nameList.add("失敗");
		msg.nameList.add("し");
		msg.nameList.add("まし");
		msg.nameList.add("た。");
		
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		msg.emotionList.add(1);
		
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
		msg.pointList.add(-1);
				
		return msg;
		
	}
	
}
