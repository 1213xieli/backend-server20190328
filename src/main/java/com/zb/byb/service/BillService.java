package com.zb.byb.service;

import com.zb.byb.entity.BillInfo;

import java.util.List;

/**
 * 作者：谢李
 */
public interface BillService {

    /**
     * 查询账单列表
     * @param custId
     * @param info
     * @return
     * @throws Exception
     */
    List queryInfoRecordList(String custId, BillInfo info)throws Exception;

    /**
     * 账单单据查询
     * @param custId
     * @param info
     * @return
     * @throws Exception
     */
    String queryBillRecordById(String custId, BillInfo info)throws Exception;
}
