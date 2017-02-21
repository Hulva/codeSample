package hulva.luva.demo.networkFramework.netty.handler;

import io.netty.channel.ChannelHandlerContext;

public interface WebSocketMessageHandler {
   String handleMessage(ChannelHandlerContext ctx, String frameText);
}
