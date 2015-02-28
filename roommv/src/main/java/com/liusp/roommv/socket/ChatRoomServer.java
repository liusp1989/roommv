package com.liusp.roommv.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by jackyliu on 2015/3/20.
 */
public class ChatRoomServer {
    public static void start(){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup sub = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,sub).channel(NioServerSocketChannel.class).childHandler(new ChatRoomInitializer());
    }
}
