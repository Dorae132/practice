package com.nsb.practice.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * processor for server
 * @author Dorae
 *
 */
public class Processor {

	private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());
	
	private Selector selector;

	public Processor() throws IOException {
		super();
		this.selector = SelectorProvider.provider().openSelector();
		start();
	}
	
	public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
		socketChannel.register(this.selector, SelectionKey.OP_READ);
	}
	
	public void wakeup() {
		this.selector.wakeup();
	}
	
	public void start() {
		SERVICE.submit(() -> {
			while (true) {
				if (selector.select(500) < 0) {
					continue;
				}
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					if (key.isReadable()) {
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						SocketChannel socketChannel = (SocketChannel) key.channel();
						int count = socketChannel.read(byteBuffer);
						if (count < 0) {
							socketChannel.close();
							key.cancel();
							continue;
						} else if (count == 0) {
							continue;
						} else {
							System.out.println("server receive message: " + new String(byteBuffer.array()));
							byteBuffer.clear();
							socketChannel.write(byteBuffer.wrap("server has received the message".getBytes()));
						}
					}
				}
			}
		});
	}
}
