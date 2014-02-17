//=======================================================================
//  ClassName : LiplisViewListItem
//  概要      : リストビューアイテム
//
//	extends   :
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.View.LiplisLogListView;

public class LiplisViewListItem {
	 
	  private String description;
	 
	  public LiplisViewListItem(String title, String description){
	    this.description = description;
	  }
	  public LiplisViewListItem(String description){
		    this.description = description;
		  }
	 
	  public String getDescription(){
	    return description;
	  }
}