

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * 简单处理器接口
 * 
 * @author shirdrn
 */
interface Handler {
	/**
	 * 处理{@link SelectionKey#OP_ACCEPT}事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleAccept(SelectionKey key) throws IOException;

	/**
	 * 处理{@link SelectionKey#OP_READ}事件
	 * 
	 * @param key
	 * @throws IOException
	 * @throws Exception
	 */
	void handleRead(SelectionKey key) throws Exception;

	/**
	 * 处理{@link SelectionKey#OP_WRITE}事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleWrite(SelectionKey key) throws IOException;
}
