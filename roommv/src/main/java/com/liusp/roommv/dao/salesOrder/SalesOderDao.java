package com.liusp.roommv.dao.salesOrder;

import com.liusp.roommv.annotation.MybatisMapper;
import com.liusp.roommv.entity.salesOrder.SalesOrder;

/**
 * Created by jackyliu on 2015/3/13.
 */
@MybatisMapper
public interface SalesOderDao {
    public void  addSalesDao(SalesOrder salesOrder);
}
