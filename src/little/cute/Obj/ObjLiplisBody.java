//=======================================================================
//  ClassName : ObjLiplisBody
//  概要      : リプリスボディオブジェクト
//
//	extends   :
//	impliments:
//
//  LiplisAndroidシステム
//  Copyright(c) 2011-2011 sachin. All Rights Reserved.
//=======================================================================
package little.cute.Obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import little.cute.R;

public class ObjLiplisBody {

	///=============================
    /// ボディ
	protected List<ObjBody> normalList;			//ノーマルリスト
	protected List<ObjBody> joy_p_List;			//ジョイ+
	protected List<ObjBody> joy_m_List;			//ジョイ-
	protected List<ObjBody> admiration_p_List;	//アドミレイション+
	protected List<ObjBody> admiration_m_List;	//アドミレイション-
	protected List<ObjBody> peace_p_List;		//ピース+
	protected List<ObjBody> peace_m_List;		//ピース-
	protected List<ObjBody> ecstasy_p_List;		//エクスタシー+
	protected List<ObjBody> ecstasy_m_List;		//エクスタシー-
	protected List<ObjBody> amazement_p_List;	//アメイズメント+
	protected List<ObjBody> amazement_m_List;		//アメイズメント-
	protected List<ObjBody> rage_p_List;			//レイジ+
	protected List<ObjBody> rage_m_List;			//レイジ-
	protected List<ObjBody> interest_p_List;		//インタレスト+
	protected List<ObjBody> interest_m_List;		//インタレスト-
	protected List<ObjBody> respect_p_List;		//リスペクト+
	protected List<ObjBody> respect_m_List;		//リスペクト-
	protected List<ObjBody> calmly_p_List;		//クラメリー+
	protected List<ObjBody> calmly_m_List;		//クラメリー-
	protected List<ObjBody> proud_p_List;		//プラウド+
	protected List<ObjBody> proud_m_List;		//プラウド-

	///=============================
    /// 破損ボディ
	protected List<ObjBody> batteryHi_List;			//小破 2013/10/27 ver3.6.0
	protected List<ObjBody> batteryMid_List;			//中破 2013/10/27 ver3.6.0
	protected List<ObjBody> batteryLow_List;			//大破 2013/10/27 ver3.6.0

    /// <summary>
    /// コンストラクター
    /// </summary>
	public ObjLiplisBody()
	{
		normalList 			= new ArrayList<ObjBody>();
		joy_p_List 			= new ArrayList<ObjBody>();
		joy_m_List 			= new ArrayList<ObjBody>();
		admiration_p_List 	= new ArrayList<ObjBody>();
		admiration_m_List 	= new ArrayList<ObjBody>();
		peace_p_List 		= new ArrayList<ObjBody>();
		peace_m_List		= new ArrayList<ObjBody>();
		ecstasy_p_List		= new ArrayList<ObjBody>();
		ecstasy_m_List		= new ArrayList<ObjBody>();
		amazement_p_List	= new ArrayList<ObjBody>();
		amazement_m_List	= new ArrayList<ObjBody>();
		rage_p_List			= new ArrayList<ObjBody>();
		rage_m_List			= new ArrayList<ObjBody>();
		interest_p_List		= new ArrayList<ObjBody>();
		interest_m_List		= new ArrayList<ObjBody>();
		respect_p_List		= new ArrayList<ObjBody>();
		respect_m_List		= new ArrayList<ObjBody>();
		calmly_p_List		= new ArrayList<ObjBody>();
		calmly_m_List		= new ArrayList<ObjBody>();
		proud_p_List		= new ArrayList<ObjBody>();
		proud_m_List		= new ArrayList<ObjBody>();

		batteryHi_List= new ArrayList<ObjBody>();
		batteryMid_List= new ArrayList<ObjBody>();
		batteryLow_List= new ArrayList<ObjBody>();

		//リストをセットする。
		//Excelマクロでソースを生成する
		setList();
	}

    /// <summary>
    /// Excelマクロより自動生成する
    /// </summary>
	protected void setList()
	{
		normalList.add(new ObjBody(R.drawable.normal_1_1_1, R.drawable.normal_1_1_2, R.drawable.normal_1_2_1, R.drawable.normal_1_2_2, R.drawable.normal_1_3_1, R.drawable.normal_1_3_2));
		normalList.add(new ObjBody(R.drawable.normal_2_1_1, R.drawable.normal_2_1_2, R.drawable.normal_2_2_1, R.drawable.normal_2_2_2, R.drawable.normal_2_3_1, R.drawable.normal_2_3_2));
		normalList.add(new ObjBody(R.drawable.normal_3_1_1, R.drawable.normal_3_1_2, R.drawable.normal_3_2_1, R.drawable.normal_3_2_2, R.drawable.normal_3_3_1, R.drawable.normal_3_3_2));
		normalList.add(new ObjBody(R.drawable.normal_4_1_1, R.drawable.normal_4_1_2, R.drawable.normal_4_2_1, R.drawable.normal_4_2_2, R.drawable.normal_4_3_1, R.drawable.normal_4_3_2));
		normalList.add(new ObjBody(R.drawable.normal_5_1_1, R.drawable.normal_5_1_2, R.drawable.normal_5_2_1, R.drawable.normal_5_2_2, R.drawable.normal_5_3_1, R.drawable.normal_5_3_2));
		joy_p_List.add(new ObjBody(R.drawable.joy_p_1_1_1, R.drawable.joy_p_1_1_2, R.drawable.joy_p_1_2_1, R.drawable.joy_p_1_2_2, R.drawable.joy_p_1_3_1, R.drawable.joy_p_1_3_2));
		joy_p_List.add(new ObjBody(R.drawable.joy_p_2_1_1, R.drawable.joy_p_2_1_2, R.drawable.joy_p_2_2_1, R.drawable.joy_p_2_2_2, R.drawable.joy_p_2_3_1, R.drawable.joy_p_2_3_2));
		joy_p_List.add(new ObjBody(R.drawable.joy_p_3_1_1, R.drawable.joy_p_3_1_2, R.drawable.joy_p_3_2_1, R.drawable.joy_p_3_2_2, R.drawable.joy_p_3_3_1, R.drawable.joy_p_3_3_2));
		joy_p_List.add(new ObjBody(R.drawable.joy_p_4_1_1, R.drawable.joy_p_4_1_2, R.drawable.joy_p_4_2_1, R.drawable.joy_p_4_2_2, R.drawable.joy_p_4_3_1, R.drawable.joy_p_4_3_2));
		joy_m_List.add(new ObjBody(R.drawable.joy_m_1_1_1, R.drawable.joy_m_1_1_2, R.drawable.joy_m_1_2_1, R.drawable.joy_m_1_2_2, R.drawable.joy_m_1_3_1, R.drawable.joy_m_1_3_2));
		joy_m_List.add(new ObjBody(R.drawable.joy_m_2_1_1, R.drawable.joy_m_2_1_2, R.drawable.joy_m_2_2_1, R.drawable.joy_m_2_2_2, R.drawable.joy_m_2_3_1, R.drawable.joy_m_2_3_2));
		joy_m_List.add(new ObjBody(R.drawable.joy_m_3_1_1, R.drawable.joy_m_3_1_2, R.drawable.joy_m_3_2_1, R.drawable.joy_m_3_2_2, R.drawable.joy_m_3_3_1, R.drawable.joy_m_3_3_2));
		joy_m_List.add(new ObjBody(R.drawable.joy_m_4_1_1, R.drawable.joy_m_4_1_2, R.drawable.joy_m_4_2_1, R.drawable.joy_m_4_2_2, R.drawable.joy_m_4_3_1, R.drawable.joy_m_4_3_2));
		admiration_p_List.add(new ObjBody(R.drawable.admiration_p_1_1_1, R.drawable.admiration_p_1_1_2, R.drawable.admiration_p_1_2_1, R.drawable.admiration_p_1_2_2, R.drawable.admiration_p_1_3_1, R.drawable.admiration_p_1_3_2));
		admiration_p_List.add(new ObjBody(R.drawable.admiration_p_2_1_1, R.drawable.admiration_p_2_1_2, R.drawable.admiration_p_2_2_1, R.drawable.admiration_p_2_2_2, R.drawable.admiration_p_2_3_1, R.drawable.admiration_p_2_3_2));
		admiration_p_List.add(new ObjBody(R.drawable.admiration_p_3_1_1, R.drawable.admiration_p_3_1_2, R.drawable.admiration_p_3_2_1, R.drawable.admiration_p_3_2_2, R.drawable.admiration_p_3_3_1, R.drawable.admiration_p_3_3_2));
		admiration_m_List.add(new ObjBody(R.drawable.admiration_m_1_1_1, R.drawable.admiration_m_1_1_2, R.drawable.admiration_m_1_2_1, R.drawable.admiration_m_1_2_2, R.drawable.admiration_m_1_3_1, R.drawable.admiration_m_1_3_2));
		admiration_m_List.add(new ObjBody(R.drawable.admiration_m_2_1_1, R.drawable.admiration_m_2_1_2, R.drawable.admiration_m_2_2_1, R.drawable.admiration_m_2_2_2, R.drawable.admiration_m_2_3_1, R.drawable.admiration_m_2_3_2));
		admiration_m_List.add(new ObjBody(R.drawable.admiration_m_3_1_1, R.drawable.admiration_m_3_1_2, R.drawable.admiration_m_3_2_1, R.drawable.admiration_m_3_2_2, R.drawable.admiration_m_3_3_1, R.drawable.admiration_m_3_3_2));
		admiration_m_List.add(new ObjBody(R.drawable.admiration_m_4_1_1, R.drawable.admiration_m_4_1_2, R.drawable.admiration_m_4_2_1, R.drawable.admiration_m_4_2_2, R.drawable.admiration_m_4_3_1, R.drawable.admiration_m_4_3_2));
		admiration_m_List.add(new ObjBody(R.drawable.admiration_m_5_1_1, R.drawable.admiration_m_5_1_2, R.drawable.admiration_m_5_2_1, R.drawable.admiration_m_5_2_2, R.drawable.admiration_m_5_3_1, R.drawable.admiration_m_5_3_2));
		peace_p_List.add(new ObjBody(R.drawable.peace_p_1_1_1, R.drawable.peace_p_1_1_2, R.drawable.peace_p_1_2_1, R.drawable.peace_p_1_2_2, R.drawable.peace_p_1_3_1, R.drawable.peace_p_1_3_2));
		peace_p_List.add(new ObjBody(R.drawable.peace_p_2_1_1, R.drawable.peace_p_2_1_2, R.drawable.peace_p_2_2_1, R.drawable.peace_p_2_2_2, R.drawable.peace_p_2_3_1, R.drawable.peace_p_2_3_2));
		peace_m_List.add(new ObjBody(R.drawable.peace_m_1_1_1, R.drawable.peace_m_1_1_2, R.drawable.peace_m_1_2_1, R.drawable.peace_m_1_2_2, R.drawable.peace_m_1_3_1, R.drawable.peace_m_1_3_2));
		peace_m_List.add(new ObjBody(R.drawable.peace_m_2_1_1, R.drawable.peace_m_2_1_2, R.drawable.peace_m_2_2_1, R.drawable.peace_m_2_2_2, R.drawable.peace_m_2_3_1, R.drawable.peace_m_2_3_2));
		peace_m_List.add(new ObjBody(R.drawable.peace_m_3_1_1, R.drawable.peace_m_3_1_2, R.drawable.peace_m_3_2_1, R.drawable.peace_m_3_2_2, R.drawable.peace_m_3_3_1, R.drawable.peace_m_3_3_2));
		ecstasy_p_List.add(new ObjBody(R.drawable.ecstasy_p_1_1_1, R.drawable.ecstasy_p_1_1_2, R.drawable.ecstasy_p_1_2_1, R.drawable.ecstasy_p_1_2_2, R.drawable.ecstasy_p_1_3_1, R.drawable.ecstasy_p_1_3_2));
		ecstasy_p_List.add(new ObjBody(R.drawable.ecstasy_p_2_1_1, R.drawable.ecstasy_p_2_1_2, R.drawable.ecstasy_p_2_2_1, R.drawable.ecstasy_p_2_2_2, R.drawable.ecstasy_p_2_3_1, R.drawable.ecstasy_p_2_3_2));
		ecstasy_p_List.add(new ObjBody(R.drawable.ecstasy_p_3_1_1, R.drawable.ecstasy_p_3_1_2, R.drawable.ecstasy_p_3_2_1, R.drawable.ecstasy_p_3_2_2, R.drawable.ecstasy_p_3_3_1, R.drawable.ecstasy_p_3_3_2));
		ecstasy_m_List.add(new ObjBody(R.drawable.ecstasy_m_1_1_1, R.drawable.ecstasy_m_1_1_2, R.drawable.ecstasy_m_1_2_1, R.drawable.ecstasy_m_1_2_2, R.drawable.ecstasy_m_1_3_1, R.drawable.ecstasy_m_1_3_2));
		ecstasy_m_List.add(new ObjBody(R.drawable.ecstasy_m_2_1_1, R.drawable.ecstasy_m_2_1_2, R.drawable.ecstasy_m_2_2_1, R.drawable.ecstasy_m_2_2_2, R.drawable.ecstasy_m_2_3_1, R.drawable.ecstasy_m_2_3_2));
		ecstasy_m_List.add(new ObjBody(R.drawable.ecstasy_m_3_1_1, R.drawable.ecstasy_m_3_1_2, R.drawable.ecstasy_m_3_2_1, R.drawable.ecstasy_m_3_2_2, R.drawable.ecstasy_m_3_3_1, R.drawable.ecstasy_m_3_3_2));
		amazement_p_List.add(new ObjBody(R.drawable.amazement_p_1_1_1, R.drawable.amazement_p_1_1_2, R.drawable.amazement_p_1_2_1, R.drawable.amazement_p_1_2_2, R.drawable.amazement_p_1_3_1, R.drawable.amazement_p_1_3_2));
		amazement_p_List.add(new ObjBody(R.drawable.amazement_p_2_1_1, R.drawable.amazement_p_2_1_2, R.drawable.amazement_p_2_2_1, R.drawable.amazement_p_2_2_2, R.drawable.amazement_p_2_3_1, R.drawable.amazement_p_2_3_2));
		amazement_p_List.add(new ObjBody(R.drawable.amazement_p_3_1_1, R.drawable.amazement_p_3_1_2, R.drawable.amazement_p_3_2_1, R.drawable.amazement_p_3_2_2, R.drawable.amazement_p_3_3_1, R.drawable.amazement_p_3_3_2));
		amazement_m_List.add(new ObjBody(R.drawable.amazement_m_1_1_1, R.drawable.amazement_m_1_1_2, R.drawable.amazement_m_1_2_1, R.drawable.amazement_m_1_2_2, R.drawable.amazement_m_1_3_1, R.drawable.amazement_m_1_3_2));
		amazement_m_List.add(new ObjBody(R.drawable.amazement_m_2_1_1, R.drawable.amazement_m_2_1_2, R.drawable.amazement_m_2_2_1, R.drawable.amazement_m_2_2_2, R.drawable.amazement_m_2_3_1, R.drawable.amazement_m_2_3_2));
		amazement_m_List.add(new ObjBody(R.drawable.amazement_m_3_1_1, R.drawable.amazement_m_3_1_2, R.drawable.amazement_m_3_2_1, R.drawable.amazement_m_3_2_2, R.drawable.amazement_m_3_3_1, R.drawable.amazement_m_3_3_2));
		rage_p_List.add(new ObjBody(R.drawable.rage_p_1_1_1, R.drawable.rage_p_1_1_2, R.drawable.rage_p_1_2_1, R.drawable.rage_p_1_2_2, R.drawable.rage_p_1_3_1, R.drawable.rage_p_1_3_2));
		rage_p_List.add(new ObjBody(R.drawable.rage_p_2_1_1, R.drawable.rage_p_2_1_2, R.drawable.rage_p_2_2_1, R.drawable.rage_p_2_2_2, R.drawable.rage_p_2_3_1, R.drawable.rage_p_2_3_2));
		rage_p_List.add(new ObjBody(R.drawable.rage_p_3_1_1, R.drawable.rage_p_3_1_2, R.drawable.rage_p_3_2_1, R.drawable.rage_p_3_2_2, R.drawable.rage_p_3_3_1, R.drawable.rage_p_3_3_2));
		rage_m_List.add(new ObjBody(R.drawable.rage_m_1_1_1, R.drawable.rage_m_1_1_2, R.drawable.rage_m_1_2_1, R.drawable.rage_m_1_2_2, R.drawable.rage_m_1_3_1, R.drawable.rage_m_1_3_2));
		rage_m_List.add(new ObjBody(R.drawable.rage_m_2_1_1, R.drawable.rage_m_2_1_2, R.drawable.rage_m_2_2_1, R.drawable.rage_m_2_2_2, R.drawable.rage_m_2_3_1, R.drawable.rage_m_2_3_2));
		interest_p_List.add(new ObjBody(R.drawable.interest_p_1_1_1, R.drawable.interest_p_1_1_2, R.drawable.interest_p_1_2_1, R.drawable.interest_p_1_2_2, R.drawable.interest_p_1_3_1, R.drawable.interest_p_1_3_2));
		interest_p_List.add(new ObjBody(R.drawable.interest_p_2_1_1, R.drawable.interest_p_2_1_2, R.drawable.interest_p_2_2_1, R.drawable.interest_p_2_2_2, R.drawable.interest_p_2_3_1, R.drawable.interest_p_2_3_2));
		interest_p_List.add(new ObjBody(R.drawable.interest_p_3_1_1, R.drawable.interest_p_3_1_2, R.drawable.interest_p_3_2_1, R.drawable.interest_p_3_2_2, R.drawable.interest_p_3_3_1, R.drawable.interest_p_3_3_2));
		interest_p_List.add(new ObjBody(R.drawable.interest_p_4_1_1, R.drawable.interest_p_4_1_2, R.drawable.interest_p_4_2_1, R.drawable.interest_p_4_2_2, R.drawable.interest_p_4_3_1, R.drawable.interest_p_4_3_2));
		interest_p_List.add(new ObjBody(R.drawable.interest_p_5_1_1, R.drawable.interest_p_5_1_2, R.drawable.interest_p_5_2_1, R.drawable.interest_p_5_2_2, R.drawable.interest_p_5_3_1, R.drawable.interest_p_5_3_2));
		interest_m_List.add(new ObjBody(R.drawable.interest_m_1_1_1, R.drawable.interest_m_1_1_2, R.drawable.interest_m_1_2_1, R.drawable.interest_m_1_2_2, R.drawable.interest_m_1_3_1, R.drawable.interest_m_1_3_2));
		interest_m_List.add(new ObjBody(R.drawable.interest_m_2_1_1, R.drawable.interest_m_2_1_2, R.drawable.interest_m_2_2_1, R.drawable.interest_m_2_2_2, R.drawable.interest_m_2_3_1, R.drawable.interest_m_2_3_2));
		respect_p_List.add(new ObjBody(R.drawable.respect_p_1_1_1, R.drawable.respect_p_1_1_2, R.drawable.respect_p_1_2_1, R.drawable.respect_p_1_2_2, R.drawable.respect_p_1_3_1, R.drawable.respect_p_1_3_2));
		respect_p_List.add(new ObjBody(R.drawable.respect_p_2_1_1, R.drawable.respect_p_2_1_2, R.drawable.respect_p_2_2_1, R.drawable.respect_p_2_2_2, R.drawable.respect_p_2_3_1, R.drawable.respect_p_2_3_2));
		respect_p_List.add(new ObjBody(R.drawable.respect_p_3_1_1, R.drawable.respect_p_3_1_2, R.drawable.respect_p_3_2_1, R.drawable.respect_p_3_2_2, R.drawable.respect_p_3_3_1, R.drawable.respect_p_3_3_2));
		respect_m_List.add(new ObjBody(R.drawable.respect_m_1_1_1, R.drawable.respect_m_1_1_2, R.drawable.respect_m_1_2_1, R.drawable.respect_m_1_2_2, R.drawable.respect_m_1_3_1, R.drawable.respect_m_1_3_2));
		respect_m_List.add(new ObjBody(R.drawable.respect_m_2_1_1, R.drawable.respect_m_2_1_2, R.drawable.respect_m_2_2_1, R.drawable.respect_m_2_2_2, R.drawable.respect_m_2_3_1, R.drawable.respect_m_2_3_2));
		calmly_p_List.add(new ObjBody(R.drawable.calmly_p_1_1_1, R.drawable.calmly_p_1_1_2, R.drawable.calmly_p_1_2_1, R.drawable.calmly_p_1_2_2, R.drawable.calmly_p_1_3_1, R.drawable.calmly_p_1_3_2));
		calmly_p_List.add(new ObjBody(R.drawable.calmly_p_2_1_1, R.drawable.calmly_p_2_1_2, R.drawable.calmly_p_2_2_1, R.drawable.calmly_p_2_2_2, R.drawable.calmly_p_2_3_1, R.drawable.calmly_p_2_3_2));
		calmly_m_List.add(new ObjBody(R.drawable.calmly_m_1_1_1, R.drawable.calmly_m_1_1_2, R.drawable.calmly_m_1_2_1, R.drawable.calmly_m_1_2_2, R.drawable.calmly_m_1_3_1, R.drawable.calmly_m_1_3_2));
		calmly_m_List.add(new ObjBody(R.drawable.calmly_m_2_1_1, R.drawable.calmly_m_2_1_2, R.drawable.calmly_m_2_2_1, R.drawable.calmly_m_2_2_2, R.drawable.calmly_m_2_3_1, R.drawable.calmly_m_2_3_2));
		calmly_m_List.add(new ObjBody(R.drawable.calmly_m_3_1_1, R.drawable.calmly_m_3_1_2, R.drawable.calmly_m_3_2_1, R.drawable.calmly_m_3_2_2, R.drawable.calmly_m_3_3_1, R.drawable.calmly_m_3_3_2));
		calmly_m_List.add(new ObjBody(R.drawable.calmly_m_4_1_1, R.drawable.calmly_m_4_1_2, R.drawable.calmly_m_4_2_1, R.drawable.calmly_m_4_2_2, R.drawable.calmly_m_4_3_1, R.drawable.calmly_m_4_3_2));
		proud_p_List.add(new ObjBody(R.drawable.proud_p_1_1_1, R.drawable.proud_p_1_1_2, R.drawable.proud_p_1_2_1, R.drawable.proud_p_1_2_2, R.drawable.proud_p_1_3_1, R.drawable.proud_p_1_3_2));
		proud_m_List.add(new ObjBody(R.drawable.proud_m_1_1_1, R.drawable.proud_m_1_1_2, R.drawable.proud_m_1_2_1, R.drawable.proud_m_1_2_2, R.drawable.proud_m_1_3_1, R.drawable.proud_m_1_3_2));
		proud_m_List.add(new ObjBody(R.drawable.proud_m_2_1_1, R.drawable.proud_m_2_1_2, R.drawable.proud_m_2_2_1, R.drawable.proud_m_2_2_2, R.drawable.proud_m_2_3_1, R.drawable.proud_m_2_3_2));
		proud_m_List.add(new ObjBody(R.drawable.proud_m_3_1_1, R.drawable.proud_m_3_1_2, R.drawable.proud_m_3_2_1, R.drawable.proud_m_3_2_2, R.drawable.proud_m_3_3_1, R.drawable.proud_m_3_3_2));

//		sDamage_List.add(new ObjBody(R.drawable.joy_m_1_1_1, R.drawable.joy_m_1_1_2, R.drawable.joy_m_1_2_1, R.drawable.joy_m_1_2_2, R.drawable.joy_m_1_3_1, R.drawable.joy_m_1_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.joy_m_2_1_1, R.drawable.joy_m_2_1_2, R.drawable.joy_m_2_2_1, R.drawable.joy_m_2_2_2, R.drawable.joy_m_2_3_1, R.drawable.joy_m_2_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.joy_m_3_1_1, R.drawable.joy_m_3_1_2, R.drawable.joy_m_3_2_1, R.drawable.joy_m_3_2_2, R.drawable.joy_m_3_3_1, R.drawable.joy_m_3_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.joy_m_4_1_1, R.drawable.joy_m_4_1_2, R.drawable.joy_m_4_2_1, R.drawable.joy_m_4_2_2, R.drawable.joy_m_4_3_1, R.drawable.joy_m_4_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.admiration_m_1_1_1, R.drawable.admiration_m_1_1_2, R.drawable.admiration_m_1_2_1, R.drawable.admiration_m_1_2_2, R.drawable.admiration_m_1_3_1, R.drawable.admiration_m_1_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.admiration_m_2_1_1, R.drawable.admiration_m_2_1_2, R.drawable.admiration_m_2_2_1, R.drawable.admiration_m_2_2_2, R.drawable.admiration_m_2_3_1, R.drawable.admiration_m_2_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.admiration_m_3_1_1, R.drawable.admiration_m_3_1_2, R.drawable.admiration_m_3_2_1, R.drawable.admiration_m_3_2_2, R.drawable.admiration_m_3_3_1, R.drawable.admiration_m_3_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.admiration_m_4_1_1, R.drawable.admiration_m_4_1_2, R.drawable.admiration_m_4_2_1, R.drawable.admiration_m_4_2_2, R.drawable.admiration_m_4_3_1, R.drawable.admiration_m_4_3_2));
//		sDamage_List.add(new ObjBody(R.drawable.admiration_m_5_1_1, R.drawable.admiration_m_5_1_2, R.drawable.admiration_m_5_2_1, R.drawable.admiration_m_5_2_2, R.drawable.admiration_m_5_3_1, R.drawable.admiration_m_5_3_2));
//
//		mDamage_List.add(new ObjBody(R.drawable.joy_m_1_1_1, R.drawable.joy_m_1_1_2, R.drawable.joy_m_1_2_1, R.drawable.joy_m_1_2_2, R.drawable.joy_m_1_3_1, R.drawable.joy_m_1_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.joy_m_2_1_1, R.drawable.joy_m_2_1_2, R.drawable.joy_m_2_2_1, R.drawable.joy_m_2_2_2, R.drawable.joy_m_2_3_1, R.drawable.joy_m_2_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.joy_m_3_1_1, R.drawable.joy_m_3_1_2, R.drawable.joy_m_3_2_1, R.drawable.joy_m_3_2_2, R.drawable.joy_m_3_3_1, R.drawable.joy_m_3_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.joy_m_4_1_1, R.drawable.joy_m_4_1_2, R.drawable.joy_m_4_2_1, R.drawable.joy_m_4_2_2, R.drawable.joy_m_4_3_1, R.drawable.joy_m_4_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.admiration_m_1_1_1, R.drawable.admiration_m_1_1_2, R.drawable.admiration_m_1_2_1, R.drawable.admiration_m_1_2_2, R.drawable.admiration_m_1_3_1, R.drawable.admiration_m_1_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.admiration_m_2_1_1, R.drawable.admiration_m_2_1_2, R.drawable.admiration_m_2_2_1, R.drawable.admiration_m_2_2_2, R.drawable.admiration_m_2_3_1, R.drawable.admiration_m_2_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.admiration_m_3_1_1, R.drawable.admiration_m_3_1_2, R.drawable.admiration_m_3_2_1, R.drawable.admiration_m_3_2_2, R.drawable.admiration_m_3_3_1, R.drawable.admiration_m_3_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.admiration_m_4_1_1, R.drawable.admiration_m_4_1_2, R.drawable.admiration_m_4_2_1, R.drawable.admiration_m_4_2_2, R.drawable.admiration_m_4_3_1, R.drawable.admiration_m_4_3_2));
//		mDamage_List.add(new ObjBody(R.drawable.admiration_m_5_1_1, R.drawable.admiration_m_5_1_2, R.drawable.admiration_m_5_2_1, R.drawable.admiration_m_5_2_2, R.drawable.admiration_m_5_3_1, R.drawable.admiration_m_5_3_2));

		batteryLow_List.add(new ObjBody(R.drawable.joy_m_1_1_1, R.drawable.joy_m_1_1_2, R.drawable.joy_m_1_2_1, R.drawable.joy_m_1_2_2, R.drawable.joy_m_1_3_1, R.drawable.joy_m_1_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.joy_m_2_1_1, R.drawable.joy_m_2_1_2, R.drawable.joy_m_2_2_1, R.drawable.joy_m_2_2_2, R.drawable.joy_m_2_3_1, R.drawable.joy_m_2_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.joy_m_3_1_1, R.drawable.joy_m_3_1_2, R.drawable.joy_m_3_2_1, R.drawable.joy_m_3_2_2, R.drawable.joy_m_3_3_1, R.drawable.joy_m_3_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.joy_m_4_1_1, R.drawable.joy_m_4_1_2, R.drawable.joy_m_4_2_1, R.drawable.joy_m_4_2_2, R.drawable.joy_m_4_3_1, R.drawable.joy_m_4_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.admiration_m_1_1_1, R.drawable.admiration_m_1_1_2, R.drawable.admiration_m_1_2_1, R.drawable.admiration_m_1_2_2, R.drawable.admiration_m_1_3_1, R.drawable.admiration_m_1_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.admiration_m_2_1_1, R.drawable.admiration_m_2_1_2, R.drawable.admiration_m_2_2_1, R.drawable.admiration_m_2_2_2, R.drawable.admiration_m_2_3_1, R.drawable.admiration_m_2_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.admiration_m_3_1_1, R.drawable.admiration_m_3_1_2, R.drawable.admiration_m_3_2_1, R.drawable.admiration_m_3_2_2, R.drawable.admiration_m_3_3_1, R.drawable.admiration_m_3_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.admiration_m_4_1_1, R.drawable.admiration_m_4_1_2, R.drawable.admiration_m_4_2_1, R.drawable.admiration_m_4_2_2, R.drawable.admiration_m_4_3_1, R.drawable.admiration_m_4_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.admiration_m_5_1_1, R.drawable.admiration_m_5_1_2, R.drawable.admiration_m_5_2_1, R.drawable.admiration_m_5_2_2, R.drawable.admiration_m_5_3_1, R.drawable.admiration_m_5_3_2));

	}


    ///====================================================================
    ///
    ///                           要素の取得
    ///
    ///====================================================================
    /// <summary>
    /// エモーション、ポイント、まばたき状態、口パク状態からIDを取得する
    /// </summary>
	public ObjBody getLiplisBody(int emotion, int point){
		ObjBody body = new ObjBody();
		try
		{
			if(point >=0)
			{
				switch(emotion)
				{
					case 0:body = selectBody(normalList);break;
					case 1:body = selectBody(joy_p_List);break;
					case 2:body = selectBody(admiration_p_List);break;
					case 3:body = selectBody(peace_p_List);break;
					case 4:body = selectBody(ecstasy_p_List);break;
					case 5:body = selectBody(amazement_p_List);break;
					case 6:body = selectBody(rage_p_List);break;
					case 7:body = selectBody(interest_p_List);break;
					case 8:body = selectBody(respect_p_List);break;
					case 9:body = selectBody(calmly_p_List);break;
					case 10:body = selectBody(proud_p_List);break;
					default:body = selectBody(normalList);break;
				}
			}
			else
			{
				switch(emotion)
				{
					case 0:body = selectBody(normalList);break;
					case 1:body = selectBody(joy_m_List);break;
					case 2:body = selectBody(admiration_m_List);break;
					case 3:body = selectBody(peace_m_List);break;
					case 4:body = selectBody(ecstasy_m_List);break;
					case 5:body = selectBody(amazement_m_List);break;
					case 6:body = selectBody(rage_m_List);break;
					case 7:body = selectBody(interest_m_List);break;
					case 8:body = selectBody(respect_m_List);break;
					case 9:body = selectBody(calmly_m_List);break;
					case 10:body = selectBody(proud_m_List);break;
					default:body = selectBody(normalList);break;
				}
			}

			return body;
		}catch(Exception e)
		{
			return normalList.get(0);
		}
	}
	/// <summary>
    /// 健康状態状態からIDを取得する
    /// </summary>
	public ObjBody getLiplisBodyHelth(int helth,int emotion, int point){
		try
		{
			//小破以上
			if(helth > 50)
			{
				if(batteryHi_List.size() == 0)
				{
					return getLiplisBody(emotion, point);
				}
				else
				{
					return selectBody(batteryHi_List);
				}
			}
			//中破
			else if(helth > 25)
			{
				if(batteryMid_List.size() == 0)
				{
					return getLiplisBody(emotion, point);
				}
				else
				{
					return selectBody(batteryMid_List);
				}
			}
			//大破
			else
			{
				if(batteryLow_List.size() == 0)
				{
					return getLiplisBody(emotion, point);
				}
				else
				{
					return selectBody(batteryLow_List);
				}
			}
		}catch(Exception e)
		{
			return normalList.get(0);
		}
	}
	public int getLiplisBodyId(int emotion, int point ,int eyeState, int mouthState)
	{
		try
		{
			ObjBody body =getLiplisBody(emotion,point);
			return body.getLiplisBodyId(eyeState, mouthState);
		}catch(Exception e)
		{
			return R.drawable.normal_1_1_1;
		}
	}

    /// <summary>
    /// ボティをランダムに取得する
    /// </summary>
	public ObjBody getLiplisBodyByEmotionList(List<Integer> emotionList, List<Integer> pointList)
	{
		//インデックス
		int idx = 0;
		int max = 0; //マックス値
		int maxIdx = 0;

		// 感情値
		int[] emotionArray = new int[11];
		int pEmo = 0;


		//リストが空ならノーマルを返しておく
		if(!emotionList.isEmpty()){return selectBody(normalList);}

		//エモーションリストを回して積算エモーション値を算出
		for(Integer emotion : emotionList)
		{
			emotionArray[emotion] += emotionList.get(idx);
			idx++;
		}

		//積算エモーション値からマックス値を取得
	    for (idx = 1; idx < emotionArray.length; idx++) {   //(2)
	    	pEmo = Math.abs(emotionArray[idx]);
	    	if(pEmo > 0)
	    	{
	    		if(pEmo >= max)
		    	{
	    			//マックス値更新
		    		max    = pEmo;

		    		//マックスIDを取得しておく(同値でも後がち)
		    		maxIdx = idx;
		    	}
	    	}

	    }

		//マックスIDのエモーションを返す
		return getLiplisBody(maxIdx,99);

	}

    /// <summary>
    /// ボティをランダムに取得する
    /// </summary>
	protected ObjBody selectBody(List<ObjBody> lst)
	{
		if(!lst.isEmpty())
		{
			Random rnd = new Random();
			return lst.get(rnd.nextInt(lst.size()));
		}
		return normalList.get(0);
	}

    /// <summary>
    /// デフォルトボディを取得する
    /// </summary>
	public ObjBody getDefaultBody()
	{
		return normalList.get(0);
	}
}
