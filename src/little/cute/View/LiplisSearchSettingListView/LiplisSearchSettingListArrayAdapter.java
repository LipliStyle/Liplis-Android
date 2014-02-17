//=======================================================================
//  ClassName : LiplisSearchSettingListArrayAdapter
//  概要      : アレイアダプター
//
//	extends   : ArrayAdapter<LiplisSettingListItem>
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2013 sachin. All Rights Reserved.
//=======================================================================
package little.cute.View.LiplisSearchSettingListView;

import java.util.List;

import little.cute.R;
import little.cute.Activity.LiplisWidgetConfigurationSearchSettingRegist;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class LiplisSearchSettingListArrayAdapter extends ArrayAdapter<LiplisSearchSettingListItem> {
	private LayoutInflater layoutInflater_;

	private LiplisWidgetConfigurationSearchSettingRegist act;

	public LiplisSearchSettingListArrayAdapter(Context context, int textViewResourceId, List<LiplisSearchSettingListItem> objects) {
		 super(context, textViewResourceId, objects);
		 layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 this.act = (LiplisWidgetConfigurationSearchSettingRegist)context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 // 特定の行(position)のデータを得る
		LiplisSearchSettingListItem item = (LiplisSearchSettingListItem)getItem(position);

		 // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		 if (null == convertView) {
			 convertView = layoutInflater_.inflate(R.layout.cuslayoutviewrow_topicset, null);
		 }

		 //ワードをセットする
		 TextView textView= (TextView)convertView.findViewById(R.id.txtCusLayoutViewRow_t);
		 textView.setText(item.getWord());

		 //チェックボックスをセットする
		 CheckBox cb =  (CheckBox)convertView.findViewById(R.id.chkCusLayoutViewRow_t);
		 cb.setChecked(item.getFlgEnableBit());

		 // チェックボックスがクリックされた時に呼び出されるコールバックリスナーを登録します
		 cb.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
			    CheckBox checkBox = (CheckBox) v;
			    act.checkOn(checkBox.isChecked());
			}
		});


		 return convertView;
	}
}