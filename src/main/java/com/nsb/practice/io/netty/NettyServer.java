package com.nsb.practice.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty server
 * @author Dorae
 *
 */
public class NettyServer {

	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		NioEventLoopGroup boos = new NioEventLoopGroup();
		NioEventLoopGroup workers = new NioEventLoopGroup();
		bootstrap.group(boos, workers)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<NioSocketChannel>() {

				@Override
				protected void initChannel(NioSocketChannel ch) throws Exception {
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {

						@Override
						protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
							System.out.println(msg);
						}
						
					});
				}
			}).bind(8080);
	}
}
