//=======================================================================
//  ClassName : LiplisUtil
//  概要      : リプリスユーティル
//
//	extends   :
//	impliments:
//
//2013/03/23 ver3.3.5 「getOae」「convInt」追加。API変更に伴う処理の追加対応
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import little.cute.renew.R;
import little.cute.renew.Obj.Json.ObjLeafAndEmotion;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;


public class LiplisUtil {

    /// <summary>
    /// parseInt
	/// インテジャーに変換する
    /// </summary>
    public static Integer parseInt(String str)
    {
    	try
    	{
    		return Integer.parseInt(str);
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// isServiceRunning
    /// 対象のサービスが動いているかチェックする
    /// </summary>
    public boolean isServiceRunning(Context c, Class<?> cls) {
        ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> runningService = am.getRunningServices(Integer.MAX_VALUE);
        for (RunningServiceInfo i : runningService) {
            if (cls.getName().equals(i.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /// <summary>
    /// getAppList
    /// アプリケーションリストを取得する
    /// </summary>
	public boolean getAppList(Context c, String target){
	    ActivityManager activityManager = (ActivityManager)c.getSystemService(Context.ACTIVITY_SERVICE);
	    // 起動中のアプリ情報を取得
	    List<RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
	    PackageManager packageManager = c.getPackageManager();
	    if(runningApp != null) {
	      for(RunningAppProcessInfo app : runningApp) {
	        try {
	          // アプリ名をリストに追加
	          ApplicationInfo appInfo = packageManager.getApplicationInfo(app.processName, 0);
	          if(target.equals((String)packageManager.getApplicationLabel(appInfo)))
	          {
	        	  return true;
	          }
	        }
	        catch(NameNotFoundException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return false;
	}

    /// <summary>
    /// sleep
    /// スリープ
    /// </summary>
    public static void sleep(int interval)
    {
    	try{Thread.sleep(interval);}catch(InterruptedException e){}
    }

    /// <summary>
    /// getName
    /// ランダムな名前を取得する
    /// </summary>
    public static String getName(int num)
    {
    	//ランダム文字列の取得
    	String longResult = UUID.randomUUID().toString();

    	//マックス文字数の設定
    	int maxLength = longResult.length();

    	//マックス文字数のチェック
    	if(num > maxLength)
    	{
    		num = maxLength;
    	}

    	return longResult.substring(0,num);
    }

    /// <summary>
    /// getName
    /// 現在時刻を取得する
    ///	カレンダーの精度で指定。
    /// </summary>
    public static String getNowTime(int precision)
    {
    	final Calendar calendar = Calendar.getInstance();
    	StringBuffer result = new StringBuffer();

    	//年精度
    	if(precision == Calendar.YEAR)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		result.append(String.valueOf(year));
    	}
    	//月精度
    	else if(precision == Calendar.MONTH)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;

    		result.append(String.valueOf(year) + "/" + String.valueOf(month));
    	}
    	//日精度
    	else if(precision == Calendar.DAY_OF_MONTH)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
    	}
    	//時精度
    	else if(precision == Calendar.HOUR_OF_DAY)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);
        	final int hour = calendar.get(Calendar.HOUR_OF_DAY);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
        	result.append(" ");
        	result.append(String.valueOf(hour));
    	}
    	//分精度
    	else if(precision == Calendar.MINUTE)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);
        	final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        	final int minute = calendar.get(Calendar.MINUTE);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
        	result.append(" ");
        	result.append(String.valueOf(hour) + ":" + String.valueOf(minute));
    	}
    	//秒精度
    	else if(precision == Calendar.SECOND)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);
        	final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        	final int minute = calendar.get(Calendar.MINUTE);
        	final int second = calendar.get(Calendar.SECOND);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
        	result.append(" ");
        	result.append(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second));
    	}
    	//ミリ秒精度
    	else if(precision == Calendar.MILLISECOND)
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);
        	final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        	final int minute = calendar.get(Calendar.MINUTE);
        	final int second = calendar.get(Calendar.SECOND);
        	final int ms = calendar.get(Calendar.MILLISECOND);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
        	result.append(" ");
        	result.append(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(ms));
    	}
    	//デフォルトは分精度
    	else
    	{
    		final int year = calendar.get(Calendar.YEAR);
    		final int month = calendar.get(Calendar.MONTH) + 1;
        	final int day = calendar.get(Calendar.DAY_OF_MONTH);
        	final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        	final int minute = calendar.get(Calendar.MINUTE);

        	result.append(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
        	result.append(" ");
        	result.append(String.valueOf(hour) + ":" + String.valueOf(minute));
    	}

    	//結果を返す
    	return result.toString();
    }

    /// <summary>
    /// isNumeric
    /// 数値チェック
    /// </summary>
    public static boolean isNumeric(String numString ){
		try{
			Double.parseDouble(numString);
		}catch( NumberFormatException e ){
			return false;
		}
		return true;
	}

    /// <summary>
    /// isNull
    /// nullチェック
    /// </summary>
    @SuppressWarnings("unused")
	private static boolean isNull(Object obj){
    	// objのnullチェックする
    	return obj == null;
	}

    /// <summary>
    /// strToInt
    /// 文字列を数値に変換する。
    /// 変換不可なら0を返す
    /// </summary>
    public static int convValStrToInt(String numString ){
		try{
			return Integer.parseInt(numString);
		}catch( NumberFormatException e ){
			return 0;
		}
	}
    public static String convValStrToStr(String numString ){
		try{
			Integer.parseInt(numString);
			return numString;
		}catch( NumberFormatException e ){
			return "0";
		}
	}
    public static String convValIntToStr(int numString ){
		try{
			return String.valueOf(numString);
		}catch( NumberFormatException e ){
			return "0";
		}
	}

    /// <summary>
    /// nvl
    /// nullチェック
    /// </summary>
    @SuppressWarnings("unused")
	private static String nvl(String str){
    	// objのnullチェックする
    	if(str == null)
    	{
    		return "";
    	}
    	return str;
	}

    /// <summary>
    /// getWindowCode
    /// ウインドウコードをを取得する
    /// </summary>
    public static int getWindowCode(int liplisWindowCode){
    	//ウインドウの色変更
    	switch(liplisWindowCode)
    	{
	    	case 0:return R.drawable.window;
	    	case 1:return R.drawable.window_blue;
	    	case 2:return R.drawable.window_green;
	    	case 3:return R.drawable.window_pink;
	    	case 4:return R.drawable.window_purple;
	    	case 5:return R.drawable.window_red;
	    	case 6:return R.drawable.window_yellow;
	    	default:return R.drawable.window;
    	}
	}

    /// <summary>
    /// sleep
    /// 指定ミリ秒停止する
    /// </summary>
	public synchronized void sleep(long msec)
	{	//指定ミリ秒実行を止めるメソッド
		try
		{
			wait(msec);
		}catch(InterruptedException e){}
	}

    /// <summary>
    /// inputStreemToString
    /// インプットストリームを変換する
    /// </summary>
	public static String inputStreemToString(InputStream in) throws IOException{
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	    StringBuffer buf = new StringBuffer();
	    String str;
	    while ((str = reader.readLine()) != null) {
	            buf.append(str);
	    }
	    return buf.toString();
	}

    /// <summary>
    /// getOae
	/// ObjLeafAndEmotionを作成する
    /// </summary>
	public static ObjLeafAndEmotion getOae(String[] leafEmotionPointStr)
    {
    	ObjLeafAndEmotion result = new ObjLeafAndEmotion();

		if(leafEmotionPointStr.length==3)
		{
			result.name = leafEmotionPointStr[0];
			result.emotion = convInt(leafEmotionPointStr[1]);
			result.point = convInt(leafEmotionPointStr[2]);
		}
		else
		{
			result.name = "";
			result.emotion = 0;
			result.point = 0;
		}

		return result;
    }

    /// <summary>
    /// convInt
	/// intへの安全な変換
    /// </summary>
	public static int convInt(String val)
    {
    	try
    	{
    		return Integer.valueOf(val);
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// boolToBit
	/// ブーリアンからビットに変換する
    /// </summary>
	public static int boolToBit(boolean val)
    {
    	if(val)
    	{
    		return 1;
    	}
    	else
    	{
    		return 0;
    	}
    }

    /// <summary>
    /// boolToBit
	/// ブーリアンからビットに変換する
    /// </summary>
	public static boolean bitToBool(int val)
    {
		return val == 1;
    }
}
