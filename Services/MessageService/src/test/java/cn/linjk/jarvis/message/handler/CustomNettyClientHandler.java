package cn.linjk.jarvis.message.handler;

import cn.linjk.jarvis.common.entity.TargetDevice;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: CustomNettyClientHandler
 * @author: linjk
 * @date: 2021/10/3 上午9:02
 * @description:
 */
@Slf4j
public class CustomNettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            TargetDevice target = (TargetDevice) msg;
            log.info("device msg read: {}", target.toString());
        }
        finally {
            // 清除缓存
            ReferenceCountUtil.release(msg);
        }
    }
}
