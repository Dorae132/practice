package com.nsb.practice.io.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * multi reactor pattern
 * @author Dorae
 *
 */
public class MultiReactorNio {
	
	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(8080));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		int coreNum = Runtime.getRuntime().availableProcessors();
		Processor[] processors = new Processor[coreNum];
		for (int i = 0; i < processors.length; i++) {
			processors[i] = new Processor();
		}
		
		int index = 0;
		while (selector.select() > 0) {
			Set<SelectionKey> keys = selector.selectedKeys();
			for (SelectionKey selectionKey : keys) {
				keys.remove(selectionKey);
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = acceptServerSocketChannel.accept();
					socketChannel.configureBlocking(false);
					Processor processor = processors[(int)((index ++) % coreNum)];
					processor.addChannel(socketChannel);
					processor.wakeup();
				}
			}
		}
	}

}
