package com.zb.byb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.Constants;
import com.zb.byb.entity.Entruster;
import com.zb.byb.entity.MyInfo;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.util.JDService;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import com.zb.byb.util.RequestUtils;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/myInfo")
public class MyInfoController {
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation("查看我的信息")
    @GetMapping("/list")
    public ResponseEntity<MyInfo> queryMyInfo(HttpServletRequest request) {
        String openId=(String) request.getSession().getAttribute("openId");
//        System.out.println("openId="+openId);
        try {
            if (C.checkNull(openId)){
                throw new Exception("未获取openId");
            }
            String jsonback = myInfoService.viewMyInfo(openId);
            //"data":{"cflevel":"B","cfpigpen":"泗阳县王集镇人民路","cfraisestate":"0","cfraisestateText":"在养","cfwinternum":"1000","fcell":"18344895755","fkhsj":"2017-08-26","fname":"叶飞","ftelno":"18370533970","id":"Va4AAABJzw/Mns7U","manager":"王相","servicedep":"洋河服务部"}
            MyInfo myInfo= JsonPluginsUtil.jsonToBean(jsonback,MyInfo.class);
            /*MyInfo myInfo=new MyInfo();
            myInfo.setId(jsonObject.getString("id"));
            myInfo.setDept(jsonObject.getString("servicedep"));
//            myInfo.setEntrustedIdentity("");//被委托人身份证
//            myInfo.setEntrustedName("");//被委托人姓名
            myInfo.setEntruster(entruster);
            myInfo.setManager(jsonObject.getString("manager"));
            myInfo.setManagerTelNum(jsonObject.getString("fcell"));
            myInfo.setName(jsonObject.getString("fname"));
            myInfo.setPiggeryAddress(jsonObject.getString("cfpigpen"));//猪舍地址
            myInfo.setRegisterDate(jsonObject.getString("fkhsj"));
            myInfo.setStatus(jsonObject.getString("cfraisestateText"));
            myInfo.setTelNum(jsonObject.getString("ftelno"));
            myInfo.setGrowUp(jsonObject.getString("cflevel"));
            System.out.println(myInfo.getName());*/
            ResponseEntity responseEntity=new ResponseEntity();
            responseEntity.setData(myInfo);
           /* System.out.println("json="+myInfoService.viewMyInfo(openId));
            JSONObject result = JSONObject.fromObject(myInfoService.viewMyInfo(openId));
            String dataInfo = result.getString("data");
            myInfo = objectMapper.readValue(dataInfo, MyInfo.class);*//*
            ResponseEntity<MyInfo> resp=new ResponseEntity<>();
            resp.setData(myInfo);*/
            return responseEntity;
        } catch (Exception e) {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }

    }
}
