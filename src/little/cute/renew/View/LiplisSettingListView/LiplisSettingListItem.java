//=======================================================================
//  ClassName : LiplisSettingListItem
//  概要      : 設定リストビューアイテム
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.View.LiplisSettingListView;

public class LiplisSettingListItem {

		private String name;
		private String description;

		public LiplisSettingListItem(String name, String description){
			this.name = name;
			this.description = description;
		}

		public final String getName() {
			return name;
		}

		public final void setName(String name) {
			this.name = name;
		}

		public final String getDescription() {
			return description;
		}

		public final void setDescription(String description) {
			this.description = description;
		}


}