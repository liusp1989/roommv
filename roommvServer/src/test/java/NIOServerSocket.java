import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jackyliu on 2015/3/17.
 */
public class NIOServerSocket {
    private Selector selector;
    public static void main(String[] args) throws IOException {
        NIOServerSocket serverSocket = new NIOServerSocket();
        serverSocket.init();
        serverSocket.listen();
    }

    private  void listen() throws IOException {
        try {
            while (true){
                int num = selector.select();
                while (num>0){
                    Set keys = selector.selectedKeys();
                    Iterator selectionKeyIterator = keys.iterator();
                    while (selectionKeyIterator.hasNext()){
                        SelectionKey key = (SelectionKey) selectionKeyIterator.next();
                        selectionKeyIterator.remove();
                        handleInsOps(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleInsOps(SelectionKey key) throws IOException {
        try {
            if(key.isAcceptable()){
                System.out.print("accept");
                ServerSocketChannel newServerSocket = (ServerSocketChannel) key.channel();
                SocketChannel channel = newServerSocket.accept();
                channel.configureBlocking(false);
                channel.register(selector,SelectionKey.OP_READ);
            }else if(key.isReadable()){
                System.out.print("read");
            }else if (key.isWritable()){
                System.out.print("write");
            }else{
                System.out.print("asdsadsad");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9099));

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
