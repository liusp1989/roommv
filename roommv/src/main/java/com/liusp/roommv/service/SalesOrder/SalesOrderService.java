package com.liusp.roommv.service.salesOrder;

import com.liusp.roommv.entity.salesOrder.SalesOrder;

/**
 * Created by jackyliu on 2015/3/13.
 */
public interface SalesOrderService {
    public  void addSalesorder(SalesOrder salesOrder);
    public  void sendSalesOrder(SalesOrder salesOrder);
}
