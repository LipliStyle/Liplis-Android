//=======================================================================
//  ClassName : LiplisSettingListArrayAdapter
//  概要      : アレイアダプター
//
//	extends   : ArrayAdapter<LiplisSettingListItem>
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.renew.View.LiplisSettingListView;

import java.util.List;

import little.cute.renew.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class LiplisSettingListArrayAdapter extends ArrayAdapter<LiplisSettingListItem> {
	private LayoutInflater layoutInflater_;

	public LiplisSettingListArrayAdapter(Context context, int textViewResourceId, List<LiplisSettingListItem> objects) {
		 super(context, textViewResourceId, objects);
		 layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 // 特定の行(position)のデータを得る
		LiplisSettingListItem item = (LiplisSettingListItem)getItem(position);

		 // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		 if (null == convertView) {
			 convertView = layoutInflater_.inflate(R.layout.cuslayoutviewrow, null);
		 }

		 // CustomDataのデータをViewの各Widgetにセットする
		 TextView textView;
		 textView = (TextView)convertView.findViewById(R.id.cusLayoutViewRow1);
		 textView.setText(item.getName());

		 TextView textView2;
		 textView2 = (TextView)convertView.findViewById(R.id.cusLayoutViewRow2);
		 textView2.setText(item.getDescription());

		 return convertView;
	}
}