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
package little.cute.renew.Obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import little.cute.renew.R;

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
		normalList.add(new ObjBody(R.drawable.normal_2_1_1, R.drawable.normal_2_1_2, R.drawable.normal_2_2_1, R.drawable.normal_2_2_2, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862));
		normalList.add(new ObjBody(R.drawable.normal_3_1_1, R.drawable.normal_3_1_2, R.drawable.normal_3_2_1, R.drawable.normal_3_2_2, R.drawable.normal_3_3_1, R.drawable.normal_3_3_2));
		normalList.add(new ObjBody(R.drawable.normal_4_1_1, R.drawable.normal_4_1_2, R.drawable.normal_4_2_1, R.drawable.normal_4_2_2, R.drawable.normal_4_3_1, R.drawable.normal_4_3_2));
		normalList.add(new ObjBody(R.drawable.normal_5_1_1, R.drawable.normal_5_1_2, R.drawable.normal_5_2_1, R.drawable.normal_5_2_2, R.drawable.pft63v7cgkj6c53f, R.drawable.pdsdc2g642qhg2tp));
		normalList.add(new ObjBody(R.drawable.normal_6_1_1, R.drawable.normal_6_1_2, R.drawable.normal_6_2_1, R.drawable.normal_6_2_2, R.drawable.pm01wllt1l2qqfvw, R.drawable.p2axd9q387msl13w));
		joy_p_List.add(new ObjBody(R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup, R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup, R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup));
		joy_p_List.add(new ObjBody(R.drawable.p9xok3hsbn4heujz, R.drawable.p7wvubqmz4bshr9a, R.drawable.p9xok3hsbn4heujz, R.drawable.p7wvubqmz4bshr9a, R.drawable.p2axd9q387msl13w, R.drawable.pm01wllt1l2qqfvw));
		joy_p_List.add(new ObjBody(R.drawable.p5w14jzgomi3lpyl, R.drawable.p3v8dq8ad3peomov, R.drawable.p5w14jzgomi3lpyl, R.drawable.p3v8dq8ad3peomov, R.drawable.p5w14jzgomi3lpyl, R.drawable.p3v8dq8ad3peomov));
		joy_m_List.add(new ObjBody(R.drawable.pk54uedkjp9fk9wv, R.drawable.pi4b4mmf87gqn6l6, R.drawable.pg4iduv9won1r4bh, R.drawable.pe3pn143l6ucu10r, R.drawable.pc2wx9exan1nyzq2, R.drawable.ptcsexi7g9louly2));
		joy_m_List.add(new ObjBody(R.drawable.prcyn4r25rszxjnd, R.drawable.p20cck02u7v2igpu, R.drawable.p00jls9wjp2dmee5, R.drawable.pyzpvziq869opb3g, R.drawable.pwyw47rkwogzt9tq, R.drawable.pd8smvwv3a01ov1q));
		admiration_p_List.add(new ObjBody(R.drawable.pb8zv25psr7cstr1, R.drawable.p9765aejg9emwqgc, R.drawable.p76deiod5qlxzo6m, R.drawable.p9765aejg9emwqgc, R.drawable.pb8zv25psr7cstr1, R.drawable.p9765aejg9emwqgc));
		admiration_p_List.add(new ObjBody(R.drawable.p56kopx7t7s83mvx, R.drawable.plgg5d1i0tday83x, R.drawable.p56kopx7t7s83mvx, R.drawable.plgg5d1i0tday83x, R.drawable.pjfmflbcpbkl25t8, R.drawable.phetosk6dsrw53ii));
		admiration_m_List.add(new ObjBody(R.drawable.pfe0y0t02ay7918t, R.drawable.pdd7782urr5icyx4, R.drawable.pun3pw75xdpj8k54, R.drawable.pm1r5vkp5pfw1tow, R.drawable.p3bnnjozbbzxxfww, R.drawable.p1auwryt0t680dm7));
		peace_p_List.add(new ObjBody(R.drawable.pft63v7cgkj6c53f, R.drawable.pdsdc2g642qhg2tp, R.drawable.pft63v7cgkj6c53f, R.drawable.pdsdc2g642qhg2tp, R.drawable.pft63v7cgkj6c53f, R.drawable.pdsdc2g642qhg2tp));
		peace_p_List.add(new ObjBody(R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862));
		peace_m_List.add(new ObjBody(R.drawable.pk54uedkjp9fk9wv, R.drawable.pi4b4mmf87gqn6l6, R.drawable.pg4iduv9won1r4bh, R.drawable.pe3pn143l6ucu10r, R.drawable.pc2wx9exan1nyzq2, R.drawable.ptcsexi7g9louly2));
		peace_m_List.add(new ObjBody(R.drawable.prcyn4r25rszxjnd, R.drawable.p20cck02u7v2igpu, R.drawable.p00jls9wjp2dmee5, R.drawable.pyzpvziq869opb3g, R.drawable.pwyw47rkwogzt9tq, R.drawable.pd8smvwv3a01ov1q));
		ecstasy_p_List.add(new ObjBody(R.drawable.pjfmflbcpbkl25t8, R.drawable.phetosk6dsrw53ii, R.drawable.pjfmflbcpbkl25t8, R.drawable.phetosk6dsrw53ii, R.drawable.p56kopx7t7s83mvx, R.drawable.plgg5d1i0tday83x));
		ecstasy_p_List.add(new ObjBody(R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup, R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup, R.drawable.puolu83nfkc4ealp, R.drawable.pbyhbw7xm6x6awup));
		amazement_m_List.add(new ObjBody(R.drawable.pfe0y0t02ay7918t, R.drawable.pdd7782urr5icyx4, R.drawable.pun3pw75xdpj8k54, R.drawable.pm1r5vkp5pfw1tow, R.drawable.p3bnnjozbbzxxfww, R.drawable.p1auwryt0t680dm7));
		amazement_p_List.add(new ObjBody(R.drawable.pcz7l66uqaablbnp, R.drawable.payeuefoerhmp8cz, R.drawable.pcz7l66uqaablbnp, R.drawable.payeuefoerhmp8cz, R.drawable.pcz7l66uqaablbnp, R.drawable.payeuefoerhmp8cz));
		amazement_m_List.add(new ObjBody(R.drawable.p8xl4loi38oxs62a, R.drawable.p6xsdtycrqu8w3rl, R.drawable.p8xl4loi38oxs62a, R.drawable.p6xsdtycrqu8w3rl, R.drawable.p8xl4loi38oxs62a, R.drawable.p6xsdtycrqu8w3rl));
		rage_p_List.add(new ObjBody(R.drawable.phl5296ch7ybh1s3, R.drawable.pflcbgf76o5mlyid, R.drawable.phl5296ch7ybh1s3, R.drawable.pflcbgf76o5mlyid, R.drawable.phl5296ch7ybh1s3, R.drawable.pflcbgf76o5mlyid));
		rage_m_List.add(new ObjBody(R.drawable.pdkjlop1u6cxow7o, R.drawable.puuf2ctb1rwzkifo, R.drawable.pdkjlop1u6cxow7o, R.drawable.puuf2ctb1rwzkifo, R.drawable.pstmck25p93ang5z, R.drawable.pqttlrczeqalrdu9));
		rage_m_List.add(new ObjBody(R.drawable.rage_m_2_1_1, R.drawable.rage_m_2_1_2, R.drawable.rage_m_2_2_1, R.drawable.rage_m_2_2_2, R.drawable.rage_m_2_3_1, R.drawable.rage_m_2_3_2));
		interest_p_List.add(new ObjBody(R.drawable.pb8zv25psr7cstr1, R.drawable.p9765aejg9emwqgc, R.drawable.p76deiod5qlxzo6m, R.drawable.p9765aejg9emwqgc, R.drawable.pb8zv25psr7cstr1, R.drawable.p9765aejg9emwqgc));
		interest_m_List.add(new ObjBody(R.drawable.p7o03xzhp8x8pln9, R.drawable.p5n7c58beq4jtidj, R.drawable.p7o03xzhp8x8pln9, R.drawable.p5n7c58beq4jtidj, R.drawable.p7o03xzhp8x8pln9, R.drawable.p5n7c58beq4jtidj));
		interest_m_List.add(new ObjBody(R.drawable.pdkjlop1u6cxow7o, R.drawable.puuf2ctb1rwzkifo, R.drawable.pdkjlop1u6cxow7o, R.drawable.puuf2ctb1rwzkifo, R.drawable.pstmck25p93ang5z, R.drawable.pqttlrczeqalrdu9));
		respect_p_List.add(new ObjBody(R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862));
		respect_m_List.add(new ObjBody(R.drawable.p3nemch537buwg2u, R.drawable.pkwa30mf9tvvs2au, R.drawable.p3nemch537buwg2u, R.drawable.pkwa30mf9tvvs2au, R.drawable.piwhd8vayb26w005, R.drawable.pgvomf44ns9hzxpf));
		respect_m_List.add(new ObjBody(R.drawable.pfe0y0t02ay7918t, R.drawable.pdd7782urr5icyx4, R.drawable.pun3pw75xdpj8k54, R.drawable.pm1r5vkp5pfw1tow, R.drawable.p3bnnjozbbzxxfww, R.drawable.p1auwryt0t680dm7));
		calmly_p_List.add(new ObjBody(R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862, R.drawable.phkh9g0qlh1prahs, R.drawable.pfjoio9kaz80v862));
		calmly_m_List.add(new ObjBody(R.drawable.prk1bvc4c9clkvrx, R.drawable.ppj8k3my1qjvotg8, R.drawable.prk1bvc4c9clkvrx, R.drawable.calmly_m_1_2_2, R.drawable.prk1bvc4c9clkvrx, R.drawable.ppj8k3my1qjvotg8));
		proud_p_List.add(new ObjBody(R.drawable.pkwa30mf9tvvs2au, R.drawable.p3nemch537buwg2u, R.drawable.pkwa30mf9tvvs2au, R.drawable.p3nemch537buwg2u, R.drawable.pgvomf44ns9hzxpf, R.drawable.piwhd8vayb26w005));
		proud_p_List.add(new ObjBody(R.drawable.plil3i4nepxhvovt, R.drawable.p2shl69xlbhjra3t, R.drawable.plil3i4nepxhvovt, R.drawable.p2shl69xlbhjra3t, R.drawable.proud_p_2_3_1, R.drawable.proud_p_2_3_2));
		proud_p_List.add(new ObjBody(R.drawable.p9xok3hsbn4heujz, R.drawable.p7wvubqmz4bshr9a, R.drawable.p9xok3hsbn4heujz, R.drawable.p7wvubqmz4bshr9a, R.drawable.p2axd9q387msl13w, R.drawable.pm01wllt1l2qqfvw));
		proud_m_List.add(new ObjBody(R.drawable.p9f9s1zlnrz8j3jw, R.drawable.p9f9s1zlnrz8j3jw, R.drawable.p9f9s1zlnrz8j3jw, R.drawable.p9f9s1zlnrz8j3jw, R.drawable.p9f9s1zlnrz8j3jw, R.drawable.p9f9s1zlnrz8j3jw));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_1_1_1, R.drawable.battery_low_1_1_2, R.drawable.battery_low_1_2_1, R.drawable.battery_low_1_2_2, R.drawable.battery_low_1_3_1, R.drawable.battery_low_1_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_2_1_1, R.drawable.battery_low_2_1_2, R.drawable.battery_low_2_2_1, R.drawable.battery_low_2_2_2, R.drawable.battery_low_2_3_1, R.drawable.battery_low_2_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_3_1_1, R.drawable.battery_low_3_1_2, R.drawable.battery_low_3_2_1, R.drawable.battery_low_3_2_2, R.drawable.battery_low_3_3_1, R.drawable.battery_low_3_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_4_1_1, R.drawable.battery_low_4_1_2, R.drawable.battery_low_4_2_1, R.drawable.battery_low_4_2_2, R.drawable.battery_low_4_3_1, R.drawable.battery_low_4_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_5_1_1, R.drawable.battery_low_5_1_2, R.drawable.battery_low_5_2_1, R.drawable.battery_low_5_2_2, R.drawable.battery_low_5_3_1, R.drawable.battery_low_5_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_6_1_1, R.drawable.battery_low_6_1_2, R.drawable.battery_low_6_2_1, R.drawable.battery_low_6_2_2, R.drawable.battery_low_6_3_1, R.drawable.battery_low_6_3_2));
		batteryLow_List.add(new ObjBody(R.drawable.battery_low_7_1_1, R.drawable.battery_low_7_1_2, R.drawable.battery_low_7_2_1, R.drawable.battery_low_7_2_2, R.drawable.battery_low_7_3_1, R.drawable.battery_low_7_3_2));
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
