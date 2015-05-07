package com.liusp.roommv.activeMQ;

import com.liusp.roommv.entity.salesOrder.SalesOrder;
import com.liusp.roommv.service.salesOrder.SalesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by jackyliu on 2015/3/13.
 */
public class MessageListenerLocal implements MessageListener {
    public  static  final Logger logger = LoggerFactory.getLogger(MessageListenerLocal.class);
    @Resource
    private SalesOrderService salesOrderService;
    @Override
    public void onMessage(Message message) {
        try {
            if(!(message instanceof MapMessage)){
                throw new RuntimeException("message is not MapMessage");
            }
            MapMessage mapMessage = (MapMessage) message;
            SalesOrder salesOrder = new SalesOrder();
            salesOrder.setProductId(mapMessage.getString("productId"));
            salesOrder.setUserId(mapMessage.getString("userId"));
            salesOrderService.addSalesorder(salesOrder);
            logger.info(message.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
