package little.cute.View.TitleAndDiscriptionListView;


import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.text.TextUtils;

import java.util.List;
 

public class CustomArrayAdapter extends ArrayAdapter<ListItem> {

 private Context mContext;
 private ListItem listItem;

 public CustomArrayAdapter(Context context, int textViewResourceId, List<ListItem> objects) {
   super(context, textViewResourceId, objects);
   mContext = context;
 }

 public View getView(final int position, View convertView, ViewGroup parent) {
   // 一行分のデータ取得
   listItem = this.getItem(position);

   // 一行分のレイアウトを作って返す
   LinearLayout layout = new LinearLayout(mContext);
   layout.setOrientation(LinearLayout.VERTICAL);
   layout.addView(_makeTitleTextView());
   layout.addView(_makeDescTextView());

   return layout;
 }

 // 以下、プライベート ----------------------------------------------------
 private TextView _makeTitleTextView(){
   TextView textView = new TextView(mContext);
   textView.setTextSize(20);
   textView.setTextColor(Color.rgb(255, 255, 255));
   textView.setText(listItem.getTitle());

   return textView;
 }

 private TextView _makeDescTextView(){
   TextView textView = new TextView(mContext);
   textView.setTextSize(15);
   textView.setSingleLine(true);
   textView.setEllipsize(TextUtils.TruncateAt.END);
   textView.setTextColor(Color.rgb(0, 0, 0));
   textView.setText(listItem.getDescription());

   return textView;
 }

}