package com.liusp.roommv.activeMQ;

import com.liusp.roommv.entity.salesOrder.SalesOrder;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by jackyliu on 2015/3/13.
 */
public class MessageConverterLocal implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        if(!(o instanceof  SalesOrder)){
            throw  new RuntimeException("object is not SalesOrder");
        }
        SalesOrder salesOrder = (SalesOrder) o;
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("productId",salesOrder.getProductId());
        mapMessage.setString("userId", salesOrder.getUserId());
        return mapMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        if(!(message instanceof MapMessage)){
            throw new RuntimeException("message is not MapMessage");
        }
        MapMessage mapMessage = (MapMessage) message;
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setProductId(mapMessage.getString("productId"));
        salesOrder.setUserId(mapMessage.getString("userId"));
        return salesOrder;
    }
}
