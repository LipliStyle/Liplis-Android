//=======================================================================
//  ClassName : ObjLeafAndEmotion
//  概要      : リーフエモーションオブジェクト
//              Clalis互換クラス
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Obj.Json;

public class ObjLeafAndEmotion {
    public String name;
    public int emotion;
    public int point;

    public ObjLeafAndEmotion()
    {
    }

    public ObjLeafAndEmotion(String name, int emotion, int point)
    {
        this.name = name;
        this.emotion = emotion;
        this.point = point;
    }
}
