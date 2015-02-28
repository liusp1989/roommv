package com.liusp.roommv.service.salesOrder;

import com.liusp.roommv.dao.salesOrder.SalesOderDao;
import com.liusp.roommv.entity.salesOrder.SalesOrder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jackyliu on 2015/3/13.
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService{
    @Resource
    private SalesOderDao salesOderDao;
    @Resource
    private JmsTemplate jmsTemplate1;
    @Override
    public void addSalesorder(SalesOrder salesOrder) {
        salesOderDao.addSalesDao(salesOrder);
    }

    @Override
    public void sendSalesOrder(SalesOrder salesOrder) {
        jmsTemplate1.convertAndSend(salesOrder);
    }

}
