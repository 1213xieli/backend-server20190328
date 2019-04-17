package com.zb.byb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.byb.common.CommonFunc;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DataRecord;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.entity.UserInfo;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BaseController;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 投苗申请
 */
@RestController
@RequestMapping("/api/toumiao")
public class TouMiaoController extends BaseController {
    @Autowired
    private TouMiaoService touMiaoService;

    @ApiOperation("保存投苗申请")
    @PostMapping("/saveToumiaoApply")
    @ResponseBody
    public ResponseEntity<?> saveToumiaoApply(HttpServletRequest request, HttpServletResponse response, TouMiao touMiao) {
        try{
            return ResponseEntity.buildSuccess(touMiaoService.saveInfo(touMiao));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法保存数据");
        }
    }

    @ApiOperation("初始化投苗数据")
    @GetMapping("/queryTouMiaoInitData")
    @ResponseBody
    public ResponseEntity<TouMiao> queryTouMiaoInitData(HttpServletRequest request, String custId)
    {
        try{

            return ResponseEntity.buildSuccess(touMiaoService.queryListInitData(custId));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据养户id查询到投苗记录")
    @GetMapping("/queryTouMiaoRecordList")
    @ResponseBody
    public ResponseEntity<?> queryTouMiaoRecordList(HttpServletRequest request, String custId)
    {
        try{
            if (CommonFunc.checkNull(custId))
                throw new Exception("未传入养户id.");

            List list = touMiaoService.queryInfoRecordList(custId);
            PageInfo<TouMiao> info = new PageInfo(list);
            return ResponseEntity.build(200,new Message(), info);
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

    @ApiOperation("根据id查询到对象信息")
    @GetMapping("/queryInfoById")
    @ResponseBody
    public ResponseEntity<TouMiao> queryInfoById(String id)
    {
        try{
            return ResponseEntity.buildSuccess(touMiaoService.queryInfoById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.build(100, "无法查询到数据");
        }
    }

}
