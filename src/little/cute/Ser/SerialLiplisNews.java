//=======================================================================
//  ClassName : SerialLiplisNews
//  概要      : LiplisNewsシリアライザー
//
//  シリアル化: LiplisNews
//	extends   :
//	impliments:
//
//2013/03/01 ver3.2.0 LiplisNewsシリアライズ保存
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Ser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import android.content.Context;
import android.util.Log;
import little.cute.Web.LiplisNews;

public class SerialLiplisNews {

	///=============================
    /// ニュース
    private static final String SERIAL_FILE_NAME = "SerialLiplisNews.dat";


    /// <summary>
    /// オブジェクトの内容をファイルから読み込み復元する
    /// </summary>
    public static LiplisNews loadObject(Context context)
    {
    	// ファイルの存在チェック
        if (!(new File(context.getFilesDir().getPath() + "/" + SERIAL_FILE_NAME).exists())) {
            saveObject(context, new LiplisNews(context));
        }

    	try {
    	    FileInputStream fis = context.openFileInput(SERIAL_FILE_NAME);
    	    ObjectInputStream ois = new ObjectInputStream(fis);
    	    LiplisNews data = (LiplisNews) ois.readObject();
    	    ois.close();

    	    return data;
    	} catch (Exception e) {
    		 Log.d("TAG", "Error");
    		 return new LiplisNews(context);
    	}
    }


    /// <summary>
    /// オブジェクトの内容をファイルに保存する
    /// </summary>
    public static void saveObject(Context context,LiplisNews obj)
    {
        try {
            FileOutputStream fos = context.openFileOutput(SERIAL_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        } catch (Exception e) {
            Log.d("TAG", "Error");
        }
    }

}
