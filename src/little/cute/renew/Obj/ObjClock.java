//=======================================================================
//  ClassName : ObjClock
//  概要      : 時計オブジェクト
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj;

import android.annotation.SuppressLint;
import java.util.Calendar;

import little.cute.renew.R;

@SuppressLint("DefaultLocale")
public class ObjClock {

    ///=============================
    /// 定数
    private int year4;
    private int year3;
	private int year2;
	private int year1;
    private int month2;
    private int month1;
    private int day2;
    private int day1;
    private int hour2;
    private int hour1;
    private int min2;
    private int min1;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ObjClock()
    {
    	updateClockObject();
    }

    /// <summary>
    ///	updateClockObject
    /// 現在時刻のタイムオブジェクトを更新する
    /// </summary>
	@SuppressLint("DefaultLocale")
	public void updateClockObject()
	{
		Calendar cal = Calendar.getInstance();  //(1)オブジェクトの生成

		String year 	= String.format("%1$04d",cal.get(Calendar.YEAR));
		String month 	= String.format("%1$02d",cal.get(Calendar.MONTH) + 1);
		String day 		= String.format("%1$02d",cal.get(Calendar.DAY_OF_MONTH));
		//String hour 	= String.format("%1$02d",cal.get(Calendar.HOUR));
		String hour 	= String.format("%1$02d",cal.get(Calendar.HOUR_OF_DAY ));
		String min 		= String.format("%1$02d",cal.get(Calendar.MINUTE));

		year1 = getClockViewId(year.substring(3, 4).toString());
		year2 = getClockViewId(year.substring(2, 3).toString());
		year3 = getClockViewId(year.substring(1, 2).toString());
		year4 = getClockViewId(year.substring(0, 1).toString());

		month1 = getClockViewId(month.substring(1, 2).toString());
		month2 = getClockViewId(month.substring(0, 1).toString());

		day1 = getClockViewId(day.substring(1, 2).toString());
		day2 = getClockViewId(day.substring(0, 1).toString());

		hour1 = getClockViewId(hour.substring(1, 2).toString());
		hour2 = getClockViewId(hour.substring(0, 1).toString());

		min1 = getClockViewId(min.substring(1, 2).toString());
		min2 = getClockViewId(min.substring(0, 1).toString());
	}

	/// <summary>
    ///	getClockViewId
    /// 対象数字(文字)をViewオブジェクトとして取得する
    /// </summary>
	private int getClockViewId(String val)
	{
		if(val.equals("0"))
		{
			return R.drawable.clock_0;
		}
		else if(val.equals("1"))
		{
			return R.drawable.clock_1;
		}
		else if(val.equals("2"))
		{
			return R.drawable.clock_2;
		}
		else if(val.equals("3"))
		{
			return R.drawable.clock_3;
		}
		else if(val.equals("4"))
		{
			return R.drawable.clock_4;
		}
		else if(val.equals("5"))
		{
			return R.drawable.clock_5;
		}
		else if(val.equals("6"))
		{
			return R.drawable.clock_6;
		}
		else if(val.equals("7"))
		{
			return R.drawable.clock_7;
		}
		else if(val.equals("8"))
		{
			return R.drawable.clock_8;
		}
		else if(val.equals("9"))
		{
			return R.drawable.clock_9;
		}
		else
		{
			return R.drawable.clock_9;
		}
	}

    /// <summary>
    /// ゲッターセッター
    /// </summary>

	public final int getYear4() {
		return year4;
	}

	public final int getYear3() {
		return year3;
	}

	public final int getYear2() {
		return year2;
	}

	public final int getYear1() {
		return year1;
	}

	public final int getMonth2() {
		return month2;
	}

	public final int getMonth1() {
		return month1;
	}

	public final int getDay2() {
		return day2;
	}

	public final int getDay1() {
		return day1;
	}

	public final int getHour2() {
		return hour2;
	}

	public final int getHour1() {
		return hour1;
	}

	public final int getMin2() {
		return min2;
	}

	public final int getMin1() {
		return min1;
	}

}
