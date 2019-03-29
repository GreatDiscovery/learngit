package meite.example.socket;

import java.io.IOException;
import java.net.*;

/**
 * 先写服务器端
 *
 * @author gavin
 * @date 2018/12/26 9:45
 */

// 服务器端ip+port
class UdpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("udp服务器启动");
        DatagramSocket ds = new DatagramSocket(8080);
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        ds.receive(dp);
        System.out.println("source IP:" + dp.getAddress() + ":" + dp.getPort());
        String result = new String(dp.getData(), 0, dp.getLength());
        System.out.println(result);
        ds.close();
    }
}

public class UdpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("udp客户端启动");
        DatagramSocket ds = new DatagramSocket();
        String str = "蚂蚁课堂";
        byte[] bytes = str.getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8080);
        ds.send(dp);
        ds.close();
    }
}
