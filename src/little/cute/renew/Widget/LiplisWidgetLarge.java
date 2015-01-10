//=======================================================================
//  ClassName : LiplisWidgetNormal
//  概要      : リプリスウィジェットノーマル
//
//	extends   : LiplisWidget
//	impliments: 	
//
//　設定仕様
//	おしゃべり頻度
//　　おしゃべり　発言後 10s後
//　　ふつう　　　発言後 30s後
//　　ひかえめ　　発言後 60s後
//
//	アクティブ頻度
//　　おてんば　フル動作。1s置きのスキャン　口パク 1s置き、まばたき5s～10s
//　　ひかえめ　ハーフ動作2s置きのスキャン　口パク 2s置き、まばたき16秒おき固定
//　　エコ　　　まばたき、口パクを一切しない。感情総合ポイントで最終エモーション算出して、たち絵を変える
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Widget;

import little.cute.renew.Common.LiplisDefine;
import little.cute.renew.Obj.ObjR;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class LiplisWidgetLarge extends LiplisWidget {
    /// <summary>
    //  MethodType : base
    /// MethodName : onEnabled
    /// </summary>
    @Override
    public void onEnabled(Context context) {
		//アールオブジェクト
    	or = new ObjR(2);
    	
      final SharedPreferences config = context.getSharedPreferences(LiplisDefine.PREFS_NAME, 2);
      SharedPreferences.Editor configEditor = config.edit();
      configEditor.putInt(LiplisDefine.PREFS_NOW_SIZE, 2);
      configEditor.commit();
    	
    	super.onEnabled(context);
    }
    
    /// <summary>
    //  MethodType : base
    /// MethodName : onUpdate
    /// </summary>
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    	super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onDeleted
    /// </summary>
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /// <summary>
    //  MethodType : base
    /// MethodName : onDeleted
    /// </summary>
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}
	    
    /// <summary>
    //  MethodType : base
    /// MethodName : onReceive
    /// </summary>
    @Override
    public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
    }
    
    ///====================================================================
    ///
    ///                           実装メソッド
    ///                         
    ///====================================================================
	
	@Override
	protected void updateFirstStep(Context context, int appWidgetId)
	{
		super.updateFirstStep(context, appWidgetId);
	}
}
