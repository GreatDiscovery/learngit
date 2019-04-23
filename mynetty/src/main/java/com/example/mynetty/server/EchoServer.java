package com.example.mynetty.server;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        ExecutorService boss = Executors.newFixedThreadPool(5);
        ExecutorService worker = Executors.newFixedThreadPool(5);
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("serverHandler", new EchoServerHandler());
                return pipeline;
            }
        });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080)).getCloseFuture();
        System.out.println("服务端启动");
        while (future.isDone()) {
            System.out.println("服务完成");
        }
    }
}
