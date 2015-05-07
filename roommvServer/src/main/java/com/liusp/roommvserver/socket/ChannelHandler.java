package com.liusp.roommvserver.socket;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.log4j.Logger;

import com.liusp.roommvserver.common.WebApplicationListener;
import com.liusp.roommvserver.service.redis.RedisVisitInfoService;
@Sharable
public class ChannelHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger.getLogger(ChannelHandler.class);
	private static RedisVisitInfoService visitInfoService = WebApplicationListener
			.getWebApplicationContext().getBean(RedisVisitInfoService.class);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
		try {
			visitInfoService.addVisitInfo(msg.toString());
			ctx.writeAndFlush("{code:500}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ctx.writeAndFlush("{code:200}");
			logger.error("bootstrap server 添加访问者信息失败", e);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
