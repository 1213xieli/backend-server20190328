package com.zb.byb.service.impl;

import com.zb.byb.entity.YuE;
import com.zb.byb.service.YuEService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class YuEServiceImpl implements YuEService {
    @Override
    public String queryYuE(YuE yuE,String userId) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHART");//微信
        map.put("data",yuE);//参数

        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_BALANCE);
        return jsonStr;
    }
}
