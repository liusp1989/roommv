package com.liusp.roommv.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;

import org.apache.log4j.Logger;

public class SocketClient {
	private static final Logger logger = Logger.getLogger(SocketClient.class);
	private static final String LISTEN_SERVER = "127.0.0.1";
	private static final Integer LISTEN_PORT = 10000;
	private static ChannelHandler channelHandler = new ChannelHandler();

	public static void createClientSocket() throws Exception {
		EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap
					.group(workerEventLoopGroup)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.handler(
							new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {

								@Override
								protected void initChannel(SocketChannel ch)
										throws Exception {
									// TODO Auto-generated method stub
									ch.pipeline()
											.addLast(
													new LengthFieldPrepender(4,
															false),
													new StringEncoder(),
													new StringDecoder(),
													channelHandler);
								}

							});
			ChannelFuture future = bootstrap
					.connect(LISTEN_SERVER, LISTEN_PORT).sync();
			logger.info("bootstrap client 开始。。。。。。。。。。。。。。。。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ChannelHandler getChannelHandler() {
		return channelHandler;
	}

	public static void main(String[] args) throws IOException {
		// SocketClient.sendMessage("");
	}

}
