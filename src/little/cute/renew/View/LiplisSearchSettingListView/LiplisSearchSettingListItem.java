//=======================================================================
//  ClassName : LiplisSearchSettingListItem
//  概要      : 設定リストビューアイテム
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.View.LiplisSearchSettingListView;

public class LiplisSearchSettingListItem {

		private int topicId;
		private String word;
		private int flgEnable;

		public LiplisSearchSettingListItem(int topicId, String word, int flgEnable){
			this.topicId = topicId;
			this.word = word;
			this.flgEnable = flgEnable;
		}

		public int getTopicId() {
			return topicId;
		}

		public void setTopicId(int topicId) {
			this.topicId = topicId;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public int getFlgEnable() {
			return flgEnable;
		}

		public void setFlgEnable(int flgEnable) {
			this.flgEnable = flgEnable;
		}

		public boolean getFlgEnableBit() {
			return this.flgEnable == 1;
		}


}