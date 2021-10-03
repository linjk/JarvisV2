package cn.linjk.jarvis.message.handler;

import cn.linjk.jarvis.common.entity.TargetDevice;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: CustomNettyServerHandler
 * @author: linjk
 * @date: 2021/10/2 下午9:24
 * @description:
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class CustomNettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TargetDevice target = (TargetDevice) msg;
        log.info("device msg readed: {}", target.toString());

        target.setServerReceived(new Date());
        ctx.writeAndFlush(target);
    }
}
