import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by jackyliu on 2015/3/17.
 */
public interface SockHandler {
    /**
     * 处理{@link SelectionKey#OP_ACCEPT}事件
     * @param key
     * @throws IOException
     */
    void handleAccept(SelectionKey key) throws IOException;
    /**
     * 处理{@link SelectionKey#OP_READ}事件
     * @param key
     * @throws IOException
     */
    void handleRead(SelectionKey key) throws IOException;
    /**
     * 处理{@link SelectionKey#OP_WRITE}事件
     * @param key
     * @throws IOException
     */
    void handleWrite(SelectionKey key) throws IOException;
}
