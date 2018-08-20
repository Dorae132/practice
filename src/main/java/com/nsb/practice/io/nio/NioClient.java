package com.nsb.practice.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * NIO client
 * @author Dorae
 *
 */
public class NioClient {

	private Selector selector;
	
	public NioClient init(String serverIp, int port) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		selector = Selector.open();
		socketChannel.connect(new InetSocketAddress(serverIp, port));
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		return this;
	}
	
	public void listen() throws IOException {
		System.out.println("客户端启动");
		while (true) {
			selector.select();
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while (keys.hasNext()) {
				SelectionKey key = keys.next();
				keys.remove();
				if (key.isConnectable()) {// 连接事件
					SocketChannel channel = (SocketChannel) key.channel();
					// finish if there is a connecting
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.register(selector, SelectionKey.OP_READ);
					new Thread(() -> {
						while (true) {
							try {
								channel.write(ByteBuffer.wrap(new String("send message to server" + System.currentTimeMillis()).getBytes()));
								TimeUnit.SECONDS.sleep(2);
							} catch (IOException | InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();
					System.out.println("客户端连接成功");
				} else if (key.isReadable()) {// 读事件
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					channel.read(byteBuffer);
					byte[] data = byteBuffer.array();
					String message = new String(data);
					System.out.println("receive message from server:, size:" + byteBuffer.position() + "msg:" + message);
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new NioClient().init("127.0.0.1", 8080).listen();
	}
}
