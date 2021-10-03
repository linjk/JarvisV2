package cn.linjk.jarvis.message.config.netty;

import cn.linjk.jarvis.common.entity.TargetDevice;
import cn.linjk.jarvis.message.config.MarsharllingCodeCFactory;
import cn.linjk.jarvis.message.handler.CustomNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: NettyClient
 * @author: linjk
 * @date: 2021/10/3 上午12:20
 * @description:
 */
@Slf4j
public class NettyClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final Integer SERVER_PORT = 9103;
    private final EventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;
    private ChannelFuture channelFuture;

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect(SERVER_IP, SERVER_PORT);
        nettyClient.sendData();
    }

    private void connect(String serverIp, Integer serverPort) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workGroup).channel(NioSocketChannel.class)
                    // 表示缓存区动态调配(自适应)，但会有一定的性能问题，看场景使用
                    .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    // 缓冲区池化操作
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarsharllingCodeCFactory.buildMarshallingDecoder());
                            socketChannel.pipeline().addLast(MarsharllingCodeCFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast(new CustomNettyClientHandler());
                        }
                    });
            channelFuture = bootstrap.connect(serverIp, serverPort).sync();
            log.info("connect to netty server...");
            channel = channelFuture.channel();
        }
        catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

    public void sendData() {
        for (int i = 0; i < 10 ; i++) {
            log.info("sending data, no: {}", i);
            TargetDevice target = new TargetDevice();
            target.setDeviceCode("D" + i);
            channel.writeAndFlush(target);
        }
    }

    private void close() {
        try {
            channelFuture.channel().closeFuture().sync();
            workGroup.shutdownGracefully();
            log.info("netty shutdown gracefully");
        }
        catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
