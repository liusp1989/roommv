

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;



/**
 * 服务端事件处理实现类
 * 
 * @author shirdrn
 */
class ServerHandler implements Handler {
	private static final Logger logger = Logger.getLogger(Handler.class);

	public void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
				.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		logger.info("Server: accept client socket " + socketChannel);
		socketChannel.configureBlocking(false);
		socketChannel.register(key.selector(), SelectionKey.OP_READ);
	}


	public void handleRead(SelectionKey key) throws Exception {

		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int readBytes = socketChannel.read(byteBuffer);
		if (readBytes > 0) {
			logger.info("Server: readBytes = " + readBytes);
			logger.info("Server: data = "
					+ new String(byteBuffer.array(), 0, readBytes));
			String message = new String(byteBuffer.array(), 0, readBytes);
			if (message.equals("a")) {
				throw new RuntimeException("出错了");
					}
			// byteBuffer.flip();
			// socketChannel.write(byteBuffer);
		}
		socketChannel.close();

	}

	public void handleWrite(SelectionKey key) throws IOException {
		ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
		byteBuffer.flip();
		SocketChannel socketChannel = (SocketChannel) key.channel();
		socketChannel.write(byteBuffer);
		if (byteBuffer.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		byteBuffer.compact();
	}
public static void main(String[] args) {
		NioTcpServer server = new NioTcpServer("localhost", 10001);
	server.start();
}
}