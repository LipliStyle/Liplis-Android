//=======================================================================
//  ClassName : ObjLiplisChat
//  概要      : チャットオブジェクト
//
//	extends   :
//	impliments:
//
//2013/09/15 ver3.4.4 バージョン 追加
//           警告解除「new Integer(x) → 「Integer.valueOf(x)」 」
//
//  LiplisAndroid
//  Copyright(c) 2013-2013 sachin.
//=======================================================================
package little.cute.Obj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import little.cute.Common.LiplisUtil;
import little.cute.Msg.MsgShortNews;

public class ObjLiplisChat {
	///=============================
    /// バージョン 追加
	public String version;

	///=============================
    /// ボディ
	public List<String> nameList;			//メッセージ名
	public List<String> typeList;			//タイプ
	public List<String> discriptionList;	//内容
	public List<Integer> emotionList;		//エモーション
	public List<String> prerequisiteList;	//発言条件


    /// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisChat()
	{
		version = "1.0";
		nameList 			= new ArrayList<String>();
		typeList 			= new ArrayList<String>();
		discriptionList 	= new ArrayList<String>();
		emotionList 		= new ArrayList<Integer>();
		prerequisiteList 	= new ArrayList<String>();
	}

    /// <summary>
    /// getGreet
    /// </summary>
	public MsgShortNews getGreet()
	{
		MsgShortNews result = new MsgShortNews();
		String prerequinste = "";
		String[] timeList;
		String[] startList;
		String[] endList;
		int idx = 0;
		int nowHour = 0;
		int nowMin = 0;
		int startHour = 0;
		int startMin = 0;
		int endHour = 0;
		int endMin = 0;


		//対象インデックスリスト
		List<Integer> resList = new ArrayList<Integer>();

		//時間に合致する挨拶を検索
        for (String type : typeList) {
        	//挨拶なら対象
            if(type.equals("greet")){
            	//対象時刻チェック
            	try
            	{
            		//条件の取得
            		prerequinste = prerequisiteList.get(idx);
            		timeList = prerequinste.split(",");

            		//時刻が2つ記述されていて初めて判定
            		if(timeList.length == 2)
            		{
            			startList = timeList[0].split(":");
            			endList = timeList[1].split(":");

            			if(startList.length == 2 && endList.length == 2)
                		{
            				Calendar cal1 = Calendar.getInstance();

            				nowHour = cal1.get(Calendar.HOUR_OF_DAY);
            				nowMin = cal1.get(Calendar.MINUTE);

            				startHour = Integer.valueOf(startList[0]);
            				startMin = Integer.valueOf(startList[1]);

            				endHour = Integer.valueOf(endList[0]);
            				endMin = Integer.valueOf(endList[1]);

            				//スタートアワーの場合、分を確認
            				if(nowHour == startHour && nowMin >= startMin)
            				{
            					if(nowHour == endHour && nowMin <= endMin)
            					{
            						resList.add(idx);
            					}
            					else if(nowHour < endHour)
            					{
            						resList.add(idx);
            					}
            				}
            				else if(nowHour >= startHour)
            				{
            					if(nowHour == endHour && nowMin <= endMin)
            					{
            						resList.add(idx);
            					}
            					else if(nowHour < endHour)
            					{
            						resList.add(idx);
            					}
            				}
                		}
            		}

            	}
            	catch(Exception e)
            	{

            	}
            }
            idx++;
        }

        //候補が置ければ1個目を取得
        if(resList.size() > 0)
        {
        	if(nameList.size() > 0)
        	{
        		Random rnd = new Random();

                int ran = rnd.nextInt(resList.size());
        		int tarIdx = resList.get(ran);

        		try
        		{
        			result = new MsgShortNews(discriptionList.get(tarIdx), emotionList.get(tarIdx), 99);
        		}
        		catch(Exception e)
        		{
        			result = new MsgShortNews("", 0,0 );
        		}
        	}
        	else
        	{
        		result = new MsgShortNews("", 0, 0);
        	}
        }

        return result;
	}

	/// <summary>
    /// getChatWord
	/// 対象のタイプのセリフを1つランダムに返す
    /// </summary>
	public MsgShortNews getChatWord(String pType)
	{
		MsgShortNews result = new MsgShortNews();
		int idx = 0;

		//対象インデックスリスト
		List<Integer> resList = new ArrayList<Integer>();

		//時間に合致する挨拶を検索
        for (String type : typeList) {
        	//挨拶なら対象
            if(type.equals(pType)){
            	resList.add(idx);
            }
            idx++;
        }

        //候補が置ければ1個目を取得
        if(resList.size() > 0)
        {
        	if(nameList.size() > 0)
        	{
        		Random rnd = new Random();

                int ran = rnd.nextInt(resList.size());
        		int tarIdx = resList.get(ran);

        		try
        		{
        			result = new MsgShortNews(discriptionList.get(tarIdx), emotionList.get(tarIdx), 99);
        		}
        		catch(Exception e)
        		{
        			result = new MsgShortNews("", 0,0 );
        		}
        	}
        	else
        	{
        		result = new MsgShortNews("", 0, 0);
        	}
        }

		return result;
	}

	/// <summary>
    /// getChatWord
	/// 対象のタイプのセリフを1つランダムに返す
    /// </summary>
	public String getChatWordStr(String pType)
	{
		String result = "";
		int idx = 0;

		//対象インデックスリスト
		List<Integer> resList = new ArrayList<Integer>();

		//時間に合致する挨拶を検索
        for (String type : typeList) {
        	//挨拶なら対象
            if(type.equals(pType)){
            	resList.add(idx);
            }
            idx++;
        }

        //候補が置ければ1個目を取得
        if(resList.size() > 0)
        {
        	if(nameList.size() > 0)
        	{
        		Random rnd = new Random();

                int ran = rnd.nextInt(resList.size());
        		int tarIdx = resList.get(ran);

        		try
        		{
        			result = discriptionList.get(tarIdx);
        		}
        		catch(Exception e)
        		{
        			result = "";
        		}
        	}
        	else
        	{
        		result = "";
        	}
        }
        else
        {
        	result= "";
        }

		return result;
	}

	/// <summary>
    /// getBatteryInfo
	/// バッテリー情報を取得する
    /// </summary>
	public MsgShortNews getBatteryInfo(int batteryLevel)
	{

		MsgShortNews result;
		MsgShortNews batteryWord;
		String resStr = "";

		try
		{
			//電池容量のセリフを取得
			resStr = getChatWordStr("batteryInfo");

			//空だったら、電池格納用ワードを入れておく
	    	if(resStr.equals(""))
	    	{
	    		resStr = "[?]%";
	    	}

	    	//バッテリーレベルによってセリフを変える
	    	if(batteryLevel > 70)
	    	{
	    		batteryWord = getChatWord("batteryHi");
	    	}
	    	else if(batteryLevel > 30)
	    	{
	    		batteryWord = getChatWord("batteryMid");
	    	}
	    	else if(batteryLevel > 0)
	    	{
	    		batteryWord = getChatWord("batteryLow");
	    	}
	    	else
	    	{
	    		batteryWord = new MsgShortNews();
	    	}

	    	//メッセージ作成
	    	resStr = resStr + batteryWord.nameList.get(0);
	    	resStr = resStr.replace("[?]", String.valueOf(batteryLevel));
	    	result = new MsgShortNews(resStr, batteryWord.emotionList.get(0), batteryWord.pointList.get(0));

	    	return result;
		}
		catch(Exception e)
		{
			return new MsgShortNews( "[?]%", 1, 1);
		}
	}

	/// <summary>
    /// getClockInfo
	/// 時刻情報を取得する
    /// </summary>
	public MsgShortNews getClockInfo()
	{
		String resStr = "";

		//時刻情報のセリフを取得
		resStr = getChatWordStr("clockInfo");

		//空だったら、電池格納用ワードを入れておく
    	if(resStr.equals(""))
    	{
    		resStr = LiplisUtil.getNowTime(Calendar.MINUTE);
    	}
    	else
    	{
    		resStr = resStr.replace("[?]", LiplisUtil.getNowTime(Calendar.MINUTE));
    	}

		return new MsgShortNews(resStr, 1, 1);
	}

	/// <summary>
    /// getTimeSignal
    /// 時報を取得する
    /// </summary>
    public MsgShortNews getTimeSignal(int hour)
    {
        MsgShortNews result = new MsgShortNews();
        MsgShortNews buf;

        try
        {
            switch (hour)
            {
            case 1: buf = getChatWord("1Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 2: buf = getChatWord("2Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 3: buf = getChatWord("3Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 4: buf = getChatWord("4Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 5: buf = getChatWord("5Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 6: buf = getChatWord("6Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 7: buf = getChatWord("7Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 8: buf = getChatWord("8Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 9: buf = getChatWord("9Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 10: buf = getChatWord("10Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 11: buf = getChatWord("11Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 12: buf = getChatWord("12Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 13: buf = getChatWord("13Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 14: buf = getChatWord("14Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 15: buf = getChatWord("15Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 16: buf = getChatWord("16Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 17: buf = getChatWord("17Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 18: buf = getChatWord("18Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 19: buf = getChatWord("19Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 20: buf = getChatWord("20Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 21: buf = getChatWord("21Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 22: buf = getChatWord("22Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 23: buf = getChatWord("23Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));
            case 0: buf = getChatWord("24Oclock"); return new MsgShortNews(buf.nameList.get(0).replace("@", ""), buf.emotionList.get(0), buf.pointList.get(0));

                default:
                    return result;
            }
        }
        catch (Exception ex)
        {
            return new MsgShortNews("[?]%", 1, 1);
        }
    }
}
