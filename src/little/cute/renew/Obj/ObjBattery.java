//=======================================================================
//  ClassName : ObjBattery
//  概要      : バッテリーオブジェクト
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj;

import little.cute.renew.R;
import android.content.Intent;

public class ObjBattery {

    ///=============================
    /// ステータス
	private String batteryText = "";
	private int batteryImageId = 0;

	private int batteryNowLevel 	= 0;
	//private int batteryScale 		= 0;
	//private int batteryStatus 		= 0;
	private boolean batteryExists	= false;
	//private int batteryPlugged  	= 0;

    ///=============================
    /// 定数
    private int battery_100;
    private int battery_87;
	private int battery_75;
	private int battery_62;
    private int battery_50;
    private int battery_37;
    private int battery_25;
    private int battery_12;
    private int battery_0;
    private int battery_non;




    /// <summary>
    /// コンストラクター
    /// </summary>
    public ObjBattery()
    {
    	battery_100 = R.drawable.battery_100;
    	battery_87 = R.drawable.battery_87;
    	battery_75 = R.drawable.battery_75;
    	battery_62 = R.drawable.battery_62;
    	battery_50 = R.drawable.battery_50;
    	battery_37 = R.drawable.battery_37;
    	battery_25 = R.drawable.battery_25;
    	battery_12 = R.drawable.battery_12;
    	battery_0 = R.drawable.battery_0;
    	battery_non = R.drawable.battery_non;
    }

    /// <summary>
    /// ノンバッテリーを帰す
    /// </summary>
    public int getNon()
    {
    	return battery_non;
    }

    /// <summary>
    /// getBatteryImage
    /// バッテリーイメージを返す
    /// </summary>
    public void getBatteryImage(Intent intent)
    {
    	//バッテリー情報の取得
    	batteryCheck(intent);

    	//バッテリー存在
    	if(batteryExists)
    	{
//    		//バッテリー充電中
//    		if(status == BatteryManager.BATTERY_STATUS_CHARGING)
//    		{
//    			//テキスト出力
//    			if(plugged == BatteryManager.BATTERY_PLUGGED_AC)
//    			{
//    				batteryText = "AC";
//    			}else if(plugged == BatteryManager.BATTERY_PLUGGED_AC)
//    			{
//    				batteryText = "USB";
//    			}
//    			else
//    			{
//    				batteryText = "CHARGE";
//    			}
//
//    			//充電中
//    			batteryImageId = battery_charge;
//    		}
//    		else
//    		{
//    			//
//    			if(level <= 10)
//    			{
//    				batteryImageId = battery_0;
//    			}else if(level <= 25)
//    			{
//    				batteryImageId = battery_25;
//    			}else if(level <= 50)
//    			{
//    				batteryImageId = battery_50;
//    			}else if(level <= 75)
//    			{
//    				batteryImageId = battery_75;
//    			}else if(level > 75)
//    			{
//    				batteryImageId = battery_100;
//    			}
//
//    			//バッテリー残量の表示
//    			batteryText = level + "%";
//    		}

			//
			if(batteryNowLevel <= 10)
			{
				batteryImageId = battery_0;
			}else if(batteryNowLevel <= 12)
			{
				batteryImageId = battery_12;
			}else if(batteryNowLevel <= 25)
			{
				batteryImageId = battery_25;
			}else if(batteryNowLevel <= 37)
			{
				batteryImageId = battery_37;
			}else if(batteryNowLevel <= 50)
			{
				batteryImageId = battery_50;
			}else if(batteryNowLevel <= 62)
			{
				batteryImageId = battery_62;
			}else if(batteryNowLevel <= 75)
			{
				batteryImageId = battery_75;
			}else if(batteryNowLevel <= 87)
			{
				batteryImageId = battery_87;
			}else if(batteryNowLevel > 87)
			{
				batteryImageId = battery_100;
			}

			//バッテリー残量の表示
			batteryText = batteryNowLevel + "%";

    	}else
    	{
    		//バッテリー接続なし
    		batteryText = "-";
    	}
    }

    /// <summary>
    /// ゲッターセッター
    /// </summary>
	public String getBatteryText() {
		return batteryText;
	}

	public void setBatteryText(String batteryText) {
		this.batteryText = batteryText;
	}

	public int getBatteryImageId() {
		return batteryImageId;
	}

	public void setBatteryImageId(int batteryImageId) {
		this.batteryImageId = batteryImageId;
	}

	public int getBatteryNowLevel() {
		return batteryNowLevel;
	}


	/// <summary>
    /// getBatteryRatel
    /// バッテリー割合を取得する
    /// </summary>
	public int getBatteryRatel() {
		return batteryNowLevel / 10;
	}


    /// <summary>
    /// batteryCheck
    /// バッテリーチェック
    /// </summary>
    private void batteryCheck(Intent intent)
    {
//    	   int status = intent.getIntExtra("status", 0);				//ステータス			定義値は、BatteryManager.BATTERY_STATUS_XXX
//         int health = intent.getIntExtra("health", 0);				//ヘルス				定義値は、BatteryManager.BATTERY_HEALTH_XXX
//         boolean present = intent.getBooleanExtra("present", false);	//
//         int level = intent.getIntExtra("level", 0);					//バッテリー残量
//         int scale = intent.getIntExtra("scale", 0);					//バッテリー最大値		通常は、100固定
//         int icon_small = intent.getIntExtra("icon-small", 0);		//アイコンID
//         int plugged = intent.getIntExtra("plugged", 0);				//接続されているプラグ	定義値は、BatteryManager.BATTERY_PLUGGED_XXX
//         int voltage = intent.getIntExtra("voltage", 0);				//電圧					ミリボルト
//         int temperature = intent.getIntExtra("temperature", 0);		//温度					0.1度単位。例えば、197は、19.7度。
//         String technology = intent.getStringExtra("technology");		//電池種類				例えば、Li-ionなど。


    	//電池情報の取得
    	if(intent != null)
    	{
    		batteryNowLevel 	= intent.getIntExtra("level", 0);			//バッテリー残量値
    		batteryExists 		= intent.getBooleanExtra("present", false);//バッテリー存在
        	//batteryScale 		= intent.getIntExtra("scale", 0);			//バッテリー最大値
        	//batteryStatus 		= intent.getIntExtra("status",0);			//バッテリーステータス
        	//batteryPlugged 		= intent.getIntExtra("plugged",0);			//バッテリー接続種類
    	}else
    	{
    		batteryNowLevel 	= 100;
    		batteryExists 		= true;
        	//batteryScale 		= 0;
        	//batteryStatus 		= 0;
        	//batteryPlugged 		= 0;
    	}


    }




}


