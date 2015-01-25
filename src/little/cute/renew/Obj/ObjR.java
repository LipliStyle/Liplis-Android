package little.cute.renew.Obj;

import little.cute.renew.R;
import little.cute.renew.Widget.LiplisWidgetLarge;
import little.cute.renew.Widget.LiplisWidgetNormal;

public class ObjR {

	public Class<?> cls;
	public int layout;

	public int liplisSleep;
	public int liplisLog;
	public int liplisSetting;
	public int liplisThinking;
	public int liplisAngClock;
	public int liplisBattery;

	public int llLpsChat;			//ver4.5 2015/01/11 追加
	public int llLpsSleep;
	public int llLpsLog;
	public int llLpsSetting;
	public int llLpsAngClock;
	public int llLpsbattery;

	public int llTalkText;
	public int liplisImage;
	public int liplisWindow;
	public int liplisTalkText;
	public int llClockAndBattery;

	public int imYear1;
	public int imYear2;
	public int imYear3;
	public int imYear4;

	public int imMonth1;
	public int imMonth2;
	public int imDay1;
	public int imDay2;

	public int imHour1;
	public int imHour2;
	public int imMin1;
	public int imMin2;

	public int imBattery01;
	public int imBattery02;
	public int imBattery03;
	public int imBattery04;
	public int imBattery05;
	public int imBattery06;
	public int imBattery07;
	public int imBattery08;
	public int imBattery09;
	public int imBattery10;

	public ObjR(int size)
	{
		if(size == 1)
		{
			initNormal();
		}
		else if(size == 2)
		{
			initLarge();
		}
		else
		{
			initNormal();
		}
	}

	public void initNormal()
	{
		cls = LiplisWidgetNormal.class;

		layout = R.layout.widget;

		liplisSleep=R.id.liplisSleep;
		liplisLog=R.id.liplisLog;
		liplisSetting=R.id.liplisSetting;
		liplisThinking=R.id.liplisThinking;
		liplisAngClock=R.id.liplisAngClock;
		liplisBattery=R.id.liplisBattery;

		llLpsChat = R.id.llLpsThinking;
		llLpsSleep = R.id.llLpsSleep;
		llLpsLog = R.id.llLpsLog;
		llLpsSetting = R.id.llLpsSetting;
		llLpsAngClock = R.id.llLpsAngClock;
		llLpsbattery = R.id.llLpsbattery;

		llTalkText = R.id.llTalkText;
		liplisImage=R.id.liplisImage;
		liplisWindow=R.id.liplisWindow;
		liplisTalkText=R.id.liplisTalkText;
		llClockAndBattery=R.id.llClockAndBattery;

		imYear1=R.id.imYear1;
		imYear2=R.id.imYear2;
		imYear3=R.id.imYear3;
		imYear4=R.id.imYear4;

		imMonth1=R.id.imMonth1;
		imMonth2=R.id.imMonth2;
		imDay1=R.id.imDay1;
		imDay2=R.id.imDay2;

		imHour1=R.id.imHour1;
		imHour2=R.id.imHour2;
		imMin1=R.id.imMin1;
		imMin2=R.id.imMin2;

		imBattery01=R.id.imBattery01;
		imBattery02=R.id.imBattery02;
		imBattery03=R.id.imBattery03;
		imBattery04=R.id.imBattery04;
		imBattery05=R.id.imBattery05;
		imBattery06=R.id.imBattery06;
		imBattery07=R.id.imBattery07;
		imBattery08=R.id.imBattery08;
		imBattery09=R.id.imBattery09;
		imBattery10=R.id.imBattery10;
	}

	public void initLarge()
	{
		cls = LiplisWidgetLarge.class;

		layout = R.layout.widgetlarge;

		liplisSleep=R.id.liplisSleepLar;
		liplisLog=R.id.liplisLogLar;
		liplisSetting=R.id.liplisSettingLar;
		liplisThinking=R.id.liplisThinkingLar;
		liplisAngClock=R.id.liplisAngClockLar;
		liplisBattery=R.id.liplisBatteryLar;

		llLpsChat = R.id.llLpsThinkingLar;		//ver4.5 2015/01/11 追加
		llLpsSleep = R.id.llLpsSleepLar;
		llLpsLog = R.id.llLpsLogLar;
		llLpsSetting = R.id.llLpsSettingLar;
		llLpsAngClock = R.id.llLpsAngClockLar;
		llLpsbattery = R.id.llLpsbatteryLar;

		llTalkText = R.id.llTalkTextLar;
		liplisImage=R.id.liplisImageLar;
		liplisWindow=R.id.liplisWindowLar;
		liplisTalkText=R.id.liplisTalkTextLar;
		llClockAndBattery=R.id.llClockAndBatteryLar;

		imYear1=R.id.imYear1Lar;
		imYear2=R.id.imYear2Lar;
		imYear3=R.id.imYear3Lar;
		imYear4=R.id.imYear4Lar;

		imMonth1=R.id.imMonth1Lar;
		imMonth2=R.id.imMonth2Lar;
		imDay1=R.id.imDay1Lar;
		imDay2=R.id.imDay2Lar;

		imHour1=R.id.imHour1Lar;
		imHour2=R.id.imHour2Lar;
		imMin1=R.id.imMin1Lar;
		imMin2=R.id.imMin2Lar;

		imBattery01=R.id.imBattery01Lar;
		imBattery02=R.id.imBattery02Lar;
		imBattery03=R.id.imBattery03Lar;
		imBattery04=R.id.imBattery04Lar;
		imBattery05=R.id.imBattery05Lar;
		imBattery06=R.id.imBattery06Lar;
		imBattery07=R.id.imBattery07Lar;
		imBattery08=R.id.imBattery08Lar;
		imBattery09=R.id.imBattery09Lar;
		imBattery10=R.id.imBattery10Lar;
	}

}
