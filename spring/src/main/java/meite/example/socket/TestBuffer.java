package meite.example.socket;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author gavin
 * @date 2018/12/26 14:09
 */
public class TestBuffer {
    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 三个关键属性
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("----------------------");
        byteBuffer.put("hello".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("----------------------");
        // 开启读取模式，将position置0
        byteBuffer.flip();
        // 可重复读
//        byteBuffer.rewind();
        System.out.println(byteBuffer.limit());
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("----------------");
        // 清空缓冲区依旧能读取到值，值被遗忘了
        byteBuffer.clear();
        System.out.println((char) byteBuffer.get());
    }
}
