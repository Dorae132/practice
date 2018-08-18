package com.nsb.practice.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * java原生nio
 * @author Dorae
 *
 */
public class NioServer {

	public static void main(String[] args) throws Exception {
		Selector serverSelector = Selector.open();
		Selector clientSelector = Selector.open();
		new Thread(() -> {
			try {
				// server start
				ServerSocketChannel listenerChannel = ServerSocketChannel.open();
				listenerChannel.socket().bind(new InetSocketAddress(8080));
				listenerChannel.configureBlocking(false);
				listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
				
				// monitor whether there is a connector or not
				while (true) {
					if (serverSelector.select(1) > 0) {
						Set<SelectionKey> set = serverSelector.selectedKeys();
						Iterator<SelectionKey> iterator = set.iterator();
						while (iterator.hasNext()) {
							SelectionKey key = iterator.next();
							if (key.isAcceptable()) {
								try {
									SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
									clientChannel.configureBlocking(false);
									clientChannel.register(clientSelector, SelectionKey.OP_READ);
								} catch (Exception e) {
									// TODO: handle exception
								} finally {
									iterator.remove();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}).start();
		
		new Thread(() -> {
			try {
				// monitor whether there is a connector that has data need to be processed.
				while (true) {
					if (clientSelector.select(1) > 0) {
						Set<SelectionKey> set = clientSelector.selectedKeys();
						Iterator<SelectionKey> iterator = set.iterator();
						
						while (iterator.hasNext()) {
							SelectionKey key = iterator.next();
							try {
								SocketChannel clientChannel = (SocketChannel) key.channel();
								ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
								clientChannel.read(byteBuffer);
								byteBuffer.flip();
								System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
							} finally {
								iterator.remove();
								key.interestOps(SelectionKey.OP_READ);
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}).start();
	}
}
