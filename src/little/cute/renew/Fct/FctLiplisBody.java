//=======================================================================
//  ClassName : FctLiplisBody
//  概要      	  : ボディファクトリー
//
//	extends   : 
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.Fct;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


public class FctLiplisBody {
	
	public Bitmap createBody(int width, int height,
			                Bitmap window, int windowX, int windowY,
							Bitmap body, int bodyX, int bodyY,
							Bitmap eye, int eyeX, int eyeY,
							Bitmap mouth, int mouthX, int mouthY
							) {
		
		//ビットマップ作成
	  	Bitmap resBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444); 
		
	  	//キャンバス作成
	  	Canvas offScreen = new Canvas(resBmp); 
	  	
	  	//ビットマップの合成
	  	offScreen.drawBitmap(window, windowX, windowY, (Paint)null); 	//吹き出し
        offScreen.drawBitmap(body, bodyX, bodyY, (Paint)null); 			//ボディ
    	offScreen.drawBitmap(eye, eyeX, eyeY, (Paint)null); 			//目
        offScreen.drawBitmap(mouth, mouthX, mouthY, (Paint)null); 		//口

        return resBmp;
	}
}
