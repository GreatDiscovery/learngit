package com.example.mytomcat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class MyResponse {
    private SelectionKey selectionKey;

    public MyResponse(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }
    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-type:text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        ByteBuffer bb = ByteBuffer.wrap(httpResponse.toString().getBytes(StandardCharsets.UTF_8));
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        long len = channel.write(bb);
        if (len == -1) {
            selectionKey.cancel();
        }
        bb.flip();
        channel.close();
        selectionKey.cancel();
    }
}
