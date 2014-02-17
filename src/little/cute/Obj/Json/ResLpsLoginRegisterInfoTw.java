//=======================================================================
//  ClassName : ResLpsLoginRegisterInfoTw
package little.cute.Obj.Json;

import java.util.ArrayList;
import java.util.List;

public class ResLpsLoginRegisterInfoTw {
    ///=============================
    ///プロパティ
    public List<RegisterTwUserInfo> twuserlist;

    /// <summary>
    /// コンストラクター
    /// </summary>
    public ResLpsLoginRegisterInfoTw()
    {
        this.twuserlist = new ArrayList<RegisterTwUserInfo>();
    }
    public ResLpsLoginRegisterInfoTw(List<RegisterTwUserInfo> twuserlist)
    {
        this.twuserlist = twuserlist;
    }
}
