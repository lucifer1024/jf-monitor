package org.proxy.out.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		System.out.println("server received data :" + msg);
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		try {
			String body = new String(req, "UTF-8");
			System.out.println("server received data :" + body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.write(msg);// 写回数据，
	}

	public void channelReadComplete(ChannelHandlerContext ctx) {
		System.out.println("server channelReadComplete..");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // flush掉所有写回的数据
				.addListener(ChannelFutureListener.CLOSE); // 当flush完成后关闭channel
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();// 捕捉异常信息
		ctx.close();// 出现异常时关闭channel
	}
}
