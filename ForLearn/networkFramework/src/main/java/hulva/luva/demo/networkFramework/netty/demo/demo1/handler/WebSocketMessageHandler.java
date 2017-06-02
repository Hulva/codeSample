package hulva.luva.demo.networkFramework.netty.demo.demo1.handler;

import io.netty.channel.ChannelHandlerContext;

public interface WebSocketMessageHandler {
   String handleMessage(ChannelHandlerContext ctx, String frameText);
}
