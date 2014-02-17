//=======================================================================
//  ClassName : ResLpsTopicSearchWord
//  概要      : LpsShortNewsJsonオブジェクト
//              Clalis互換クラス
//
//2013/09/21 ver3.5.0 話題検索設定新設
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Obj.Json;

public class ResLpsTopicSearchWord {

    public int topicId;
    public String word;
    public int flgEnable;

    public ResLpsTopicSearchWord()
    {
    	this.topicId = 0;
        this.word = "";
        this.flgEnable = 0;
    }
    public ResLpsTopicSearchWord(int topicId, String word, int flgEnable)
    {
        this.topicId = topicId;
        this.word = word;
        this.flgEnable = flgEnable;
    }

}
