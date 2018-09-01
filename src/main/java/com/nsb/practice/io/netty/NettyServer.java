package com.nsb.practice.io.netty;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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

	public static void main(String[] args) throws Exception {
		ServerBootstrap bootstrap = new ServerBootstrap();
		NioEventLoopGroup boos = new NioEventLoopGroup();
		NioEventLoopGroup workers = new NioEventLoopGroup();
		try {
			bootstrap.group(boos, workers)
				.channel(NioServerSocketChannel.class)
				.handler(new SimpleServerHandler())
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
	
					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						ch.pipeline().addLast(new StringDecoder());
						ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
	
							@Override
							protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
								System.out.println(msg);
								// this run for reactor thread, so there are no competitive condition
								ctx.channel().eventLoop().execute(() -> {
									
								});
								ctx.channel().eventLoop().schedule(() -> {}, 60, TimeUnit.SECONDS);
								// this could be ran by another thread, and this method may be invoked by many threads, so the mscQueue will be useful
								// the really write action will be processed by the reactor thread
								ctx.channel().write("");
							}
							
						});
					}
				});
			ChannelFuture feature = bootstrap.bind(8080).sync();
			feature.channel().closeFuture().sync();
		} finally {
			boos.shutdownGracefully();
			workers.shutdownGracefully();
		}
	}
	
	private static class SimpleServerHandler extends ChannelInboundHandlerAdapter {
		
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("channelActive");
		}
		
		@Override
		public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
			System.out.println("channelRegistered");
		}
		
		@Override
		public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
			System.out.println("handlerAdded");
		}
	}
}
