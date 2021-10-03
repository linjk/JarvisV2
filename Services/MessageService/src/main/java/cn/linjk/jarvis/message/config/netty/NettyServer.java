package cn.linjk.jarvis.message.config.netty;

import cn.linjk.jarvis.message.config.MarsharllingCodeCFactory;
import cn.linjk.jarvis.message.handler.CustomNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: NettyServer
 * @author: linjk
 * @date: 2021/10/2 下午8:57
 * @description:
 */
@Slf4j
@Component
public class NettyServer implements InitializingBean, ApplicationListener<ContextClosedEvent> {
    @Autowired private CustomNettyServerHandler customNettyServerHandler;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();
    private ChannelFuture channelFuture;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        try {
            channelFuture.channel().closeFuture().sync();
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.info("netty shutdown gracefully");
        }
        catch(InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void init() {
        ServerBootstrap server = new ServerBootstrap();
        try {
            server.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 表示缓存区动态调配(自适应)，但会有一定的性能问题，看场景使用
                    .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    // 缓冲区池化操作
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .localAddress(9103)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarsharllingCodeCFactory.buildMarshallingDecoder());
                            socketChannel.pipeline().addLast(MarsharllingCodeCFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast(customNettyServerHandler);
                        }
                    });
            channelFuture = server.bind(9103).sync();
            log.info("netty start listening at 9103...");
        }
        catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
