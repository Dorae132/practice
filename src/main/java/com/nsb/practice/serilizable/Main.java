package com.nsb.practice.serilizable;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Main {
	public static void main(String[] args) throws Exception {

		RandomAccessFile raf = new RandomAccessFile(
				"C:\\Users\\Dorae\\Desktop\\testByte.pdf", "rw");
		ByteBuffer buffer = ByteBuffer.allocate((int) raf.length());
		raf.getChannel().read(buffer);
		byte[] arr = buffer.array();

		ClientProxy clientProxy = new ClientProxy();
		MyInterface myInterface = (MyInterface) clientProxy.bind();
		Object object = myInterface.upload(arr);
		System.out.println("end");
	}
}
