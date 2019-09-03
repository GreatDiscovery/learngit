package meite.example.socket;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 直接缓冲区与非直接缓冲区区别
 *
 * @author gavin
 * @date 2018/12/26 15:15
 */
public class TestDirectBuffer {
    // 非直接缓冲区
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\github\\learngit\\src\\main\\resources\\statics\\1.png");
        FileOutputStream fos = new FileOutputStream("E:\\github\\learngit\\src\\main\\resources\\statics\\2.png");
        // 创建通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        inChannel.close();
        outChannel.close();
        fos.close();
        fis.close();
    }


    // 直接缓冲区
    @Test
    public void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("E:\\github\\learngit\\src\\main\\resources\\statics\\1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("E:\\github\\learngit\\src\\main\\resources\\statics\\2.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 定义映射文件
        MappedByteBuffer inMapperByte = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperByte = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        byte[] bytes = new byte[inMapperByte.limit()];
        inMapperByte.get(bytes);
        outMapperByte.put(bytes);
        inChannel.close();
        outChannel.close();
    }
}
