import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by jackyliu on 2015/3/17.
 */
public class NIOClientSocket {
    private InetSocketAddress inetSocketAddress;

    public NIOClientSocket(String hostname, int port) {
        inetSocketAddress = new InetSocketAddress(hostname, port);
    }

    /**
     * 发送请求数据
     * @param requestData
     */
    public void send(String requestData) {
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            socketChannel.write(ByteBuffer.wrap(requestData.getBytes()));
            while (true) {
                byteBuffer.clear();
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    System.out.println("Client: readBytes = " + readBytes);
                    System.out.println("Client: data = " + new String(byteBuffer.array(), 0, readBytes));
                    socketChannel.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        String requestData = "Actions speak louder than words!";
        int port = 9099;
        new NioTcpClient(hostname, port).send(requestData);
    }
}
