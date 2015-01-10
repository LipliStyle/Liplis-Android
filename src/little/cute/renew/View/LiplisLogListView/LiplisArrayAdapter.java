//=======================================================================
//  ClassName : LiplisArrayAdapter
//  概要      : アレイアダプター
//
//	extends   : ArrayAdapter<LiplisViewListItem>
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.renew.View.LiplisLogListView;


import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.text.TextUtils;

import java.util.List;
 

public class LiplisArrayAdapter extends ArrayAdapter<LiplisViewListItem> {

 private Context mContext;
 private LiplisViewListItem listItem;

 public LiplisArrayAdapter(Context context, int textViewResourceId, List<LiplisViewListItem> objects) {
   super(context, textViewResourceId, objects);
   mContext = context;
 }

 public View getView(final int position, View convertView, ViewGroup parent) {
   // 一行分のデータ取得
   listItem = this.getItem(position);

   // 一行分のレイアウトを作って返す
   LinearLayout layout = new LinearLayout(mContext);
   layout.setOrientation(LinearLayout.VERTICAL);
   layout.addView(_makeDescTextView());

   return layout;
 }

 private TextView _makeDescTextView(){
   TextView textView = new TextView(mContext);
   textView.setTextSize(20);
   //textView.setSingleLine(true);
   textView.setLines(2);
   textView.setMaxLines(2);
   textView.setEllipsize(TextUtils.TruncateAt.END);
   textView.setTextColor(Color.rgb(0, 0, 0));
   textView.setText(listItem.getDescription());

   return textView;
 }

}