package meite.example.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gavin
 * @date 2018/12/26 10:31
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("tcp服务端启动");
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(8080);
        try {
            while (true) {
            Socket accept = socket.accept();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len = is.read(bytes);
                        String result = new String(bytes, 0, len);
                        System.out.println("接受数据：" + result);
                    } catch (IOException e) {}
                }
            });
            while (true) {
                Thread.sleep(1000);
                System.out.println("测试阻塞------");
            }

            }
        } catch (Exception e) {

        } finally {
            socket.close();
        }

    }
}

class TcpClient{
    public static void main(String[] args) throws IOException {
        System.out.println("tcp客户端启动");
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();
        os.write("hello world".getBytes());
        socket.close();
    }
}