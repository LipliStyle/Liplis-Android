//=======================================================================
//  ClassName : ObjBody
//  概要      : ボディオブジェクト
//
//	extends   : 
//	impliments: 	
//
//  LiplisAndroidシステム      
//  Copyright(c) 2011-2011 sachin. All Rights Reserved. 
//=======================================================================
package little.cute.Obj;


public class ObjBody {
    ///=============================
    /// 定数
    private int eye_1_o;
	private int eye_1_c;
    private int eye_2_o;
    private int eye_2_c;
    private int eye_3_o;
    private int eye_3_c;
    
    /// <summary>
    /// コンストラクター
    /// </summary>
    public ObjBody(){}
    public ObjBody(int pEye_1_o,int pEye_1_c, 
		    		int pEye_2_o, int pEye_2_c,
		    		int pEye_3_o, int pEye_3_c
		    		)
    {
    	this.eye_1_o = pEye_1_o;
    	this.eye_1_c = pEye_1_c;
    	this.eye_2_o = pEye_2_o;
    	this.eye_2_c = pEye_2_c;
    	this.eye_3_o = pEye_3_o;
    	this.eye_3_c = pEye_3_c;
    }
    
    ///====================================================================
    ///
    ///                           ゲットID
    ///                         
    ///====================================================================
   
    /// <summary>
    /// getLiplisBodyId
    /// 状態からIDを返す
    /// </summary>
    public int getLiplisBodyId(int eyeState, int mouthState)
    {
		try
		{
			if(mouthState == 2)
			{
				if(eyeState == 2){
					return eye_2_c; 
				}
				else if(eyeState == 3){
					return eye_3_c;
				}
				else{
					return eye_1_c;
				}
			}
			else
			{
				if(eyeState == 2){
					return eye_2_o; 
				}
				else if(eyeState == 3){
					return eye_3_o;
				}
				else{
					return eye_1_o;
				}
			}
		}catch(Exception e)
		{
			return eye_1_o;
		}
    }
    
    
    ///====================================================================
    ///
    ///                           ゲッターセッター
    ///                         
    ///====================================================================
    
    public int getEye_1_o() {return eye_1_o;}
	public void setEye_1_o(int eye_1_o) {this.eye_1_o = eye_1_o;}
	public int getEye_1_c() {return eye_1_c;}
	public void setEye_1_c(int eye_1_c) {this.eye_1_c = eye_1_c;}
	public int getEye_2_o() {return eye_2_o;}
	public void setEye_2_o(int eye_2_o) {this.eye_2_o = eye_2_o;}
	public int getEye_2_c() {return eye_2_c;	}
	public void setEye_2_c(int eye_2_c) {this.eye_2_c = eye_2_c;}
	public int getEye_3_o() {return eye_3_o;}
	public void setEye_3_o(int eye_3_o) {this.eye_3_o = eye_3_o;}
	public int getEye_3_c() {return eye_3_c;}
	public void setEye_3_c(int eye_3_c) {this.eye_3_c = eye_3_c;}


}
