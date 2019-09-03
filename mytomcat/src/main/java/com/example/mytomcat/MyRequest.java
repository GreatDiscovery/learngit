package com.example.mytomcat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class MyRequest {
    private String url;
    private String method;
    private Map<String, String> param = new HashMap<>();

    public MyRequest(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        String httpRequest = "";
        ByteBuffer bb = ByteBuffer.allocate(16 * 1024);
        int length = 0;
        length = channel.read(bb);
        if (length < 0) {
            selectionKey.cancel();
        } else {
            httpRequest = new String(bb.array()).trim();
            String httpHead = httpRequest.split("\n")[0];
            url = httpHead.split("\\s")[1].split("\\?")[0];
            method = httpHead.split("\\s")[0];
            String path = httpHead.split("\\s")[1];
            String[] params = path.indexOf("?") > 0 ? path.split("\\?")[1].split("\\&") : null;
            if (params != null) {
                for (String tmp : params) {
                    param.put(tmp.split("\\=")[0], tmp.split("\\=")[1]);
                }
            }
        }

        System.out.println(this);
        bb.flip();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", param=" + param +
                '}';
    }
}
