//=======================================================================
//  ClassName : LiplisShortNewsIn
//  概要      : Liplisショートニュースインターナショナル
//
//  Liplisシステム      
//  Copyright(c) 2010-2012 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.Xml.LiplisShortNews;

import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import little.cute.Msg.MsgShortNews;
import little.cute.Xml.XmlReadListPullParser;

public class LiplisShortNewsIn extends XmlReadListPullParser implements LiplisShortNewsBase{

    /// --------------------------------------
    /// パス定義
	private static final String TAG_URL      = "url";
	private static final String TAG_BODY     = "body";
	private static final String TAG_EMOTION  = "emotion";
	
	/// <summary>
    /// ショートニュースの取得
	/// 日本語以外の言語の場合はこのクラスを使用する
    /// </summary>
	public MsgShortNews getShortNews(InputStream is)
	{
		return loadXml(getXmlFromSt(is));
	}
	
    /// <summary>
    /// loadXmlOld
	/// XMLをロードする(プロトタイプAPI使用)
    /// </summary>
	public MsgShortNews loadXml(XmlPullParser parser)
	{
		String url = "";
		String body = "";
		String emoiton = "";
		
        int eventType;
        try {
			while((eventType = parser.next()) != XmlPullParser.END_DOCUMENT){	
			    //URL取得
			    if(eventType == XmlPullParser.START_TAG && TAG_URL.equals(parser.getName())){
			    	eventType = parser.next();
			    	if(eventType == XmlPullParser.TEXT){
			    		url = (parser.getText());
			        }
			    }
			    //クリエイトデイト取得
			    else if(eventType == XmlPullParser.START_TAG && TAG_BODY.equals(parser.getName())){
			    	eventType = parser.next();
			    	if(eventType == XmlPullParser.TEXT){
			    		body = (parser.getText());
			        }
			    }
			    //クリエイトデイト取得
			    else if(eventType == XmlPullParser.START_TAG && TAG_EMOTION.equals(parser.getName())){
			    	eventType = parser.next();
			    	if(eventType == XmlPullParser.TEXT){
			    		emoiton = (parser.getText());
			        }
			    }
			}
			return itemConvert(url, body, emoiton);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			System.out.println(e);
			return new MsgShortNews();
		} catch (IOException e) {
			e.printStackTrace();
			return new MsgShortNews();
		}
	}
	
    /// <summary>
    /// itemConvert
	/// ストリングをメッセージリストに変換する
	/// URL;str1,emotion1,point1;str2,emotion2,point2;・・・
	/// と表されている出力結果を分解する。
    /// </summary>
    private MsgShortNews itemConvert(String url, String body, String emotion)
    {
    	MsgShortNews res = new MsgShortNews();
    	int idx = 0;
    	int idxCnt = 0;
    	int idxSwitch = 1;
    	
    	String bBody = "";
    	
    	//エモーションを分割
    	String[] wordList = emotion.split(";");
    	
    	try
    	{
    		//URLを取得
    		res.url = url;
    		
        	//スイッチインデックスの算出
        	idxSwitch = (int)((double)body.length() / (double)wordList.length);
        	
        	Log.d("body", body);
        	Log.d("idxSwitch", String.valueOf(idxSwitch));
        	
        	//最低1
        	if(idxSwitch < 1)
        	{
        		idxSwitch = 1;
        	}
        	
        	//ボディを回してポイント設定
        	if(body.length() > 0)
        	{
            	for(idx = 0; idx < body.length(); idx++)
            	{
            		//1文字取得
            		bBody = bBody +body.charAt(idx);
            		
            		Log.d("cnt", String.valueOf(idx));
            		Log.d("bBody", bBody);
            		
            		//スイッチチェック
            		if(switchCheck(idx,idxSwitch))  
            		{
            			//エモーションのカウントチェック
            			if(idxCnt < wordList.length)
            			{
            				//ボディとそれに対応する位置のエモーションをセットする
            				try
                			{
            					//バッファーリストの取得
                    			String[] bufList = wordList[idxCnt].split(",");
            					
                				res.nameList.add(bBody);
                    			res.emotionList.add(Integer.valueOf(bufList[0]));
                    			res.pointList.add(Integer.valueOf(bufList[1]));
                    		}
                    		catch(Exception e)
                    		{
                    			res.nameList.add(bBody);
                    			res.emotionList.add(0);
                    			res.pointList.add(0);
                    		}
            			}
            			else
            			{
            				//エモーション数が多い場合は0を設定する
            				res.nameList.add(bBody);
                			res.emotionList.add(0);
                			res.pointList.add(0);
            			}
            			
            			bBody = "";
            			idxCnt++;
            		}
        		}
            	
        	}

    	}
    	catch(Exception e)
    	{
    		Log.d("switchCheck",String.valueOf(idxCnt) + String.valueOf(wordList.length));
    		res = new MsgShortNews();
    	}

    	return res;
    	
    }
	
    /// <summary>
    /// switchCheck
	/// スイッチチェック
    /// </summary>
	private boolean switchCheck(double a, double b)
	{
		double res = a % b;
		Log.d("switchCheck",String.valueOf(res));
		
		if(res == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	
	
	
	
//    /// <summary>
//    /// addItem
//	/// アイテムアッド
//    /// </summary>
//    private void addItem(XmlPullParser parser, MsgShortNews msgShortNews)
//	    throws XmlPullParserException, IOException{
//	
//		while(true){
//		    int eventType = parser.next();
//		
//		    //objLeafAndEmotionのエンドタグを見つけたらループ終了
//		    if(eventType == XmlPullParser.END_TAG && "laeList".equals(parser.getName())){
//		        break;
//		    }
//		
//		    //nameの取り出し
//		    if(eventType == XmlPullParser.START_TAG && "name".equals(parser.getName())){
//		        eventType = parser.next();
//		        
//		        if(eventType == XmlPullParser.TEXT){
//		        	msgShortNews.nameList.add(parser.getText());
//		        }
//		    }
//		    
//		    //エモーションの取り出し
//		    if(eventType == XmlPullParser.START_TAG && "emotion".equals(parser.getName())){
//		        eventType = parser.next();
//		        
//		        if(eventType == XmlPullParser.TEXT){
//		        	msgShortNews.emotionList.add(LiplisUtil.parseInt(parser.getText()));
//		        }
//		    }
//		    
//		    //エモーションの取り出し
//		    if(eventType == XmlPullParser.START_TAG && "point".equals(parser.getName())){
//		        eventType = parser.next();
//		        
//		        if(eventType == XmlPullParser.TEXT){
//		        	msgShortNews.pointList.add(LiplisUtil.parseInt(parser.getText()));
//		        }
//		    }
//		}
//	}
	
}
