package com.liusp.roommvserver.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.apache.log4j.Logger;

public class SocketServer {
	private static final Logger logger = Logger.getLogger(SocketServer.class);
	private static final String LISTEN_SERVER = "127.0.0.1";
	private static final Integer LISTEN_PORT = 10000;
	public static void createServerSocket() throws Exception {
		EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
		EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							// TODO Auto-generated method stub
							ch.pipeline().addLast(
									new LengthFieldBasedFrameDecoder(512, 0, 4,
											0, 4), new StringEncoder(),
									new StringDecoder(), new ChannelHandler());
						}
					});
			ChannelFuture future = bootstrap.bind(LISTEN_SERVER, LISTEN_PORT)
					.sync();
			logger.info("bootstrap server开始。。。。。。。。。。。。。。。。");
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			workerEventLoopGroup.shutdownGracefully();
			bossEventLoopGroup.shutdownGracefully();
		}
	}


	// public static void main(String[] args) throws Exception {
	// new SocketServer().createServerSocket();
	//
	// }
}
