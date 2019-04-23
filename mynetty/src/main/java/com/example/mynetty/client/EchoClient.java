package com.example.mynetty.client;

import com.example.mynetty.server.EchoServerHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.*;

public class EchoClient {
    public static void main(String[] args) {
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        ExecutorService boos = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<>(1000));
        ExecutorService worker = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<>(1000));

        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos, worker));
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("clientHandler", new EchoClientHandler());
                return pipeline;
            }
        });

        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("localhost", 8080));
        System.out.println("客户端启动");
        Channel channel = connect.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入内容：");
            channel.write(scanner.next());
        }
    }
}
