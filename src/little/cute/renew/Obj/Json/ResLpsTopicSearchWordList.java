//=======================================================================
//  ClassName : ResLpsTopicSearchWordList
//  概要      : LpsShortNewsJsonオブジェクト
//              Clalis互換クラス
//
//2013/09/21 ver3.5.0 話題検索設定新設
//
//  Liplis Android
//  Copyright(c) 2009-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.Obj.Json;

import java.util.ArrayList;
import java.util.List;

public class ResLpsTopicSearchWordList {

    public List<ResLpsTopicSearchWord> wordList;

    public ResLpsTopicSearchWordList()
    {
    	wordList = new ArrayList<ResLpsTopicSearchWord>();
    }
    public ResLpsTopicSearchWordList(List<ResLpsTopicSearchWord> wordList)
    {
        this.wordList = wordList;
    }
}
